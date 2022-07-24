package com.ligavoley.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ligavoley.model.Jugador;
import com.ligavoley.repository.JugadorRepository;

@Service
@Transactional
public class JugadorService {

	@Autowired
	JugadorRepository jugadorRepository;
	
	public List<Jugador> list(){
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> getOne(int id){
        return jugadorRepository.findById(id);
    }

    public Optional<Jugador> getByNombre(String nombre){
        return jugadorRepository.findByNombre(nombre);
    }
    
    public Optional<Jugador> getByDni(Integer dni){
        return jugadorRepository.findByDni(dni);
    }

    public void  save(Jugador jugador){
        jugadorRepository.save(jugador);
    }

    public void delete(int id){
        jugadorRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return jugadorRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return jugadorRepository.existsByNombre(nombre);
    }
    public boolean existsByDni(Integer dni){
        return jugadorRepository.existsByDni(dni);
    }
}
