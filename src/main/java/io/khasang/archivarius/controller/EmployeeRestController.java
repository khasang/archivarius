package io.khasang.archivarius.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.khasang.archivarius.entity.Employee;
import io.khasang.archivarius.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/staff")
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;
    public static final Logger logger = Logger.getLogger(EmployeeRestController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object getOneEmployee(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int employeeId = Integer.valueOf(inputId);
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee != null) {
                logger.debug("Found employee by id " + employeeId + ": " + employee);
                return employee;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                final String message = "Employee not found by Id " + employeeId;
                logger.info(message);
                return message;
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            final String message = "Bad employee id format: " + inputId;
            logger.error(message);
            return message;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        List<Employee> employeesList = employeeService.getEmployeeList();
        logger.debug("Found " + employeesList.size() + " employees");
        return employeeService.getEmployeeList();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object addEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        try {
            employeeService.addEmployee(employee);

            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(response.toString());

            while (!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();
                logger.debug("jsonToken = " + jsonToken);
            }
            logger.debug("Add new employee: " + employee);
            return employee;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            final String message = "Error adding employee: " + e.getMessage();
            logger.error(message);
            return message;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object updateEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        try {
            employeeService.updateEmployee(employee);
            logger.debug("Updated employee: " + employee);
            return employee;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            final String message = "Error updating employee: " + e.getMessage();
            logger.error(message);
            return message;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public String deleteEmployee(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int employeeId = Integer.valueOf(inputId);
            Employee employee = employeeService.getEmployeeById(employeeId);
            if (employee != null) {
                employeeService.deleteEmployee(employee);
                final String message = "Employee #" + employeeId + "successfully deleted";
                logger.debug(message);
                return message;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                final String message = "Employee withId " + employeeId + "not found";
                logger.info(message);
                return message;
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            final String message = "Bad employee id format: " + inputId;
            logger.error(message);
            return message;
        }
    }
}
