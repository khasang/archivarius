//package io.khasang.archivarius.controller;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class DepartmentControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
////    @Before
//    public void setup() {
////        mvc = MockMvcBuilders
////                .webAppContextSetup(context)
////                .defaultRequest(get("/").with(user("user").roles("ADMIN")))
////                .apply(springSecurity())
////                .build();
////    }
//
//
////}