package com.example.springboot2.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student mariam = new Student("Mariam", "mariam@gmail.com", LocalDate.of(2022, Month.JANUARY, 15));
			Student joe = new Student("Joe", "joe@gmail.com", LocalDate.of(2022, Month.OCTOBER, 10));
			repository.saveAll(List.of(mariam, joe));
		};
	}

}
