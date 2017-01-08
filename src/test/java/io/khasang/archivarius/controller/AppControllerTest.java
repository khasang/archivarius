//package io.khasang.archivarius.controller;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AppControllerTest {
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    AppController controller;
//
//
//    @Test
//    public void homeTest() throws Exception {
//        mockMvc.perform(get("/inbox"))
//                .andDo(print())
//                .andExpect(status().isOk())
//        .andExpect(content().string(containsString("</head>")));
//    }
//}