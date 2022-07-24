package com.ligavoley.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ligavoley.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
	
	Optional<Jugador> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
	Optional<Jugador> findByDni(Integer dni);
	boolean existsByDni(Integer dni);
}