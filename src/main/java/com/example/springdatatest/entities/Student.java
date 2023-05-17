package com.example.springdatatest.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    public Student()
    {}

    public Student(String name, Integer age)
    {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @ManyToMany(mappedBy = "studentList")
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(Set<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", disciplineList=" + disciplineList +
                '}';
    }
}
