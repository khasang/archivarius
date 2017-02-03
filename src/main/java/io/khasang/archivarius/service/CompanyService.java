package io.khasang.archivarius.service;

import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("CompanyService")
@Transactional
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompanyById(int id) {
        return companyRepository.findOne(id);
    }

    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

}
