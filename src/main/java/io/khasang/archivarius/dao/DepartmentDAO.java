package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Department;
import java.util.List;

public interface DepartmentDAO {
    void addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    Department getDepartmentById(int id);

    List<Department> getDepartmentList();
}
