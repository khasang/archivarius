package io.khasang.archivarius.entity;
/**
 * Model of general document, that uses into
 * inbox, outbox and internal doc. base
 */

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Type(type="date")
    private Date dateOfReceive;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "AUTHOR_ID"))
    private User author;

    private String title;
    private String status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "doctype_id",
            foreignKey = @ForeignKey(name = "DOCTYPE_ID"))
    private DocType documentType;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "worker_id",
            foreignKey = @ForeignKey(name = "WORKER_ID"))
    private Worker worker;

    @Type(type="date")
    private Date deadline;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "DEPARTMENT_ID"))
    private Department department;

    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_key")
    private DocKey docKey;
}
