package io.khasang.archivarius.service;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import io.khasang.archivarius.entity.Report;
import org.junit.Assert;
import org.junit.Ignore;
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

    @Test
    public void testFilesExist() {
        for(File file : fileCsvParser.files) {
            assertEquals(true, fileCsvParser.checkFiles(file));
        }
    }

    @Test
    public void testContentsFile() {
        for(File file : fileCsvParser.files) {
            assertTrue(fileCsvParser.countNumberRows(file) >= 10);
        }
    }

    @Test
    public void testNumberFiles() {
        assertTrue(fileCsvParser.numberOfFiles() >= 10);
    }

    @Test
    public void testSomeOneAtVk() {
        assertEquals(true, fileCsvParser.someoneAtVk());
    }


}
