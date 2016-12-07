package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.DepartmentDAO;
import io.khasang.archivarius.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("DepartmentService")
@Transactional
public class DepartmentService {

    DepartmentDAO departmentDAO;

    public void addDepartment(Department department) {
        departmentDAO.addDepartment(department);
    }

    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    public List<Department> getDepartmentList() {
        return departmentDAO.getDepartmentList();
    }

    public void updateDepartment(Department department) {
        departmentDAO.updateDepartment(department);
    }

    public void deleteDepartment(Department department) {
        departmentDAO.deleteDepartment(department);
    }
}
