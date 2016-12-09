package io.khasang.archivarius.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Worker {

    @Id
    @GeneratedValue
    private int id;

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
