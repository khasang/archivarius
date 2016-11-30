package io.khasang.archivarius.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.khasang.archivarius.entity.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Parse csv files from resourse directory
 * Strings are divided by semicolon
 * We are use CsvParser jackson-dataformat-csv, which is most popular and fasted parser
 */
@Component
public class FileCsvParser implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ReportService reportService;

    private static final org.apache.log4j.Logger log = Logger.getLogger(FileCsvParser.class);
    /**
     * Mapper for parser - read schema and make column model
      */
    CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
    CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("name")
            .addColumn("site")
            .addColumn("timespent", CsvSchema.ColumnType.NUMBER)
            .build().withColumnSeparator(';');

    /**
     * Use class-path resourse folder and make list of files
     */
    File folder = new ClassPathResource("examples").getFile();
    List<File> files = new ArrayList<>(Arrays.asList(folder.listFiles()));

    public FileCsvParser() throws IOException {
    }

    /**
     * Check exist of our files
     * @param file - checked file in resource dir
     * @return true if file exist, false if don't exist
     */
    public boolean checkFiles(File file) {
        if (file.exists()) {
            log.debug("File " + file.getName() + " is exist!");
            return true;
        } else {
            log.error("This file does not exist");
            return false;
        }
    }

    /**
     * Our task was work with files, which contains 10 or more strings
     * @param file
     * @return count rows in File
     */
    public int countNumberRows(File file) {
        int lines = 0;
        if (checkFiles(file)) {
            try (BufferedReader reader = new BufferedReader((new FileReader(file)))) {
                lines = (int) reader.lines().count();
            } catch (IOException e) {
                log.error("Can't read from file " + e);
            }
        }
        log.debug("File " + file.getName() + " contains " + lines + " strings");
        return lines;
    }

    /**
     *
     * @return the number of files in a directory
     */
    public int numberOfFiles() {
        return files.size();
    }

    /**
     * Check bad workers, who spent more of working time for social networking
     * TODO: rewrite method, which check 'vk.com' in database, not file!     *
     * @return method returns true if the user was on site http://vk.com
     */
    public boolean someoneAtVk() {
        for (File csvFile : files) {
            try {
                MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class)
                        .with(csvSchema)
                        .readValues(csvFile);
                while (it.hasNext()) {
                    Map<String, String> rowAsMap = it.next();
                    if (rowAsMap.get("site").equals("http://vk.com")) {
                        log.debug("We found site vk.com in file " + csvFile.getName());
                        return true;
                    }
                }
            } catch (IOException e) {
                log.error("Reading file + " + csvFile.getName() + "... " + e);
            }
        }
        log.debug("We can't find site vk.com in files ");
        return false;
    }

    /**
     * Make new Report for our database
     * @param file in source database
     * @return List of reports from file
     */
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
            log.error("List of file parser contains " + e);
        }
        log.debug("Get list of parsed files and load it into database");
        return list;
    }

    /**
     * This method makes autoload our csv parser at start of working application
     * TODO: check for dublicate counts, may be we must delete file after autoload
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (File csvFile : files) {
            newReportFromCsvFile(csvFile);
        }
    }
}
