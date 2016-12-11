package io.khasang.archivarius.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DocumentLifeCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private LifeCycle lifeCycle;

    @ManyToOne
    @JoinColumn(name = "document_id",
            foreignKey = @ForeignKey(name = "DOCUMENT_ID"))
    private Document document;

    @ManyToOne
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "DEPARTMENT_ID"))
    private Department department;

    @Type(type = "timestamp")
    private Date startDate;

    @Type(type = "timestamp")
    private Date finishDate;

    public DocumentLifeCycle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "DocumentLifeCycle{" +
                "id=" + id +
                ", document=" + document +
                ", department=" + department +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
