package com.ligavoley.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ligavoley.model.Equipo;



@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

	Optional<Equipo> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	//Optional<Equipo> findByAdminNombre(Equipo equipo);
}
