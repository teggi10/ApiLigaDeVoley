package com.ligavoley.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ligavoley.model.Equipo;
import com.ligavoley.repository.EquipoRepository;



@Service
@Transactional
public class EquipoService {

	 @Autowired
	    EquipoRepository equipoRepository;

	    public List<Equipo> list(){
	        return equipoRepository.findAll();
	    }

	    public Optional<Equipo> getOne(int id){
	        return equipoRepository.findById(id);
	    }

	    public Optional<Equipo> getByNombre(String nombre){
	        return equipoRepository.findByNombre(nombre);
	    }

	    public void  save(Equipo equipo){
	        equipoRepository.save(equipo);
	    }
	   

	    public void delete(int id){
	        equipoRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return equipoRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return equipoRepository.existsByNombre(nombre);
	    }
}
