package io.khasang.archivarius.dao.impl;

import org.springframework.stereotype.Repository;
import io.khasang.archivarius.dao.DocTypeDAO;
import io.khasang.archivarius.entity.DocType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(DocType.class);
        criteria.add(Restrictions.eq("id", id));
        return (DocType) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DocType> getDocTypeList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(DocType.class);
        return (List<DocType>) criteria.list();
    }
}
