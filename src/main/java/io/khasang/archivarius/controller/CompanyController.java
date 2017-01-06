package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @GetMapping("/")
    public String companyList(Model model) {
        model.addAttribute("companies", companyService.getCompanyList());
        return "lists/companies";
    }

    @GetMapping(value = {"/{id}"})
    public String companyGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("company", companyService.getCompanyById(intId));
        return "lists/company";
    }

    @GetMapping({"/{id}/edit"})
    public ModelAndView companyForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        Company company = companyService.getCompanyById(intId);
        company.setId(company.getId());
        company.setName(company.getName());
        company.setAddress(company.getAddress());
        company.setInnNumber(company.getInnNumber());
        return new ModelAndView("forms/company", "company", company);
    }

    @GetMapping("/add")
    public ModelAndView showForm() {
        return new ModelAndView("forms/company", "company", new Company());
    }

    @PostMapping("/")
    public String submit(@ModelAttribute("company")Company company,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        companyService.updateCompany(company);
        return "forms/success";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam int id) {
        companyService.deleteCompanyById(id);
        return "redirect:/company/";
    }
}
