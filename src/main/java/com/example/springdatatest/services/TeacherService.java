package com.example.springdatatest.services;

import com.example.springdatatest.entities.Discipline;
import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
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
            System.out.println("5 - visualizar um professor específico");

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

            if (option == 5) {
                this.getTeacherById(scanner);
            }

        }
    }

    private void getAll()
    {
        List<Teacher> teacherList = this.teacherRepository.findAll();
        for (Teacher teacher : teacherList) {
            System.out.println(teacher.toString());
        }
    }

    private void getTeacherById(Scanner scanner)
    {
        System.out.println("Digite o ID do professor que deseja pesquisar");
        Long teacherId = scanner.nextLong();

        Teacher teacher = this.teacherRepository.findById(teacherId).get();

        System.out.println("Professor: {");
        System.out.println("Professor ID: " + teacher.getId());
        System.out.println("Nome do Professor: " + teacher.getName());
        System.out.println("Disciplinas: [");

        for (Discipline discipline: teacher.getDisciplineList()) {
            System.out.println("\tID: " + discipline.getId());
            System.out.println("\tname: " + discipline.getName());
            System.out.println("\tcode: " + discipline.getCode() + "\n");
        }

        System.out.println("]\n}");

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
        try {
            if (teacher == null) {
                System.out.println("Digite o ID do professor que deseja atualizar");
                Long teacherId = scanner.nextLong();
                teacher = this.teacherRepository.findById(teacherId).get();
            }

            System.out.println("Digite o nome do professor para atualizar");
            String name = scanner.next();

            teacher.setName(name);

            this.teacherRepository.save(teacher);

            System.out.println("Professor foi atualizado no banco de dados.");

            return teacher;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return teacher;
        }
    }

    private void delete(Scanner scanner)
    {
        try {
            System.out.println("Digite o ID do professor");
            Long idTeacher = scanner.nextLong();
            Teacher teacher = this.teacherRepository.findById(idTeacher).get();
            this.teacherRepository.delete(teacher);
            System.out.println("Professor deletado com sucesso!!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
