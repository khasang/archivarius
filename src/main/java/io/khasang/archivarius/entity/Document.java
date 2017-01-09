package io.khasang.archivarius.entity;
/**
 * Model of general document, that uses into
 * inbox, outbox and internal doc. base
 */

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Type(type="date")
    private Date dateOfReceive;
    private String author;
    private String title;
    private String status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "doctype_id",
            foreignKey = @ForeignKey(name = "DOCTYPE_ID"))
    private DocType documentType;

    @Type(type="date")
    private Date deadline;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "DEPARTMENT_ID"))
    private Department department;

    private String fileName;
    private int documentKey;

    public Document() {
    }

    public DocType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocType documentType) {
        this.documentType = documentType;
    }

    public int getId() {
        return id;
    }

    public int getDocumentKey() {
        return documentKey;
    }

    public int getInboxKey() {
        return 1;
}

    public int getOutboxKey() {
        return 2;
    }

    public int getInternalKey() {
        return 3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDocumentKey(int documentKey) {
        this.documentKey = documentKey;
    }

    public Date getDateOfReceive() {
        return dateOfReceive;
    }

    public void setDateOfReceive(Date dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", dateOfReceive=" + dateOfReceive +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", documentType=" + documentType +
                ", deadline=" + deadline +
                ", department=" + department +
                ", fileName='" + fileName + '\'' +
                ", documentKey=" + documentKey +
                '}';
    }
}
