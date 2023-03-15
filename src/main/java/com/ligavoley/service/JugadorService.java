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
        return jugadorRepository.findByNombre(nombre).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese jugador ya existe"));
    }
    
    public Jugador getByDni(Integer dni){
        return jugadorRepository.findByDni(dni).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese jugador no existe"));
    }

    public void  save(JugadorDto jugadorDto){
        
            if(jugadorRepository.existsByDni(jugadorDto.getDni())){
               Optional<Jugador> jugadorAlmacenado = jugadorRepository.findByDni();
                if(jugadorAlmacenado.isPresent() && jugadorAlmacenado.get().getDni() == jugadorDto.getDni()){
                    Jugador jugador = new Jugador(null, jugadorDto.getNombre(), jugadorDto.getApellido(), jugadorDto.getDni(),
                 jugadorDto.getFechaNac(), jugadorDto.getNumero(), jugadorDto.getPosicion(),jugadorDto.isEliminado(), jugadorDto.getjugador());
                    jugadorRepository.delete(jugadorAlmacenado);
                    jugadorRepository.save(jugador);
                    return new Mensaje("Jugador creado");
                }else{
                    throw new CustomException(HttpStatus.ERROR, "Ese jugador pertenece o pertenecio a otro jugador")
                }
            }else{
                Jugador jugador = new Jugador(null, jugadorDto.getNombre(), jugadorDto.getApellido(), jugadorDto.getDni(),
                 jugadorDto.getFechaNac(), jugadorDto.getNumero(), jugadorDto.getPosicion(),jugadorDto.isEliminado(), jugadorDto.getjugador());
                jugadorRepository.save(jugador);
                return new Mensaje("Jugador creado");
            }
//	        if(jugadorService.existsByNombre(jugadorDto.getNombre())
//	        && jugadorService.getByNombre(jugadorDto.getNombre()).get().getApellido() == jugadorDto.getApellido()) {
//	        	
//	        	Optional<Jugador> jugadorAlmacenado = jugadorService.getByNombre(jugadorDto.getNombre());
//	        	if(jugadorAlmacenado.get().getjugador().getIdjugador() == jugadorDto.getjugador().getIdjugador() 
//	        	&& jugadorAlmacenado.get().getDni() == jugadorDto.getDni()) {
//	        	    jugadorAlmacenado.get().setNombre(jugadorDto.getNombre());
//	        	    jugadorAlmacenado.get().setApellido(jugadorDto.getApellido());
//	        	    jugadorAlmacenado.get().setNumero(jugadorDto.getNumero());
//	        	    jugadorAlmacenado.get().setPosicion(jugadorDto.getPosicion());
//	        	    jugadorAlmacenado.get().setjugador(jugadorDto.getjugador());
//	        	    jugadorAlmacenado.get().setDni(jugadorDto.getDni());
//	        	    jugadorAlmacenado.get().setFechaNac(jugadorDto.getFechaNac());
//	        	    jugadorAlmacenado.get().setEliminado(jugadorDto.isEliminado());
//	        		jugadorService.save(jugadorAlmacenado.get());
//	    	        return new ResponseEntity(new Mensaje("Jugador creado"), HttpStatus.OK);
//	        	}else {
//	        		return new ResponseEntity(new Mensaje("No se permite cambiar jugadores de jugador"), HttpStatus.BAD_REQUEST);
//	        	}
//	        	
//	        }
      
    }

    public Mensaje update(int id, JugadorDto jugadorDto){
        if(!jugadorRepository.existsById(id))
        throw new CustomException(HttpStatus.BAD_REQUEST, "Ese jugador no existe");
        Optional<Jugador> jOptional = jugadorRepository.findByDni(jugadorDto.getDni());
        if(jOptional.isPresent() && jOptional.get().getIdJugador() != id)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Ese jugador ya existe");
        Jugador jugador = getOne(id);
        jugador.setNombre(jugadorDto.getNombre());
        jugador.setApellido(jugadorDto.getApellido());
        jugador.setNumero(jugadorDto.getNumero());
        jugador.setPosicion(jugadorDto.getPosicion());
        jugador.setEquipo(jugadorService.getOne(id).get().getEquipo());
        jugador.setDni(jugadorDto.getDni());
        jugador.setFechaNac(jugadorDto.getFechaNac());
        jugador.setEliminado(jugadorDto.isEliminado());
        jugadorRepository.save(jugador);

        return new Mensaje("Jugador actualizado");
    }

    public void delete(int id){
        if(!jugadorRepository.existsById(id))
        throw new CustomException(HttpStatus.BAD_REQUEST, "Ese jugador no existe");
        Jugador jugador = getOne(id);
        jugador.setEliminado(true); // borrado logico
        //jugadorRepository.delete(jugador);
        return new Mensaje("Jugador eliminado")
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
