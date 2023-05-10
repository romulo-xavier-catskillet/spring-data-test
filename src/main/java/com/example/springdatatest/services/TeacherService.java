package com.example.springdatatest.services;

import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.UUID;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    private Teacher teacher;

    public TeacherService(TeacherRepository teacherRepository)
    {
        this.teacherRepository = teacherRepository;
    }

    public void menu(Scanner scanner)
    {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo professor");
            System.out.println("2 - Atualizar professor");

            int option = scanner.nextInt();

            if (option == 0) {
                isTrue = false;
            }

            if (option == 1) {
                this.teacher = this.store(scanner);
            }

            if (option == 2) {
                this.teacher = this.update(scanner, this.teacher);
            }

        }
    }

    private Teacher store(Scanner scanner)
    {
        System.out.println("Digite o nome do professor");
        String name = scanner.next();

        Teacher teacherCreate = new Teacher();

        teacherCreate.setName(name);

        this.teacherRepository.save(teacherCreate);
        System.out.println("Professor foi salvo no banco de dados.");

        return teacherCreate;
    }

    private Teacher update(Scanner scanner, Teacher teacher)
    {
        System.out.println("Digite o nome do professor para atualizar");
        String name = scanner.next();

        teacher.setName(name);

        this.teacherRepository.save(teacher);

        System.out.println("Professor foi atualizado no banco de dados.");

        return teacher;
    }
}
