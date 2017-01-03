package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.RoleDAO;
import io.khasang.archivarius.entity.Role;
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
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<Role> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    public List<Role> getRoleList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.orderBy(builder.asc(root.get("name")));
        criteriaQuery.select(root);
        criteriaQuery.distinct(true);
        Query<Role> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
