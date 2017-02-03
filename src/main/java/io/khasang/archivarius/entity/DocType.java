package io.khasang.archivarius.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Model of the reference book for document types
 * Main aim is to give the possibility to user change
 * used document types in organization's field of responsibility
 */

@Data
@Entity
public class DocType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;
}
