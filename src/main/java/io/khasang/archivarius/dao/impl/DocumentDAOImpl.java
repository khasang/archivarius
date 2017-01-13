package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.entity.DocKey;
import io.khasang.archivarius.entity.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
    @Autowired
    SessionFactory sessionFactory;

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
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Document> query = builder.createQuery(Document.class);
        Root<Document> root = query.from(Document.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<Document> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    public List<Document> getDocumentList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Document> criteriaQuery = builder.createQuery(Document.class);
        Root<Document> root = criteriaQuery.from(Document.class);
        criteriaQuery.orderBy(builder.asc(root.get("id")));
        criteriaQuery.select(root);
        Query<Document> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Document> getDocKeyList(DocKey docKey) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class)
                .add(Restrictions.eq("docKey", docKey));
        return (List<Document>) criteria.list();
    }
}



