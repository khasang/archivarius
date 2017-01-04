package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.UserDAO;
import io.khasang.archivarius.entity.User;
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
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addUser(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
    }

    @Override
    public void deleteUser(User user) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(user);
        session.flush();
    }

    @Override
    public void deleteUserById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        User user = getUserById(id);
        session.delete(user);
        session.flush();
    }

    @Override
    public User getUserById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<User> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("login"), builder.parameter(Integer.class, "login")));
        Query<User> myQuery = session.createQuery(query);
        myQuery.setParameter("login", name);
        return myQuery.getSingleResult();
    }

    @Override
    public List<User> getUserList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.orderBy(builder.asc(root.get("login")));
        criteriaQuery.select(root);
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
