package com.trabajopractico.fundamentosdespring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API REST - Programación IV",
				version = "1.0",
				description = "API REST para la gestión de productos, categorías, usuarios y pedidos. " +
						"Desarrollada con Spring Boot, Spring Data JPA y documentada con Swagger/OpenAPI."
		)
)
@SpringBootApplication
public class FundamentosdespringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundamentosdespringApplication.class, args);
	}

}
