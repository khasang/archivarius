package io.khasang.archivarius.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/company")
public class RestController {
    private static final Logger log = Logger.getLogger(RestController.class);
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object getQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int companyId = Integer.valueOf(inputId);
            Company company = companyService.getCompanyById(companyId);
            if (company != null) {
                return company;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Company with id: " + companyId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad user id format: " + inputId;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Company> getAllQuestion() {
        return companyService.getCompanyList();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addQuestion(@RequestBody Company company, HttpServletResponse response) {
        try {
            companyService.addCompany(company);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return "{\"id\": " + company.getId() + ", \"name\": \"" + company.getName() + "\"}";
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            return "Company not added " + company + e;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object updateQuestion(@RequestBody Company company, HttpServletResponse response) {
        try {
            companyService.updateCompany(company);
            return company;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating user: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int companyId = Integer.valueOf(inputId);
            Company company = companyService.getCompanyById(companyId);
            if (company != null) {
                companyService.deleteCompany(company);
                return "{\"id\": null, \"name\": null}";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "{\"id\": " + company.getId() + ", \"name\": \"not found this id\"}";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad company id format: " + inputId;
        }
    }
}
