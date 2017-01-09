package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DepartmentDAO;
import io.khasang.archivarius.entity.Department;
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
    DepartmentDAO departmentDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDepartmentByIdTest() {
        Department departmentToSave = new Department();
        departmentToSave.setName(NAME);
        departmentToSave.setId(ID);
        when(departmentDAO.getDepartmentById(ID)).thenReturn(departmentToSave);
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

        when(departmentDAO.getDepartmentList()).thenReturn(departments);
        assertEquals(1, departmentService.getDepartmentList().size());
    }

}
