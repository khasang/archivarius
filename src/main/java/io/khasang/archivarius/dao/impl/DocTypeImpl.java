package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.DocTypeDAO;
import io.khasang.archivarius.entity.DocType;
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
public class DocTypeImpl implements DocTypeDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDocType(DocType docType) {
        sessionFactory.getCurrentSession().save(docType);
    }

    @Override
    public void updateDocType(DocType docType) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(docType);
        session.flush();
    }

    @Override
    public void deleteDocType(int id) {
        final Session session = sessionFactory.getCurrentSession();
        DocType docType = session.load(DocType.class, new Integer(id));
        if (docType != null) {
            session.delete(docType);
            session.flush();
        }
    }

    @Override
    public DocType getDocTypeById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocType> query = builder.createQuery(DocType.class);
        Root<DocType> root = query.from(DocType.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<DocType> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DocType> getDocTypeList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = builder.createQuery(DocType.class);
        Root<DocType> root = criteriaQuery.from(DocType.class);
        criteriaQuery.orderBy(builder.asc(root.get("name")));
        criteriaQuery.select(root);
        Query<DocType> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
