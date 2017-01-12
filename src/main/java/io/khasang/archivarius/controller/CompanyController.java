package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @GetMapping("/")
    public String companyList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("companies", companyService.getCompanyList());
        model.addAttribute("message", message);
        return "lists/companies";
    }

    @GetMapping(value = {"/{id}"})
    public String companyGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("company", companyService.getCompanyById(intId));
        return "lists/company";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @GetMapping("/add")
    public ModelAndView showForm() {
        return new ModelAndView("forms/company", "company", new Company());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value = "/", params = {"save"})
    public String submit(@ModelAttribute("company")Company company,
                         BindingResult result, ModelMap model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "error";
        }
        redirectAttributes.addFlashAttribute("message", "Организация " + company.getName() + " создана");
        companyService.updateCompany(company);
        return "redirect:/company/";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCUMENTOVED')")
    @PostMapping(value="/", params = {"delete"})
    public String delete(final Company company, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Организация " + company.getName() + " удалена");
        companyService.deleteCompany(company);
        return "redirect:/company/";
    }
}
