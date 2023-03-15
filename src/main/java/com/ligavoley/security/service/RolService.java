package com.ligavoley.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligavoley.security.enums.RolNombre;
import com.ligavoley.security.model.Rol;
import com.ligavoley.security.repository.RolRepository;



@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository equipoRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return equipoRepository.findByRolNombre(rolNombre);
	}

	public void save(Rol rolAdmin) {
		equipoRepository.save(rolAdmin);
	}
}