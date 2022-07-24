package com.ligavoley.dto;

import com.ligavoley.model.Equipo;

public class JugadorDto {

	
	private String nombre;
	
	
	private String apellido;
	
	
	private Integer numero;
	
	private Integer dni;
	
	private String fechaNac;
	
	private String posicion;
	
	private boolean eliminado;
	
	private Equipo equipo;


	public JugadorDto(String nombre, String apellido, Integer numero,String fechaNac,Integer dni , String posicion,boolean eliminado, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero = numero;
		this.posicion = posicion;
		this.equipo = equipo;
		this.dni = dni;
		this.fechaNac = fechaNac;
		this.eliminado = eliminado;
	}


	public boolean isEliminado() {
		return eliminado;
	}


	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}


	public JugadorDto() {
		super();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}

	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public String getPosicion() {
		return posicion;
	}


	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}


	public Equipo getEquipo() {
		return this.equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	
}
