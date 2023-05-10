package com.example.springdatatest.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teachers")
public class Teacher {

    public Teacher(){}

    public Teacher(String name)
    {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
