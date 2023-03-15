package com.ligavoley.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	 @CrossOrigin(origins = "https://liga-de-voley.web.app")
	 @PreAuthorize("permitAll() OR isAnonymous()")
	 @GetMapping("/lista") 
	    public ResponseEntity<List<Jugador>> list(){
			return ResponseEntity.ok(jugadorService.list());
	    }
	 
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @GetMapping("/detail/{id}")
	    public ResponseEntity<Object> getById(@PathVariable("id") int id){
			return ResponseEntity.ok(jugadorService.getOne(id));
	    }
	  
//	  @GetMapping("/detailname/{nombre}")
//	    public ResponseEntity<Jugador> getByNombre(@PathVariable("nombre") String nombre){
//	        if(!jugadorService.existsByNombre(nombre))
//	            return new ResponseEntity<Jugador>(HttpStatus.NOT_FOUND);
//	        Jugador jugador = jugadorService.getByNombre(nombre).get();
//	        return new ResponseEntity<Jugador>(jugador, HttpStatus.OK);
//	    }
//	  
	  @GetMapping("/detaildni/{dni}")
	    public ResponseEntity<Jugador> getByDni(@PathVariable("dni") Integer dni){
			return ResponseEntity.ok(jugadorService.getByDni(dni));
    } 
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @PreAuthorize("permitAll() OR isAnonymous()")
	 /* @Secured("ROLE_ADMIN")*/
	  @PostMapping("/create")
	    public ResponseEntity<Mensaje> create(@RequestBody JugadorDto jugadorDto){
			return ResponseEntity.ok(jugadorService.save(jugadorDto));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @PreAuthorize("permitAll() OR isAnonymous()")
	  /*@Secured("ROLE_ADMIN")*/
	  @PutMapping("/update/{id}")
	    public ResponseEntity<Mensaje> update(@PathVariable("id")int id, @RequestBody JugadorDto jugadorDto){
			return ResponseEntity.ok(jugadorService.update(id,jugadorDto));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @PreAuthorize("permitAll() OR isAnonymous()")
	 /* @Secured("ROLE_ADMIN")*/
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")int id){
			return ResponseEntity.ok(jugadorService.delete(id));
	    }
}
