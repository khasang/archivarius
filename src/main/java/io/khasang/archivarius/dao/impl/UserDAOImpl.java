package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.UserDAO;
import io.khasang.archivarius.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User getUserByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("login", name));
        return (User) criteria.uniqueResult();
    }

    @Override
    public List<User> getUserList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        return (List<User>) criteria.list();
    }
}
