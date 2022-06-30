package com.app.backendNotas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.backendNotas.security.controllers.LoginController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);//Para ver en /testeo por la consola
	
	@GetMapping("/testeo")
	public ResponseEntity<?> getMensaje() {		
		
		var auth =  SecurityContextHolder.getContext().getAuthentication(); 
	    logger.info("Datos del Usuario: {}", auth.getPrincipal()); 
	    logger.info("Datos de los Permisos {}", auth.getAuthorities());
	    logger.info("Esta autenticado {}", auth.isAuthenticated());
		
		Map<String, String> mensaje = new HashMap<>();
		mensaje.put("contenido", "Hola, usted es: " + auth.getName() );
		return ResponseEntity.ok(mensaje);
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> getMensajeAdmin() {
		
		var auth =  SecurityContextHolder.getContext().getAuthentication(); 
	    logger.info("Datos del Usuario: {}", auth.getPrincipal()); 
	    logger.info("Datos de los Permisos {}", auth.getAuthorities());
	    logger.info("Esta autenticado {}", auth.isAuthenticated());
		
		Map<String, String> mensaje = new HashMap<>();
		mensaje.put("contenido", "Hola ADMIN");
		return ResponseEntity.ok(mensaje);		
	}
	
}

