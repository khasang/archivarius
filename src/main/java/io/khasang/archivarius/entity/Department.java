package io.khasang.archivarius.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id",
            foreignKey = @ForeignKey(name = "COMPANY_ID"))
    private Company company;

    @ManyToOne
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
}
