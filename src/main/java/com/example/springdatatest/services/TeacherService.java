package com.example.springdatatest.services;

import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

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

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.store(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
    }

    private void store(Scanner scanner)
    {
        System.out.println("Digite o nome do professor");
        String name = scanner.next();

        Teacher teacher = new Teacher(name);
        this.teacherRepository.save(teacher);
        System.out.println("Professor foi salvo no banco de dados.");
    }
}
