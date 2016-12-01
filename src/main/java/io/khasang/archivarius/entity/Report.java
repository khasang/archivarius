package io.khasang.archivarius.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Report is class for our computer logs.
 * It will be feature for incoming documents
 */
@Entity
public class Report {

    @Id
    @GeneratedValue()
    private int id;
    private String site;
    private String nameUser;
    private int timeInSecond;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getTimeInSecond() {
        return timeInSecond;
    }

    public void setTimeInSecond(int timeInSecond) {
        this.timeInSecond = timeInSecond;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", site='" + site + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", timeInSecond=" + timeInSecond +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
