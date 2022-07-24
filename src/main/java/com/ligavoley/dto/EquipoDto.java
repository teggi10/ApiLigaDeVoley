package com.ligavoley.dto;

import java.util.List;
import java.util.Set;

import com.ligavoley.model.Jugador;

public class EquipoDto {

	private String nombre;
	
	private String localidad;
	
	private String sexo;
	
	private Integer puntos;
	
	private String nombreClave;
	
	private String categoria;
	

	private Set<Jugador> jugadores;
	
	public EquipoDto() {
		
	}

	public EquipoDto(String nombre, String localidad,String sexo, Integer puntos,String nombreClave,String categoria, Set<Jugador> jugadores) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.puntos = puntos;
		this.jugadores = jugadores;
		this.sexo = sexo;
		this.nombreClave = nombreClave;
		this.categoria = categoria;
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
