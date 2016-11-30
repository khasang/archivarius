package io.khasang.archivarius.service;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class FileCsvParserTest {
    @Autowired
    FileCsvParser fileCsvParser;

    @Test // testing method that checks the existence of the file
    public void testFilesExist() {
        for (File file : fileCsvParser.files) {
            assertEquals(true, fileCsvParser.checkFiles(file));
        }
    }

    /*testing method that checks the number of lines in the file
     (for the correct operation of the program should be more than 10)*/
    @Test
    public void testContentsFile() {
        for (File file : fileCsvParser.files) {
            assertTrue(fileCsvParser.countNumberRows(file) >= 10);
        }
    }

    /*testing method, which returns the number of files are in a directory
     (for the correct operation of the program should be more than 10)*/
    @Test
    public void testNumberFiles() {
        assertTrue(fileCsvParser.numberOfFiles() >= 10);
    }

    @Test // testing method that will check there is someone on site vk.com
    public void testSomeOneAtVk() {
        assertEquals(true, fileCsvParser.someoneAtVk());
    }
}
