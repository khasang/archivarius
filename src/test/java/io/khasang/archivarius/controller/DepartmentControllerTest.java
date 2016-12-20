package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Department> deps = new ArrayList<>();
        deps.add(new Department());
        deps.add(new Department());

        when(departmentService.getDepartmentList()).thenReturn((List) deps);

        mockMvc.perform(get("/departmentList"))
                .andExpect(status().isOk())
                .andExpect(view().name("departmentList"))
                .andExpect(model().attribute("deps", hasSize(2)));
    }

}