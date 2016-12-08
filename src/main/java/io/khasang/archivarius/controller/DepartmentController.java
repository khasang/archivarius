package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Department;
import io.khasang.archivarius.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
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
    public ModelAndView departmentForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        Department department = departmentService.getDepartmentById(intId);
        department.setId(department.getId());
        department.setName(department.getName());
        return new ModelAndView("departmentForm", "department", department);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showDepartmentForm() {
        return new ModelAndView("departmentForm", "department", new Department());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("department")Department department,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        departmentService.updateDepartment(department);
        model.addAttribute("name", department.getName());
        return "resultDepartmentFormEdit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "delete" })
    public String deny(@RequestParam int id, @RequestParam String delete, Model model) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/department/";
    }
}
