package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.service.CompanyService;
import io.khasang.archivarius.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    CompanyService companyService;

    private static final Logger log = Logger.getLogger(DepartmentController.class);

    @GetMapping("/")
    public String departmentList(Model model) {
        model.addAttribute("departments", departmentService.getDepartmentList());
        return "lists/departments";
    }

    @GetMapping({"/{id}"})
    public String departmentGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("department", departmentService.getDepartmentById(intId));
        return "lists/department";
    }

    @GetMapping({"/{id}/edit"})
    public String departmentForm(@PathVariable("id") String id, ModelMap model) {
        Department department = departmentService.getDepartmentById(Integer.valueOf(id));
        model.addAttribute("companies", companyService.getCompanyList());
        model.addAttribute("department", department);
        return "forms/department";
    }

    @GetMapping("/add")
    public String showDepartmentForm(ModelMap model) {
        model.addAttribute("companies", companyService.getCompanyList());
        model.addAttribute("department", new Department());
        return "forms/department";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute("department")Department department,
                         BindingResult result, ModelMap model) {
        Company company = companyService.getCompanyById(Integer.valueOf((String)(result.getFieldValue("company"))));
        department.setCompany(company);
        departmentService.updateDepartment(department);
        model.addAttribute("name", department.getName());
        model.addAttribute("company", department.getCompany());
        return "forms/success";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam int id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/department/";
    }

    public Map<Integer, String> getDropboxList() {
        List<Company> companiesList = companyService.getCompanyList();
        Map<Integer, String> companies = new HashMap<>();
        for(Company comp: companiesList) {
            companies.put(comp.getId(), comp.getName());
        }
        return companies;
    }
}
