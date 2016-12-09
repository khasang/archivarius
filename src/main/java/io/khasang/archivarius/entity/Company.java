package io.khasang.archivarius.entity;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private String name;

    private int innNumber;

    private String address;

    @ManyToOne
    @JoinColumn(name = "director_id",
            foreignKey = @ForeignKey(name = "DIRECTOR_ID"))
    private Worker director;

    public Company() {
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

    public int getInnNumber() {
        return innNumber;
    }

    public void setInnNumber(int innNumber) {
        this.innNumber = innNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Worker getDirector() {
        return director;
    }

    public void setDirector(Worker director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", innNumber=" + innNumber +
                ", address='" + address + '\'' +
                ", director=" + director +
                '}';
    }
}
