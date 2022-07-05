package com.ligavoley.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.ligavoley.security.model.Usuario;
import com.ligavoley.security.model.UsuarioPrincipal;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		   Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
	       return UsuarioPrincipal.build(usuario);
	}

}
