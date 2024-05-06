package com.lbg.integrator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories({"com.lbg.integrator.*"})
public class IntegratorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegratorApiApplication.class, args);
	}



}
