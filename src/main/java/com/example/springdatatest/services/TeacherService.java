package com.example.springdatatest.services;

import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.Scanner;

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
            System.out.println("3 - Visualizar todos os professores");
            System.out.println("4 - Deletar um professor");

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

            if (option == 3) {
                this.getAll();
            }

            if (option == 4) {
                this.delete(scanner);
            }

        }
    }

    private void getAll()
    {
        Iterable<Teacher> teachersIterable = this.teacherRepository.findAll();
        for (Teacher teacher : teachersIterable) {
            System.out.println(teacher);
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

    private void delete(Scanner scanner)
    {
        try {
            System.out.println("Digite o ID do professor");
            Long idTeacher = scanner.nextLong();
            Teacher teacher = this.teacherRepository.findById(idTeacher).get();
            this.teacherRepository.delete(teacher);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
