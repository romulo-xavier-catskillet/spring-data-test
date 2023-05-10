package com.example.springdatatest;

import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataTestApplication implements CommandLineRunner {

	private TeacherService teacherService;

	public SpringDataTestApplication(TeacherService teacherService)
	{
		this.teacherService = teacherService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataTestApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while (isTrue) {
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Teacher");
			int opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					this.teacherService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}

		}
	}
}
