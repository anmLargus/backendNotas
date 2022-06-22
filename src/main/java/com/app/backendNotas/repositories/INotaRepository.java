package com.app.backendNotas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.backendNotas.models.Nota;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long>{	

}
