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
    private UUID id;

    @Column(nullable = false)
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
