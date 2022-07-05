package com.ligavoley.controller;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ligavoley.dto.EquipoDto;
import com.ligavoley.dto.Mensaje;
import com.ligavoley.model.Equipo;
import com.ligavoley.service.EquipoService;



@RestController
@RequestMapping("/equipo")
@CrossOrigin(origins = "*")
public class EquipoController {

	 @Autowired
	 EquipoService equipoService;
	 
	 @GetMapping("/lista") 
	    public ResponseEntity<List<Equipo>> list(){
	        List<Equipo> list = equipoService.list();
	        return new ResponseEntity<List<Equipo>>(list, HttpStatus.OK);
	    }
	 
	  @GetMapping("/detail/{id}")
	    public ResponseEntity<Object> getById(@PathVariable("id") int id){
	        if(!equipoService.existsById(id))
	            return new ResponseEntity<Object>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        Equipo equipo = equipoService.getOne(id).get();
	        return new ResponseEntity<Object>(equipo, HttpStatus.OK);
	    }
	  
	  @GetMapping("/detailname/{nombre}")
	    public ResponseEntity<Equipo> getByNombre(@PathVariable("nombre") String nombre){
	        if(!equipoService.existsByNombre(nombre))
	            return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
	        Equipo equipo = equipoService.getByNombre(nombre).get();
	        return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
	    }
	  
	  
	  @PostMapping("/create")
	    public ResponseEntity<?> create(@RequestBody EquipoDto equipoDto){
	        if(Strings.isBlank(equipoDto.getNombre()))
	            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
	        if(Strings.isBlank(equipoDto.getLocalidad()))
	            return new ResponseEntity(new Mensaje("la localidad es obligatoria"), HttpStatus.BAD_REQUEST);
	        if(Strings.isBlank(equipoDto.getSexo()))
	            return new ResponseEntity(new Mensaje("el sexo es obligatorio"), HttpStatus.BAD_REQUEST);
	        Equipo equipo = new Equipo(null, equipoDto.getNombre(), equipoDto.getLocalidad(), equipoDto.getSexo(), null,equipoDto.getNombreClave(),equipoDto.getCategoria(), null);
	        equipoService.save(equipo);
	        return new ResponseEntity(new Mensaje("equipo creado"), HttpStatus.OK);
	    }
	  
	  
	  @PutMapping("/update/{id}")
	    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EquipoDto equipoDto){
	        if(!equipoService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        if(Strings.isBlank(equipoDto.getNombre()))
	            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
	       /* if(equipoDto.getPuntos() < 0 )
	            return new ResponseEntity(new Mensaje("los puntos no pueden ser menores a 0"), HttpStatus.BAD_REQUEST);*/

	        Equipo equipo = equipoService.getOne(id).get();
	        equipo.setNombre(equipoDto.getNombre());
	        equipo.setLocalidad(equipoDto.getLocalidad());
	        equipo.setJugadores(equipoDto.getJugadores());
	        equipo.setPuntos(equipoDto.getPuntos());
	        equipo.setSexo(equipoDto.getSexo());
	        equipo.setNombreClave(equipoDto.getNombreClave());
	        equipo.setCategoria(equipoDto.getCategoria());
	        equipoService.save(equipo);
	        return new ResponseEntity(new Mensaje("equipo actualizado" + equipo.getJugadores()), HttpStatus.OK);
	    }
	  
	 
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")int id){
	        if(!equipoService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        equipoService.delete(id);
	        return new ResponseEntity(new Mensaje("equipo eliminado"), HttpStatus.OK);
	    }
}
