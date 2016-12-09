package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("CompanyService")
@Transactional
public class CompanyService {
    @Autowired
    CompanyDAO companyDAO;

    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    public Company getCompanyById(int id) {
        return companyDAO.getCompanyById(id);
    }

    public List<Company> getCompanyList() {
        return companyDAO.getCompanyList();
    }

    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    public void deleteCompany(Company company) {
        companyDAO.deleteCompany(company);
    }

    public void deleteCompanyById(int id) {
        companyDAO.deleteCompanyById(id);
    }
}
