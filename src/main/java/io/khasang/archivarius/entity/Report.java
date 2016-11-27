package io.khasang.archivarius.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Report {

    @Id
    @GeneratedValue()
    private int id;
    private String site;
    private String nameUser;
    private int timeInSecond;

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

    public Report() {
    }
}
