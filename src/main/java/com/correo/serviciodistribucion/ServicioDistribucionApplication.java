package com.correo.serviciodistribucion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.correo.serviciodistribucionmodel.repositories")
@EntityScan("com.correo.serviciodistribucionmodel.model.dbentities")
public class ServicioDistribucionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioDistribucionApplication.class, args);
	}

}
