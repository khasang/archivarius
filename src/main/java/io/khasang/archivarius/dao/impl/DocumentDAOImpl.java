package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.entity.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDocument(Document document) {
        sessionFactory.getCurrentSession().save(document);
    }

    @Override
    public void updateDocumentTitle(Document document) {
        final String query = "SELECT title from Document WHERE id=:id";
        String newTitle = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", document.getId()).
                uniqueResult();
        document.setTitle(newTitle);
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void updateDocumentStatus(Document document) {
        final String query = "SELECT status from Document WHERE id=:id";
        String newStatus = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", document.getId()).
                uniqueResult();
        document.setStatus(newStatus);
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void updateDocumentDeadline(Document document) {
        final String query = "SELECT deadline from Document WHERE id=:id";
        Date newDeadline = (Date) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", document.getId()).
                uniqueResult();
        document.setDeadline(newDeadline);
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void updateDocumentDestination(Document document) {
        final String query = "SELECT destination from Document WHERE id=:id";
        String newDestination = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", document.getId()).
                uniqueResult();
        document.setDestination(newDestination);
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void deleteDocument(Document document) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(document);
        session.flush();
    }

    @Override
    public Document getDocumentById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class);
        criteria.add(Restrictions.eq("id", id));
        return (Document) criteria.uniqueResult();
    }

    @Override
    public Document getDocumentByAuthor(String author) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class);
        criteria.add(Restrictions.eq("author", author));
        return (Document) criteria.uniqueResult();
    }

    @Override
    public Document getDocumentByDestination(String destination) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class);
        criteria.add(Restrictions.eq("destination", destination));
        return (Document) criteria.uniqueResult();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> getDocumentList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class);
        return (List<Document>) criteria.list();
    }
}



