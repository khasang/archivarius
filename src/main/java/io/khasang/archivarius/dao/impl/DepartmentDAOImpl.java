package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DepartmentDAO;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDepartment(Department department) {
        sessionFactory.getCurrentSession().save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        final String query = "SELECT name from DEPARTMENT WHERE id=:id";
        String newName = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", department.getId()).
                uniqueResult();
        department.setName(newName);
        sessionFactory.getCurrentSession().update(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(department);
        session.flush();
    }

    @Override
    public Department getDepartmentById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("id", id));
        return (Department) criteria.uniqueResult();
    }

    @Override
    public List<Department> getDepartmentList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        return (List<Department>) criteria.list();
    }
}
