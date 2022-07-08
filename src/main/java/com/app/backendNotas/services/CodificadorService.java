package com.app.backendNotas.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CodificadorService {	
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String codificarDemo() {		
		
		String palabra = "secreto";		

		String encodedPalabraDemo = passwordEncoder.encode(palabra);

		System.out.println();
		System.out.println("Tu palabra es      : " + palabra);
		System.out.println("Encoded Palabra es : " + encodedPalabraDemo);
		
		
		return encodedPalabraDemo;

	}
	
	public String codificar(String p) {
		
		return passwordEncoder.encode(p);
		 
	}
}
