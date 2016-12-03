package io.khasang.archivarius.entity;

import javax.persistence.*;


@Entity
public class EmployeesReferenceBook {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "DEPARTMENT_ID_FK")
    )
    private Department department;
    private String position;

    @OneToOne
    @JoinColumn(name = "phone_id",
            foreignKey = @ForeignKey(name = "PHONE_ID_FK")
    )
    private Phone phone;

    public EmployeesReferenceBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}