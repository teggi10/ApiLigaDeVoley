package com.ligavoley.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ligavoley.dto.JugadorDto;
import com.ligavoley.dto.Mensaje;
import com.ligavoley.model.Jugador;
import com.ligavoley.service.EquipoService;
import com.ligavoley.service.JugadorService;

@RestController
@RequestMapping("/jugador")
@CrossOrigin(origins = "*")
public class JugadorController {

	@Autowired
	JugadorService jugadorService;
	EquipoService equipoService;
	
	 @PreAuthorize("permitAll() OR isAnonymous()")
	 @GetMapping("/lista") 
	    public ResponseEntity<List<Jugador>> list(){
	        List<Jugador> list = jugadorService.list();
	        return new ResponseEntity<List<Jugador>>(list, HttpStatus.OK);
	    }
	 
	  @GetMapping("/detail/{id}")
	    public ResponseEntity<Object> getById(@PathVariable("id") int id){
	        if(!jugadorService.existsById(id))
	            return new ResponseEntity<Object>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        Jugador jugador = jugadorService.getOne(id).get();
	        return new ResponseEntity<Object>(jugador, HttpStatus.OK);
	    }
	  
	  @GetMapping("/detailname/{nombre}")
	    public ResponseEntity<Jugador> getByNombre(@PathVariable("nombre") String nombre){
	        if(!jugadorService.existsByNombre(nombre))
	            return new ResponseEntity<Jugador>(HttpStatus.NOT_FOUND);
	        Jugador jugador = jugadorService.getByNombre(nombre).get();
	        return new ResponseEntity<Jugador>(jugador, HttpStatus.OK);
	    }
	  @PreAuthorize("permitAll() OR isAnonymous()")
	 /* @Secured("ROLE_ADMIN")*/
	  @PostMapping("/create")
	    public ResponseEntity<?> create(@RequestBody JugadorDto jugadorDto){
	        if(Strings.isBlank(jugadorDto.getNombre()))
	            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
	        if(jugadorService.existsByNombre(jugadorDto.getNombre()) && jugadorService.getByNombre(jugadorDto.getNombre()).get().getApellido() == jugadorDto.getApellido())
	            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
	        if((jugadorDto.getNumero()) == 0 || jugadorDto.getNumero() == null)
	            return new ResponseEntity(new Mensaje("el numero es obligatoria"), HttpStatus.BAD_REQUEST);
	        if(Strings.isBlank(jugadorDto.getPosicion()))
	            return new ResponseEntity(new Mensaje("la posicion es obligatorio"), HttpStatus.BAD_REQUEST);
	        Jugador jugador = new Jugador(null, jugadorDto.getNombre(), jugadorDto.getApellido(), jugadorDto.getDni(), jugadorDto.getFechaNac(), jugadorDto.getNumero(), jugadorDto.getPosicion(), jugadorDto.getEquipo());
	        jugadorService.save(jugador);
	        return new ResponseEntity(new Mensaje("jugador creado"), HttpStatus.OK);
	    }
	  
	  @PreAuthorize("permitAll() OR isAnonymous()")
	  /*@Secured("ROLE_ADMIN")*/
	  @PutMapping("/update/{id}")
	    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody JugadorDto jugadorDto){
	        if(!jugadorService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        if(jugadorService.existsByNombre(jugadorDto.getNombre())
	        		&& jugadorService.getByNombre(jugadorDto.getNombre()).get().getIdJugador() != id
	        		&& jugadorService.getByNombre(jugadorDto.getNombre()).get().getApellido() == jugadorDto.getApellido())
	            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
	        if(Strings.isBlank(jugadorDto.getNombre()))
	            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
	        if(jugadorDto.getNumero() < 0 )
	            return new ResponseEntity(new Mensaje("los numeros no pueden ser menores a 0"), HttpStatus.BAD_REQUEST);

	        Jugador jugador = jugadorService.getOne(id).get();
	        jugador.setNombre(jugadorDto.getNombre());
	        jugador.setApellido(jugadorDto.getApellido());
	        jugador.setNumero(jugadorDto.getNumero());
	        jugador.setPosicion(jugadorDto.getPosicion());
	        jugador.setEquipo(jugadorService.getOne(id).get().getEquipo());
	        jugador.setDni(jugadorDto.getDni());
	        jugador.setFechaNac(jugadorDto.getFechaNac());
	        //equipoService.getOne(jugadorDto.getEquipo()).get()
	        jugadorService.save(jugador);
	        return new ResponseEntity(new Mensaje("jugador actualizado"), HttpStatus.OK);
	    }
	  
	  @PreAuthorize("permitAll() OR isAnonymous()")
	 /* @Secured("ROLE_ADMIN")*/
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")int id){
	        if(!jugadorService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        jugadorService.delete(id);
	        
	        return new ResponseEntity(new Mensaje("jugador eliminado"), HttpStatus.OK);
	    }
}
