package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        Root<Company> game = query.from(Company.class);
        query.where(builder.equal(game.get("id"), builder.parameter(Integer.class, "id")));
        Query<Company> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
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
    public List<Company> getCompanyList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Company> criteriaQuery = builder.createQuery(Company.class);
        Root<Company> company = criteriaQuery.from(Company.class);
        criteriaQuery.select(company);
        Query<Company> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
