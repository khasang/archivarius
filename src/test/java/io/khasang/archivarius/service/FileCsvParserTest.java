package io.khasang.archivarius.service;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
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
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile1()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile2()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile3()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile4()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile5()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile6()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile7()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile8()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile9()));
        assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile10()));
    }

    @Test
    public void testContentsFile() {
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile1()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile2()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile3()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile4()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile5()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile6()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile7()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile8()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile9()) >= 10);
        assertTrue(fileCsvParser.countNumberRows(fileCsvParser.getFile10()) >= 10);
    }

    @Test
    public void testNumberFiles() {
        assertTrue(fileCsvParser.numberOfFiles() >= 10);
    }

    @Test
    public void testSomeOneAtVk() {
        assertEquals(true, fileCsvParser.someoneAtVk());
    }

    /**
     * TODO: Создать таблицу в базе данных Report c нужными нам по заданию столбцами и связать
     * ее с Entity
     */
    @Test
    public void testEntityIsAlive() {
        assertNotNull(new Report());
    }
}
