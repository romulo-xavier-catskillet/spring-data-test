package com.example.springdatatest;

import com.example.springdatatest.entities.Teacher;
import com.example.springdatatest.services.DisciplineService;
import com.example.springdatatest.services.StudentService;
import com.example.springdatatest.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataTestApplication implements CommandLineRunner {

	private TeacherService teacherService;
	private DisciplineService disciplineService;

	private StudentService studentService;

	public SpringDataTestApplication(TeacherService teacherService, DisciplineService disciplineService, StudentService studentService)
	{
		this.teacherService = teacherService;
		this.disciplineService = disciplineService;
		this.studentService = studentService;
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
			System.out.println("2 - Discipline");
			System.out.println("3 - Student");

			int option = scanner.nextInt();

			switch (option) {
				case 1:
					this.teacherService.menu(scanner);
					break;
				case 2:
					this.disciplineService.menu(scanner);
					break;
				case 3:
					this.studentService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}

		}
	}
}
