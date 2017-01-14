package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.WorkerDAO;
import io.khasang.archivarius.entity.Worker;
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
public class WorkerDAOImpl implements WorkerDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addWorker(Worker worker){
        sessionFactory.getCurrentSession().save(worker);
    }

    @Override
    public void updateWorker(Worker worker) {
        final Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(worker);
        session.flush();
    }

    @Override
    public void deleteWorker(Worker worker) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(worker);
        session.flush();
    }

    @Override
    public void deleteWorkerById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        Worker worker = getWorkerById(id);
        session.delete(worker);
        session.flush();
    }

    @Override
    public Worker getWorkerById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> query = builder.createQuery(Worker.class);
        Root<Worker> root = query.from(Worker.class);
        query.where(builder.equal(root.get("id"), builder.parameter(Integer.class, "id")));
        Query<Worker> myQuery = session.createQuery(query);
        myQuery.setParameter("id", id);
        return myQuery.getSingleResult();
    }

    @Override
    public List<Worker> getWorkerList() {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> criteriaQuery = builder.createQuery(Worker.class);
        Root<Worker> root = criteriaQuery.from(Worker.class);
        criteriaQuery.orderBy(builder.asc(root.get("lastName")));
        criteriaQuery.orderBy(builder.asc(root.get("firstAndMiddleName")));
        criteriaQuery.select(root);
        Query<Worker> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
