package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.EmployeeDAO;
import io.khasang.archivarius.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        final String sqlQuery = "SELECT fullname FROM employee WHERE id=:id";
        String newFullName = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(sqlQuery).
                setParameter("id", employee.getId()).
                uniqueResult();
        employee.setFullName(newFullName);
        sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(employee);
        currentSession.flush();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", id));
        return (Employee) criteria.uniqueResult();
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Employee.class);
        criteria.add(Restrictions.eq("fullName", name));
        return (Employee) criteria.uniqueResult();
    }

    @Override
    public List<Employee> getEmployesList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }
}
