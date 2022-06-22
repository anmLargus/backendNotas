package com.app.backendNotas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backendNotas.models.Nota;
import com.app.backendNotas.repositories.INotaRepository;

@Service
public class NotaService implements ICrudService<Nota> {
	
	@Autowired
	INotaRepository notaRepo;

	@Override
	public List<Nota> traerTodo() {
		// TODO Auto-generated method stub
		return notaRepo.findAll();
	}

	@Override
	public Nota traerUno(long id) {
		// TODO Auto-generated method stub
		return notaRepo.findById(id).orElse(new Nota());
	}

	@Override
	public boolean crear(Nota n) {
		// TODO Auto-generated method stub
		notaRepo.save(n);
		return true;
	}

	@Override
	public boolean modificar(Nota n) {
		// TODO Auto-generated method stub
		notaRepo.save(n);
		return false;
	}

	@Override
	public boolean borrar(long id) {
		// TODO Auto-generated method stub
		try {
			notaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean borrar(Nota n) {
		// TODO Auto-generated method stub
		try {
			notaRepo.delete(n);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}	

}
