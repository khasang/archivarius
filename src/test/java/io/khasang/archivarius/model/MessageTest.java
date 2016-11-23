package io.khasang.archivarius.model;

import io.khasang.archivarius.config.AppConfig;
import io.khasang.archivarius.config.HibernateConfig;
import io.khasang.archivarius.config.application.WebConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class MessageTest {
    @Autowired
    Message message;

    private static int count = 0;
    public String getNumberCount(int day) {
        if (day > 0 && day < 28) {
            return "feb";
        } else if (day > 28 && day < 30) {
            return "apr";
        } else if (day == 31) {
            return "may";
        } else return "wrong month";
    }

    @Before
    public void count(){
        count++;
    }

    @After
    public void countMin(){
        count--;
    }

    @Test(timeout = 100)
    public void testGetHelloMessage() {
        assertEquals("Что то пошло не так", getNumberCount(2), "feb");
        assertEquals("Что то пошло не так", getNumberCount(29), "apr");
        assertEquals("Что то пошло не так", getNumberCount(31), "may");
        assertEquals("Что то пошло не так", getNumberCount(0), "wrong month");
        assertEquals("Что то пошло не так", getNumberCount(211), "wrong month");
    }

    @Test
    public void testSomething(){
        assertEquals("Welcome to archivarius app!", message.getHelloMessage());
    }
}
