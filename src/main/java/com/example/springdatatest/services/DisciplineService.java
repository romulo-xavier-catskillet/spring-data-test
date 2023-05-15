package com.example.springdatatest.services;

import com.example.springdatatest.entities.Discipline;
import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.DisciplineRepository;
import com.example.springdatatest.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class DisciplineService {

    private TeacherRepository teacherRepository;
    private DisciplineRepository disciplineRepository;

    private Teacher teacher;
    private Discipline discipline;

    public DisciplineService(TeacherRepository teacherRepository, DisciplineRepository disciplineRepository)
    {
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public void menu(Scanner scanner)
    {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova disciplina");
            System.out.println("2 - Atualizar disciplina");
            System.out.println("3 - Visualizar todas as disciplinas");
            System.out.println("4 - Deletar uma disciplina");

            int option = scanner.nextInt();

            if (option == 0) {
                isTrue = false;
            }

            if (option == 1) {
                this.discipline = this.store(scanner);
            }

            if (option == 2) {
                this.discipline = this.update(scanner, this.discipline);
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
        List<Discipline> disciplineList = this.disciplineRepository.findAll();
        for (Discipline discipline : disciplineList) {
            System.out.println(discipline);
        }
    }

    private Discipline store(Scanner scanner)
    {
        System.out.println("Digite o nome da disciplina");
        String name = scanner.next();

        System.out.println("Digite o código da disciplina");
        Long code = scanner.nextLong();

        System.out.println("Digite o ID do professor");
        Long teacherId = scanner.nextLong();

        Teacher teacher = this.teacherRepository.findById(teacherId).get();
        Discipline disciplineCreate = new Discipline(name, code, teacher);

        this.disciplineRepository.save(disciplineCreate);
        System.out.println("Disciplina foi salva no banco de dados.");

        return disciplineCreate;
    }

    private Discipline update(Scanner scanner, Discipline discipline)
    {

        if (discipline == null) {
            System.out.println("Digite o ID da disciplina que deseja atualizar");
            Long disciplinaId = scanner.nextLong();
            discipline = this.disciplineRepository.findById(disciplinaId).get();
        }

        System.out.println("Digite o nome da disciplina");
        String name = scanner.next();

        System.out.println("Digite o código da disciplina");
        Long code = scanner.nextLong();

        System.out.println("Digite o ID do professor");
        Long teacherId = scanner.nextLong();

        Teacher teacher = this.teacherRepository.findById(teacherId).get();
        System.out.println(teacher);

        discipline.setName(name);
        discipline.setCode(code);
        discipline.setTeacher(teacher);


        this.disciplineRepository.save(discipline);

        System.out.println("Disciplina foi atualizada no banco de dados.");

        return discipline;
    }

    private void delete(Scanner scanner)
    {
        try {
            System.out.println("Digite o ID da disciplina");
            Long idDiscipline = scanner.nextLong();
            Discipline discipline = this.disciplineRepository.findById(idDiscipline).get();
            this.disciplineRepository.delete(discipline);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
