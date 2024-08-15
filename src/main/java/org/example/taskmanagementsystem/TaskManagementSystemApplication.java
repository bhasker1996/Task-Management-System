package org.example.taskmanagementsystem;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Task Management System REST APIs",
				description = "Spring Boot Task Management System Project REST APIs description",
				version = "v1.0",
				contact = @Contact(
						name = "Bhasker",
						email = "bhasker.medathati@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Task Management System Project description - GITHUB",
				url = "https://github.com/bhasker1996/Task-Management-System"
		)
)
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

}
