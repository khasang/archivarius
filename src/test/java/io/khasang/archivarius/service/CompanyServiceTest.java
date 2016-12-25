package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.entity.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {
    private static final int ID = 12345;
    private static final String NAME = "Tested Company";
    @InjectMocks
    CompanyService companyService = new CompanyService();

    @Mock
    CompanyDAO companyDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCompanyByIdTest() {
        Company companyToSave = new Company();
        companyToSave.setName(NAME);
        companyToSave.setId(ID);
        when(companyDAO.getCompanyById(ID)).thenReturn(companyToSave);
        Company companyById = companyService.getCompanyById(ID);
        assertEquals(NAME, companyById.getName());
    }

    @Test
    public void addCompanyTestSize() {
        List<Company> companies = new ArrayList<>();
        Company companyToSave = new Company();
        companyToSave.setName(NAME);
        companyToSave.setId(ID);
        companies.add(companyToSave);

        when(companyDAO.getCompanyList()).thenReturn(companies);
        assertEquals(1, companyService.getCompanyList().size());
    }
}
