package io.khasang.archivarius.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.khasang.archivarius.entity.Report;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Tanya on 27.11.2016.
 */
@Component
public class FileCsvParser implements ApplicationListener<ContextRefreshedEvent> {

    CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
    CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("name")
            .addColumn("site")
            .addColumn("timespent", CsvSchema.ColumnType.NUMBER)
            .build().withColumnSeparator(';');

    File folder = new ClassPathResource("examples").getFile();
    List<File> files = new ArrayList<>(Arrays.asList(folder.listFiles()));

    @Autowired
    ReportService reportService;


    public FileCsvParser() throws IOException {
    }

    public boolean checkFiles(File file) {
        if (file.exists()) {
            return true;
        } else {
            System.err.println("This file does not exist");
            return false;
        }
    }

    public int countNumberRows(File file) {
        int lines = 0;
        if (checkFiles(file)) {
            try (BufferedReader reader = new BufferedReader((new FileReader(file)))) {
                lines = (int) reader.lines().count();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    public int numberOfFiles() {
        return files.size();
    }

    /**
     * Использована библиотека парсера CSV. rowAsMap тут - список строки, разделенный на колонки
     * name, site, timespent
     *
     * @return есть ли кто-нибудь вконтакте
     */
    public boolean someoneAtVk() {
        for (File csvFile : files) {
            try {
                MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class)
                        .with(csvSchema)
                        .readValues(csvFile);
                while (it.hasNext()) {
                    Map<String, String> rowAsMap = it.next();
                    if (rowAsMap.get("site").equals("http://vk.com"))
                        return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Report> newReportFromCsvFile(File file) {
        List<Report> list = new ArrayList<>();
        try {
            MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(file);
            while (it.hasNext()) {
                Map<String, String> rowAsMap = it.next();
                Report report = new Report();
                report.setNameUser(rowAsMap.get("name"));
                report.setSite(rowAsMap.get("site"));
                report.setTimeInSecond(Integer.valueOf(rowAsMap.get("timespent")));
                list.add(report);
                reportService.addReport(report);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (File csvFile : files) {
            newReportFromCsvFile(csvFile);
        }
    }
}
