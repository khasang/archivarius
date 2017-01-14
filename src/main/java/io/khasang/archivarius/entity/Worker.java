package io.khasang.archivarius.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Worker {

    @Id
    @GeneratedValue
    private int id;

    private String lastName;

    private String firstAndMiddleName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "DEPARTMENT_ID"))
    private Department department;

    private String position;

    private boolean companyWorker;

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstAndMiddleName() {
        return firstAndMiddleName;
    }

    public void setFirstAndMiddleName(String firstAndMiddleName) {
        this.firstAndMiddleName = firstAndMiddleName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isCompanyWorker() {
        return companyWorker;
    }

    public void setCompanyWorker(boolean companyWorker) {
        this.companyWorker = companyWorker;
    }
}
