package io.khasang.archivarius.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books {
    @Id
    private int id;
    private String title;

    public Books() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
