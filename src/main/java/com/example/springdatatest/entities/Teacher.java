package com.example.springdatatest.entities;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Discipline> disciplineList;

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

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", disciplineList=" + disciplineList +
                '}';
    }
}
