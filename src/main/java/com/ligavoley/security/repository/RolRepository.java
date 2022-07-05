package com.ligavoley.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ligavoley.security.enums.RolNombre;
import com.ligavoley.security.model.Rol;


public interface RolRepository extends JpaRepository<Rol, Integer>{
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}