package io.khasang.archivarius.controller;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.model.DatabaseBackup;
import io.khasang.archivarius.model.Message;
import io.khasang.archivarius.model.QueryExample;
import io.khasang.archivarius.service.CompanyService;
import io.khasang.archivarius.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    private static final Logger log = Logger.getLogger(CompanyController.class);

    @RequestMapping("/")
    public String companyList(Model model) {
        model.addAttribute("companyList", companyService.getCompanyList());
        return "companyList";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String companyGetId(@PathVariable("id") String id, ModelMap model) {
        Integer intId = Integer.valueOf(id);
        model.addAttribute("companygetId", companyService.getCompanyById(intId));
        return "companygetId";
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView companyForm(@PathVariable("id") String id) {
        Integer intId = Integer.valueOf(id);
        Company company = companyService.getCompanyById(intId);
        company.setId(company.getId());
        company.setName(company.getName());
        company.setAddress(company.getAddress());
        company.setInnNumber(company.getInnNumber());
        return new ModelAndView("companyForm", "company", company);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("companyForm", "company", new Company());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("company")Company company,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        companyService.updateCompany(company);
        model.addAttribute("name", company.getName());
        model.addAttribute("innNumber", company.getInnNumber());
        model.addAttribute("address", company.getAddress());
        return "resultCompanyFormEdit";
    }
}
