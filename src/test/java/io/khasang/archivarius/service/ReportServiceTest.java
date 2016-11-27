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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class ReportServiceTest {
    @Autowired
    ReportService reportService;
    @Autowired
    FileCsvParser fileCsvParser;

    @Test
    public void testAddReport() {
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile1()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile2()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile3()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile4()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile5()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile6()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile7()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile8()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile9()).size() > 0);
        assertTrue(fileCsvParser.newReportFromCsvFile(fileCsvParser.getFile10()).size() > 0);
    }

    // создать отчет по всем пользователям, которые ходили в vk.com - пока без сортировки и суммирования
    @Test
    public void testGetVkUsersFromDB() {
        assertNotNull(reportService.getReportVkontakteList().size);
    }

}
