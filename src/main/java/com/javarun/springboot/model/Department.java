package com.javarun.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "depatment")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "department_key")
    private Integer key;

    @Column(name = "DateLastTimeModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastTimeModified;

    // Constructors, getters, and setters

    public Department() {
    }

    public Department(String name, Integer key, Date dateLastTimeModified) {
        this.name = name;
        this.key = key;
        this.dateLastTimeModified = dateLastTimeModified;
    }

    // Getters and setters

}
