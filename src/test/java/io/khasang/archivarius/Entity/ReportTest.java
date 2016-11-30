package io.khasang.archivarius.Entity;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import io.khasang.archivarius.entity.Report;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class ReportTest {
    /**
     * Make table 'Report' in our database
     */
    @Test
    public void testEntityIsAlive() {
        assertNotNull(new Report());
    }
}

