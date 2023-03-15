package com.ligavoley.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ligavoley.exceptions.CustomException;
import com.ligavoley.model.Jugador;
import com.ligavoley.repository.JugadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JugadorService {

	@Autowired
	JugadorRepository jugadorRepository;
	
	public List<Jugador> list(){
        return jugadorRepository.findAll();
    }

    public Jugador getOne(int id){
        return jugadorRepository.findById(id).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese jugador no existe"));
    }

    public Jugador getByNombre(String nombre){
        return jugadorRepository.findByNombre(nombre).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese jugador ya existe existe"));
    }
    
    public Jugador getByDni(Integer dni){
        return jugadorRepository.findByDni(dni).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese jugador no existe existe"));
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

    public Object getById(int id) {
        return null;
    }
}
