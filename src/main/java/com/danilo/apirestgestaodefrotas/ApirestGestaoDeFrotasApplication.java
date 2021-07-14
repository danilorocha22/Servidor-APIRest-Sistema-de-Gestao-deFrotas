package com.danilo.apirestgestaodefrotas;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("all")
@Api(value = "API REST GESTÃO DE FROTAS")
@EnableSwagger2
@SpringBootApplication
public class ApirestGestaoDeFrotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestGestaoDeFrotasApplication.class, args);
	}
}
