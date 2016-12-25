package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.RoleDAO;
import io.khasang.archivarius.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addRole(Role role){
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void updateRole(Role role) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);
        session.flush();
    }

    @Override
    public void deleteRole(Role role) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(role);
        session.flush();
    }

    @Override
    public void deleteRoleById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Role role = getRoleById(id);
        session.delete(role);
        session.flush();
    }

    @Override
    public Role getRoleById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", id));
        return (Role) criteria.uniqueResult();
    }

    @Override
    public Role getRoleByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Role.class);
        criteria.add(Restrictions.eq("name", name));
        return (Role) criteria.uniqueResult();
    }

    @Override
    public List<Role> getRoleList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Role.class);
        return (List<Role>) criteria.list();
    }
}
