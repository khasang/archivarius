package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DepartmentDAO;
import io.khasang.archivarius.entity.Department;
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
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDepartment(Department department) {
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(department);
        session.flush();
    }

    @Override
    public void deleteDepartment(Department department) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(department);
        session.flush();
    }

    @Override
    public void deleteDepartmentById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Department department = getDepartmentById(id);
        session.delete(department);
        session.flush();
    }

    @Override
    public Department getDepartmentById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Department.class);
        criteria.add(Restrictions.eq("id", id));
        return (Department) criteria.uniqueResult();
    }

    @Override
    public List<Department> getDepartmentList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);
        Root<Department> root = criteriaQuery.from(Department.class);
        criteriaQuery.orderBy(builder.asc(root.get("name")));
        criteriaQuery.select(root);
        Query<Department> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
