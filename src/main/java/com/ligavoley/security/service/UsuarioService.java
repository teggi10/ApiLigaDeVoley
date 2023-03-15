package com.ligavoley.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ligavoley.security.model.Usuario;
import com.ligavoley.security.repository.UsuarioRepository;


@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void delete(int id){
				usuarioRepository.deleteById(id);
			}

	public boolean existsById(int id){
				return usuarioRepository.existsById(id);
			}
}
