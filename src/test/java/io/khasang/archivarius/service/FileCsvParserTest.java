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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})

public class FileCsvParserTest {
    @Autowired
    FileCsvParser fileCsvParser;
    @Ignore
    @Test
    public void testEntityIsAlive(){
//        Assert.assertNotNull(new Report());
    }

    @Test
    public void testFilesExist(){
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile1()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile2()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile3()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile4()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile5()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile6()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile7()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile8()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile9()));
        Assert.assertEquals(true, fileCsvParser.checkFiles(fileCsvParser.getFile10()));
            }

    @Test
    public void testContentsFile() {
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile1()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile2()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile3()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile4()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile5()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile6()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile7()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile8()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile9()));
        Assert.assertEquals(true, fileCsvParser.countNumderRows(fileCsvParser.getFile10()));

    }
    @Ignore
    @Test
    public void testSomeOneAtVk(){
//        Assert.assertEquals(true, fileCheckService.someoneAtVk());
    }
}
