package io.khasang.archivarius.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Model of the reference book for document types
 * Main aim is to give the possibility to user change
 * used document types in organization's field of responsibility
 */

@Entity
public class DocType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;

    public DocType() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
