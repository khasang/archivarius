package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("DepartmentService")
@Transactional
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public Department getDepartmentById(int id) {
        return departmentRepository.findOne(id);
    }

    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }

    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }
}
