package io.khasang.archivarius.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.hibernate.annotations.SourceType;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Tanya on 27.11.2016.
 */
@Component
public class FileCsvParser {

    CsvMapper mapper = new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY);
    CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("name")
            .addColumn("site")
            .addColumn("timespent", CsvSchema.ColumnType.NUMBER)
            .build().withColumnSeparator(';');

    File folder = new ClassPathResource("examples").getFile();
    File[] files = folder.listFiles();

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public File getFile3() {
        return file3;
    }

    public void setFile3(File file3) {
        this.file3 = file3;
    }

    public File getFile4() {
        return file4;
    }

    public void setFile4(File file4) {
        this.file4 = file4;
    }

    public File getFile5() {
        return file5;
    }

    public void setFile5(File file5) {
        this.file5 = file5;
    }

    public File getFile6() {
        return file6;
    }

    public void setFile6(File file6) {
        this.file6 = file6;
    }

    public File getFile7() {
        return file7;
    }

    public void setFile7(File file7) {
        this.file7 = file7;
    }

    public File getFile8() {
        return file8;
    }

    public void setFile8(File file8) {
        this.file8 = file8;
    }

    public File getFile9() {
        return file9;
    }

    public void setFile9(File file9) {
        this.file9 = file9;
    }

    public File getFile10() {
        return file10;
    }

    public void setFile10(File file10) {
        this.file10 = file10;
    }



    private File file1 = new ClassPathResource("examples/source001.csv").getFile();
    private File file2 = new ClassPathResource("examples/source002.csv").getFile();
    private File file3 = new ClassPathResource("examples/source003.csv").getFile();
    private File file4 = new ClassPathResource("examples/source004.csv").getFile();
    private File file5 = new ClassPathResource("examples/source005.csv").getFile();
    private File file6 = new ClassPathResource("examples/source006.csv").getFile();
    private File file7 = new ClassPathResource("examples/source007.csv").getFile();
    private File file8 = new ClassPathResource("examples/source008.csv").getFile();
    private File file9 = new ClassPathResource("examples/source009.csv").getFile();
    private File file10 = new ClassPathResource("examples/source010.csv").getFile();

    public FileCsvParser() throws IOException {
    }

    ;

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
        return files.length;
    }

    /**
     * Использована библиотека парсера CSV. rowAsMap тут - список строки, разделенный на колонки
     * name, site, timespent
     * @return есть ли кто-нибудь вконтакте
     */
    public boolean someoneAtVk() {
        for(File csvFile: files) {
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
}
