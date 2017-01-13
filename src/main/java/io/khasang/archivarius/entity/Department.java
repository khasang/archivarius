package io.khasang.archivarius.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id",
            foreignKey = @ForeignKey(name = "COMPANY_ID"))
    private Company company;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "worker_id",
            foreignKey = @ForeignKey(name = "WORKER_ID"))
    private Worker director;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phoneNumbers;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Worker getDirector() {
        return director;
    }

    public void setDirector(Worker director) {
        this.director = director;
    }

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", director=" + director +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
