package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(company);
        session.flush();
    }

    @Override
    public void deleteCompany(Company company) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(company);
        session.flush();
    }

    @Override
    public void deleteCompanyById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Company company = getCompanyById(id);
        session.delete(company);
        session.flush();
    }

    @Override
    public Company getCompanyById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);
        Root<Company> root = query.from(Company.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<Company> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    public List<Company> getCompanyList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Company> criteriaQuery = builder.createQuery(Company.class);
        Root<Company> root = criteriaQuery.from(Company.class);
        criteriaQuery.orderBy(builder.asc(root.get("name")));
        criteriaQuery.select(root);
        Query<Company> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
