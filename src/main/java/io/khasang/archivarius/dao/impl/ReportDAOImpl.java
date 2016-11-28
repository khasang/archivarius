package io.khasang.archivarius.dao.impl;

import io.khasang.archivarius.dao.ReportDAO;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Report;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDAOImpl implements ReportDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addReport(Report report) {
        sessionFactory.getCurrentSession().save(report);
    }

    @Override
    public void updateReport(Report report) {
        final String query = "SELECT name from REPORT WHERE id=:id";
        String newName = (String) sessionFactory.
                getCurrentSession().
                createSQLQuery(query).
                setParameter("id", report.getId()).
                uniqueResult();
        report.setNameUser(newName);
        sessionFactory.getCurrentSession().update(report);
    }

    @Override
    public void deleteReport(Report report) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(report);
        session.flush();
    }

    @Override
    public Report getReportById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("id", id));
        return (Report) criteria.uniqueResult();
    }

    @Override
    public List<Report> getReportList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Report.class);
        return (List<Report>) criteria.list();
    }

    @Override
    public List<Report> getVkontakteReportList() {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Report.class)
                .add(Restrictions.like("site", "%vk.com%"))
                .setProjection( Projections.projectionList()
                        .add(Projections.sum("timeInSecond"), "sumvk")
                        .add(Projections.groupProperty("nameUser"))
                )
                .addOrder(Order.desc("sumvk"));

        return (List<Report>) criteria.list();
    }
}
