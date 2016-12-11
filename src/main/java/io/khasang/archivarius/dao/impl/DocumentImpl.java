package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocumentDAO;
import io.khasang.archivarius.dao.DocumentLifeCycleDAO;
import io.khasang.archivarius.entity.Document;
import io.khasang.archivarius.entity.DocumentLifeCycle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentImpl implements DocumentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Document getDocumentById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Document.class);
        criteria.add(Restrictions.eq("id", id));
        return (Document) criteria.uniqueResult();
    }

    @Override
    public List<Document> getDocumentList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(DocumentLifeCycle.class);
        return (List<Document>) criteria.list();
    }
}
