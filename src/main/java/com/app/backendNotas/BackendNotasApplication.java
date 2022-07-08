package com.app.backendNotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BackendNotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendNotasApplication.class, args);
		/*
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "Usuario1";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);
		*/
	}

}
