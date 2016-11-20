package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.EmployeeDAO;
import io.khasang.archivarius.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("EmployeeService")
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    public Employee getEmployeeById(long id) {
        return employeeDAO.getEmployeeById(id);
    }

    public Employee getEmplyeeByName(String name) {
        return employeeDAO.getEmployeeByName(name);
    }

    public List<Employee> getEmployeeList() {
        return employeeDAO.getEmployesList();
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDAO.deleteEmployee(employee);
    }
}
