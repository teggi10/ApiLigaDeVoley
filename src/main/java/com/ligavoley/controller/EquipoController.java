package com.ligavoley.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

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

import jakarta.validation.Valid;



@RestController
@RequestMapping("/equipo")
@CrossOrigin(origins = "*")
public class EquipoController {

	 @Autowired
	 EquipoService equipoService;
	 
	 @CrossOrigin(origins = "https://liga-de-voley.web.app")
	 @GetMapping("/lista") 
	    public ResponseEntity<List<Equipo>> list(){
	       return ResponseEntity.ok(equipoService.list());
	    }
	 
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @GetMapping("/detail/{id}")
	    public ResponseEntity<Object> getById(@PathVariable("id") int id){
			return ResponseEntity.ok(equipoService.getOne(id));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @GetMapping("/detailname/{nombre}")
	    public ResponseEntity<Equipo> getByNombre(@PathVariable("nombre") String nombre){
			return ResponseEntity.ok(equipoService.getByNombre(nombre));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @PostMapping("/create")
	    public ResponseEntity<Mensaje> create(@Valid @RequestBody EquipoDto equipoDto){
	        return ResponseEntity.ok(equipoService.save(equipoDto));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @PutMapping("/update/{id}")
	    public ResponseEntity<Mensaje> update(@PathVariable("id")int id, @Valid @RequestBody EquipoDto equipoDto){
	        return ResponseEntity.ok(equipoService.update(id,equipoDto));
	    }
	  
	  @CrossOrigin(origins = "https://liga-de-voley.web.app")
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Mensaje> delete(@PathVariable("id")int id){
			return ResponseEntity.ok(equipoService.delete(id));
	    }
}
