package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocumentLifeCycleDAO;
import io.khasang.archivarius.entity.DocumentLifeCycle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentLifeCycleImpl implements DocumentLifeCycleDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void updateDocumentLifeCycle(DocumentLifeCycle documentLifeCycle) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(documentLifeCycle);
        session.flush();
    }

    @Override
    public void deleteDocumentLifeCycle(DocumentLifeCycle documentLifeCycle) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(documentLifeCycle);
        session.flush();
    }

    @Override
    public void deleteDocumentLifeCycleById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        DocumentLifeCycle documentLifeCycle = getDocumentLifeCycleById(id);
        session.delete(documentLifeCycle);
        session.flush();
    }

    @Override
    public DocumentLifeCycle getDocumentLifeCycleById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(DocumentLifeCycle.class);
        criteria.add(Restrictions.eq("id", id));
        return (DocumentLifeCycle) criteria.uniqueResult();
    }

    @Override
    public List<DocumentLifeCycle> getDocumentLifeCycleList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(DocumentLifeCycle.class);
        return (List<DocumentLifeCycle>) criteria.list();
    }
}
