package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.service.CompanyService;
import io.khasang.archivarius.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    CompanyService companyService;

    private static final Logger log = Logger.getLogger(DepartmentController.class);

    @GetMapping("/")
    public String departmentList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("departments", departmentService.getDepartmentList());
        model.addAttribute("message", message);
        return "lists/departments";
    }

    @GetMapping({"/{id}"})
    public String departmentGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("department", departmentService.getDepartmentById(intId));
        return "lists/department";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping({"/{id}/edit"})
    public String departmentForm(@PathVariable("id") String id, ModelMap model) {
        Department department = departmentService.getDepartmentById(Integer.valueOf(id));
        model.addAttribute("companies", companyService.getCompanyList());
        model.addAttribute("department", department);
        return "forms/department";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping("/add")
    public String showDepartmentForm(ModelMap model) {
        model.addAttribute("companies", companyService.getCompanyList());
        model.addAttribute("department", new Department());
        return "forms/department";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value = "/", params = {"save"})
    public String submit(@ModelAttribute("department")Department department,
                         BindingResult result, ModelMap model,
                         final RedirectAttributes redirectAttributes) {
        Company company = companyService.getCompanyById(Integer.valueOf((String)(result.getFieldValue("company"))));
        department.setCompany(company);
        departmentService.updateDepartment(department);
        model.addAttribute("name", department.getName());
        model.addAttribute("company", department.getCompany());
        redirectAttributes.addAttribute("message", "Подразделение " + department.getName() + " создано");
        return "redirect:/department/";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value="/", params = {"delete"})
    public String delete(final Department department, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Подразделение " + department.getName() + " удалено");
        departmentService.deleteDepartment(department);
        return "redirect:/department/";
    }
}
