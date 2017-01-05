package io.khasang.archivarius.entity;
/**
 * Model of general document, that uses into
 * inbox, outbox and internal doc. base
 */

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Type(type="date")
    private Date dateOfReceive;
    private String author;
    private String title;
    private String status;

    @ManyToOne
    @JoinColumn(name = "doctype_id",
            foreignKey = @ForeignKey(name = "DOCTYPE_ID"))
    private DocType documentType;

    @Type(type="date")
    private Date deadline;
    private String destination;
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

    private void setDocumentKey(int documentKey) {
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", dateOfReceive='" + dateOfReceive + '\'' +
                ", author=" + author +
                ", title=" + title + '\'' +
                ", status=" + status +
                ", documentType=" + documentType + '\'' +
                ", deadline=" + deadline +
                ", destination=" + destination +
                ", documentKey=" + documentKey +
                '}';
    }
}
