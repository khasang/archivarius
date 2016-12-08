package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DepartmentDAO;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Department.class);
        criteria.add(Restrictions.eq("id", id));
        return (Department) criteria.uniqueResult();
    }

    @Override
    public List<Department> getDepartmentList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Department.class);
        return (List<Department>) criteria.list();
    }
}
