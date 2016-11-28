package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Report;

import java.util.List;

public interface ReportDAO {
    void addReport(Report report);

    void updateReport(Report report);

    void deleteReport(Report report);

    Report getReportById(int id);

    List<Report> getReportList();

    List<Report> getVkontakteReportList();
}
