package com.slendershield.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "MDB's Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Muralidhara Bhat KS",
						email = "ksmuralidhara0@gmail.com",
						url = "https://muralidharabhat.in"
				),
				license = @License(
						name = "MIT Licence",
						url = "https://opensource.org/licenses/MIT"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "GitHub Repository",
				url = "https://github.com/slendershield"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
