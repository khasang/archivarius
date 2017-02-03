package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.repository.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested Department";
    @InjectMocks
    DepartmentService departmentService = new DepartmentService();

    @Mock
    DepartmentRepository departmentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDepartmentByIdTest() {
        Department departmentToSave = new Department();
        departmentToSave.setName(NAME);
        departmentToSave.setId(ID);
        when(departmentRepository.findOne(ID)).thenReturn(departmentToSave);
        Department departmentById = departmentService.getDepartmentById(ID);
        assertEquals(NAME, departmentById.getName());
    }

    @Test
    public void addDepartmentTestSize() {
        List<Department> departments = new ArrayList<>();
        Department departmentToSave = new Department();
        departmentToSave.setName(NAME);
        departmentToSave.setId(ID);
        departments.add(departmentToSave);

        when(departmentRepository.findAll()).thenReturn(departments);
        assertEquals(1, departmentService.getDepartmentList().size());
    }

}
