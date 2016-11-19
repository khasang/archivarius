package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.BooksDAO;
import io.khasang.archivarius.entity.Books;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksDAOImpl implements BooksDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addBooks(Books books){
        sessionFactory.getCurrentSession().save(books);
    }

    @Override
    public void updateBooks(Books books) {
        // saving old password if do not provided new
        final String query = "SELECT title from BOOKS WHERE id=:id";
        String newTitle = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", books.getId()).
                uniqueResult();
        books.setTitle(newTitle);
        sessionFactory.getCurrentSession().update(books);
    }

    @Override
    public void deleteBooks(Books books) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(books);
        session.flush();
    }

    @Override
    public Books getBooksById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Books.class);
        criteria.add(Restrictions.eq("id", id));
        return (Books) criteria.uniqueResult();
    }

    @Override
    public Books getBooksByTitle(String title) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Books.class);
        criteria.add(Restrictions.eq("title", title));
        return (Books) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Books> getBooksList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Books.class);
        return (List<Books>) criteria.list();
    }
}
