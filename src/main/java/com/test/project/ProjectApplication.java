package com.test.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan
public class ProjectApplication implements CommandLineRunner {
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("user"));
	}

	
}
