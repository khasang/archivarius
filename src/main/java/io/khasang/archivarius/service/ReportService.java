package io.khasang.archivarius.service;

import io.khasang.archivarius.dao.CompanyDAO;
import io.khasang.archivarius.dao.ReportDAO;
import io.khasang.archivarius.entity.Company;
import io.khasang.archivarius.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ReportService {
    @Autowired
    ReportDAO reportDAO;

    public void addReport(Report report) {
        reportDAO.addReport(report);
    }

    public Report getReportById(int id) {
        return reportDAO.getReportById(id);
    }

    public List<Report> getReportList() {
        return reportDAO.getReportList();
    }

    public void updateReport(Report report) {
        reportDAO.updateReport(report);
    }

    public void deleteReport(Report report) {
        reportDAO.deleteReport(report);
    }

    public List<Report> getReportVkontakteList() {
        return reportDAO.getVkontakteReportList();
    }
}
