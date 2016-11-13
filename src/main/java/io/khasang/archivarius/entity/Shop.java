package io.khasang.archivarius.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop {
    @Id
    private int id;

    private String name;
    private String description;

    public Shop() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
