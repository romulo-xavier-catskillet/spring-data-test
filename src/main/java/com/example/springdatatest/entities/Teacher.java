package com.example.springdatatest.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {

    public Teacher(){}

    public Teacher(String name)
    {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Discipline> disciplineList;

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

    public Set<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(Set<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    @PreRemove
    public void updateDisciplinesOnRemove()
    {
        System.out.println("***** updateDisciplinesOnRemove *****");
        for (Discipline discipline: this.getDisciplineList()) {
            discipline.setTeacher(null);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", disciplineList=" + disciplineList +
                "}";
    }
}
