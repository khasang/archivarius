package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addCompany(Company company){
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    public void updateCompany(Company company) {
        // saving old password if do not provided new
        final String query = "SELECT name from COMPANY WHERE id=:id";
        String newName = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", company.getId()).
                uniqueResult();
        company.setName(newName);
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(company);
        session.flush();
    }

    @Override
    public Company getCompanyById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("id", id));
        return (Company) criteria.uniqueResult();
    }

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> getCompanyList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        return (List<Company>) criteria.list();
    }
}
