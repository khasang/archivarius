package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployeeById(long id);

    Employee getEmployeeByName(String name);

    List<Employee> getEmployesList();

}
