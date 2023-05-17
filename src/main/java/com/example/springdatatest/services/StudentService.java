package com.example.springdatatest.services;

import com.example.springdatatest.entities.Discipline;
import com.example.springdatatest.entities.Student;
import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;

    private Student student;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void menu(Scanner scanner)
    {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo aluno");
            System.out.println("2 - Atualizar aluno");
            System.out.println("3 - Visualizar todos os alunos");
            System.out.println("4 - Deletar um aluno");
            System.out.println("5 - visualizar um aluno específico");

            int option = scanner.nextInt();

            if (option == 0) {
                isTrue = false;
            }

            if (option == 1) {
                this.student = this.store(scanner);
            }

            if (option == 2) {
                this.student = this.update(scanner, this.student);
            }

            if (option == 3) {
                this.getAll();
            }

            if (option == 4) {
                this.delete(scanner);
            }

            if (option == 5) {
                this.getStudentById(scanner);
            }

        }
    }

    private void getAll()
    {
        List<Student> studentList = this.studentRepository.findAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private void getStudentById(Scanner scanner)
    {
        System.out.println("Digite o ID do aluno que deseja pesquisar");
        Long studentId = scanner.nextLong();

        Student student = this.studentRepository.findById(studentId).get();

        System.out.println("Aluno: {");
        System.out.println("Aluno ID: " + student.getId());
        System.out.println("Nome do Aluno: " + student.getName());
        System.out.println("Disciplinas: [");

        if (student.getDisciplineList() != null) {
            for (Discipline discipline: student.getDisciplineList()) {
                System.out.println("\tID: " + discipline.getId());
                System.out.println("\tname: " + discipline.getName());
                System.out.println("\tcode: " + discipline.getCode() + "\n");
            }
        }

        System.out.println("]\n}");

    }

    private Student store(Scanner scanner)
    {
        System.out.println("Digite o nome do aluno");
        String name = scanner.next();

        System.out.println("Digite a idade do aluno");
        Integer age = scanner.nextInt();

        Student studentCreate = new Student();

        studentCreate.setName(name);
        studentCreate.setAge(age);

        this.studentRepository.save(studentCreate);
        System.out.println("Aluno foi salvo no banco de dados.");

        return studentCreate;
    }

    private Student update(Scanner scanner, Student student)
    {
        try {
            if (student == null) {
                System.out.println("Digite o ID do aluno que deseja atualizar");
                Long teacherId = scanner.nextLong();
                student = this.studentRepository.findById(teacherId).get();
            }

            System.out.println("Digite o nome do aluno para atualizar");
            String name = scanner.next();

            System.out.println("Digite a idade do aluno");
            Integer age = scanner.nextInt();

            student.setName(name);
            student.setAge(age);

            this.studentRepository.save(student);

            System.out.println("Aluno foi atualizado no banco de dados.");

            return student;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return student;
        }
    }

    private void delete(Scanner scanner)
    {
        try {
            System.out.println("Digite o ID do aluno");
            Long studentId = scanner.nextLong();
            Student student = this.studentRepository.findById(studentId).get();
            this.studentRepository.delete(student);
            System.out.println("Aluno deletado com sucesso!!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
