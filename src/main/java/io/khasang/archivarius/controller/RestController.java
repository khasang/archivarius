package io.khasang.archivarius.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/company")
public class RestController {
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; encoding=UTF-8")
    @ResponseBody
    public Object getQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int questionId = Integer.valueOf(inputId);
            Company company = companyService.getCompanyById(questionId);
            if (company != null) {
                return company;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "User with id: " + questionId + " not found.";
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

            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(response.toString());

            while (!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();

                System.out.println("jsonToken = " + jsonToken);
            }

            return company;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding question: " + e.getMessage();
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
                return "Company #" + companyId + " successfully deleted.";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Company with id: " + companyId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad company id format: " + inputId;
        }
    }
}
