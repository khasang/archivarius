package io.khasang.archivarius.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Worker implements Serializable {

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
}
