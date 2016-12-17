package io.khasang.archivarius.entity;

import javax.persistence.*;

/**
 * Model of the reference book for document types
 * Main aim is to give the possibility to user change
 * used document types in organization's field of responsibility
 */

@Entity
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "director_id",
            foreignKey = @ForeignKey(name = "DIRECTOR_ID"))
    private Worker director;
    private String documentType;
    private String description;

    public DocType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
