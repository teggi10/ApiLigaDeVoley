package com.ligavoley.dto;

import java.util.Set;

import com.ligavoley.model.Jugador;
import com.ligavoley.security.model.Usuario;

import jakarta.validation.constraints.NotBlank;

public class EquipoDto {

	@NotBlank(message ="El nombre del equipo es obligatorio")
	private String nombre;

	@NotBlank(message ="La localidad del equipo es obligatorio")
	private String localidad;
	
	private String sexo;
	
	private Integer puntos;
	
	private String nombreClave;
	
	@NotBlank(message ="La categoria del equipo es obligatorio")
	private String categoria;
	
	private Usuario usuario;

	private Set<Jugador> jugadores;
	
	public EquipoDto() {
		
	}

	public EquipoDto(String nombre, String localidad,String sexo, Integer puntos,String nombreClave,String categoria, Set<Jugador> jugadores, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.puntos = puntos;
		this.jugadores = jugadores;
		this.sexo = sexo;
		this.nombreClave = nombreClave;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombreClave() {
		return nombreClave;
	}

	public void setNombreClave(String nombreClave) {
		this.nombreClave = nombreClave;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
this.jugadores = jugadores;
		//this.jugadores.addAll(jugadores);
		/*for(Jugador jugador: jugadores) {
			jugador.setEquipo(this);
		}*/
	}

	
}
