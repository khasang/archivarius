package io.khasang.archivarius.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users;
}
