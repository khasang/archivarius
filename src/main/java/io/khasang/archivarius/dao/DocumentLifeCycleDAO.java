package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Company;

import java.util.List;

public interface CompanyDAO {
    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(Company company);

    void deleteCompanyById(int id);

    Company getCompanyById(int id);

    Company getCompanyByName(String name);

    List<Company> getCompanyList();
}
