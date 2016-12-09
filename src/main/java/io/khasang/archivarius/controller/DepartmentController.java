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

    @RequestMapping("/")
    public String departmentList(Model model) {
        model.addAttribute("departmentList", departmentService.getDepartmentList());
        return "departmentList";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String departmentGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("departmentGetId", departmentService.getDepartmentById(intId));
        return "departmentGetId";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public String departmentForm(@PathVariable("id") String id, ModelMap model) {
        List<Company> companies = companyService.getCompanyList();

        Integer intId = Integer.valueOf(id);
        Department department = departmentService.getDepartmentById(intId);
//        department.setId(department.getId());
//        department.setName(department.getName());
//        department.setCompany(department.getCompany());
        model.addAttribute("companies", companies);
        model.addAttribute("department", department);
        return "departmentForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showDepartmentForm(ModelMap model) {
        List<Company> companies = companyService.getCompanyList();
        Department department = new Department();
        model.addAttribute("companies", companies);
        model.addAttribute("department", department);
        return "departmentForm";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("department")Department department,
                         BindingResult result, ModelMap model) {
        Company company;
        if("NONE".equals(result.getFieldValue("company"))) {
            company = null;
        } else {
            company = companyService.getCompanyById(Integer.valueOf((String)(result.getFieldValue("company"))));
        }
        department.setCompany(company);
        departmentService.updateDepartment(department);
        model.addAttribute("name", department.getName());
        model.addAttribute("company", department.getCompany());
        return "resultDepartmentFormEdit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "delete" })
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/department/";
    }
}
