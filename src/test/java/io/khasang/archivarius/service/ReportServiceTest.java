package io.khasang.archivarius.service;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.io.File;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class ReportServiceTest {
    @Autowired
    ReportService reportService;
    @Autowired
    FileCsvParser fileCsvParser;

    /**
     * Parser test. It loads strings into database
     */
    @Test
    public void testAddReport() {
        for(File file: fileCsvParser.files) {
            assertTrue(fileCsvParser.newReportFromCsvFile(file).size() > 0);
        }
    }

    /**
     * Report about users, who spent time in vkontakte
     */
    @Test
    public void testGetVkUsersFromDB() {
        Assert.assertNotNull(reportService.getReportVkontakteList());
    }

}
