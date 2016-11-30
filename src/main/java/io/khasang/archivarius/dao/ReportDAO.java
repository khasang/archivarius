package io.khasang.archivarius.dao;

import io.khasang.archivarius.entity.Report;
import java.util.List;

/**
 * This is a way for our reports
 * TODO: may be our interface must be devided into main methods and other for more specialized work
 */
public interface ReportDAO {
    void addReport(Report report);

    void updateReport(Report report);

    void deleteReport(Report report);

    Report getReportById(int id);

    List<Report> getReportList();

    List<Report> getVkontakteReportList();
}
