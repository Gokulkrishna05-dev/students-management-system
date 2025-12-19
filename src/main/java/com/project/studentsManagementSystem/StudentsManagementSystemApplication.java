package com.project.studentsManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication

public class StudentsManagementSystemApplication {

	public static void main(String[] args) {
		Dotenv dotenv=Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load();
		if(dotenv.get("DATASOURCE_URL")!=null) {
			System.setProperty("DATASOURCE_URL", dotenv.get("DATASOURCE_URL"));
		}
		if(dotenv.get("DATASOURCE_USER")!=null) {
			System.setProperty("DATASOURCE_USER", dotenv.get("DATASOURCE_USER"));
		}
		if(dotenv.get("DATASOURCE_PASSWORD")!=null) {
			System.setProperty("DATASOURCE_PASSWORD", dotenv.get("DATASOURCE_PASSWORD"));
		}
		if(dotenv.get("SPRING_MAIL_USERNAME")!=null) {
			System.setProperty("SPRING_MAIL_USERNAME", dotenv.get("SPRING_MAIL_USERNAME"));
		}
		if(dotenv.get("SPRING_MAIL_PASSWORD")!=null) {
			System.setProperty("SPRING_MAIL_PASSWORD", dotenv.get("SPRING_MAIL_PASSWORD"));
		}
		SpringApplication.run(StudentsManagementSystemApplication.class, args);
	}
}	
