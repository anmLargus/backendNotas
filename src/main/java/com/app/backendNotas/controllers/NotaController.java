package com.app.backendNotas.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.backendNotas.models.Nota;
import com.app.backendNotas.services.NotaService;

@CrossOrigin(origins = "http://localhost:4200") // http://localhost:4200
@RestController
public class NotaController {
	
	@Autowired
	private NotaService notaService;
	
	@GetMapping("/notas")
	public List<Nota> getAll() {
		return notaService.traerTodo();
	}
	
	@GetMapping("/notas/{id}")
	public ResponseEntity<Nota> getOne(@PathVariable int id) {
		try {
			Nota nota = notaService.traerUno(id);
			return new ResponseEntity<Nota>(nota, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Nota>(HttpStatus.NOT_FOUND); 
			// en realidad la excepcion no se lanzaria, xq en el servicio si no lo encuentra generauna nueva nota			
		}
	}
	
	@PostMapping("/notas")
	public boolean create(@RequestBody Nota n) {
		return notaService.crear(n);
	}
	/*
	 * @PostMapping("/notas")
	 * public void create(@RequestBody Nota n) {
	 * 		notaService.crear(n);
	 * 
	 */
	
	
	@PutMapping("/notas/{id}")
	public ResponseEntity<?> update(@RequestBody Nota n, @PathVariable int id){
		try {
			notaService.modificar(n);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/notas/{id}")
	public boolean remove(@PathVariable int id) {
		return notaService.borrar(id);
	}

}
