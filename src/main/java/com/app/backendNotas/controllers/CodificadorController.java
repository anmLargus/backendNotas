package com.app.backendNotas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.backendNotas.models.Palabra;
import com.app.backendNotas.models.RespuestaCodificador;
import com.app.backendNotas.services.CodificadorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CodificadorController {
	
	@Autowired
	CodificadorService codificadorService;
		
	@GetMapping("/encode")
	public String decoderDemo() {
		return codificadorService.codificarDemo();
	}
	
	@PostMapping("/encode")
	public RespuestaCodificador codificar(@RequestBody Palabra palabra) {
		
		RespuestaCodificador r = new RespuestaCodificador();
		r.msg = "Ok";
		r.palabra = palabra.palabra;
		r.palabraCodificada = codificadorService.codificar(palabra.palabra);
		
		return r;		
	}
	
	

}
