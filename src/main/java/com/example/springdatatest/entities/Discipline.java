package com.example.springdatatest.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disciplines")
public class Discipline {

    public Discipline() {
    }

    public Discipline(String name, Long code, Teacher teacher) {
        this.name = name;
        this.code = code;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private Long code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disciplines_students", joinColumns = @JoinColumn(name = "discipline_fk"), inverseJoinColumns = @JoinColumn(name = "studant_fk"))
    private List<Student> studentList;

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", teacher=" + teacher +
                ", studentList=" + studentList +
                '}';
    }
}
