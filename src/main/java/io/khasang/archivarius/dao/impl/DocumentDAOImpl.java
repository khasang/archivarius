package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.entity.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public void updateDocument(Document document) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(document);
        session.flush();
    }

    @Override
    public void deleteDocument(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Document document = session.load(Document.class, new Integer(id));
        if (document != null) {
            session.delete(document);
            session.flush();
        }
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



