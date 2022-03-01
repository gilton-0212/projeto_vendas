package com.vendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EntityScan(basePackages = {"package com.vendas.gestaovendas.entidades"})
@EnableJpaRepositories(basePackages = {"com.vendas.gestaovendas.repositorio"})
@ComponentScan(basePackages = {"com.vendas.gestaovendas.servico"})
@SpringBootApplication

public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
