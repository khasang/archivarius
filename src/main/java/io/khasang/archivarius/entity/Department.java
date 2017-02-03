package io.khasang.archivarius.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
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
}
