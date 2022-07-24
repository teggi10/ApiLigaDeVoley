package com.ligavoley.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="jugador")
public class Jugador {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJugador;
	
	@Column(name="nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name="apellido", length = 100, nullable = false)
	private String apellido;
	
	@Column(name="dni", length = 100, nullable = false)
	private Integer dni;
	
	@Column(name="fechaNac", length = 100, nullable = false)
	private String fechaNac;
	
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="posicion", length = 50)
	private String posicion;
	
	@Column(name="eliminado")
	private boolean eliminado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;

	public Jugador() {
		super();
	}
	
	public Jugador(Integer idJugador, String nombre, String apellido,Integer dni ,String fechaNac, Integer numero, String posicion,boolean eliminado, Equipo equipo) {
		super();
		this.idJugador = idJugador;
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

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
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

	@JsonBackReference
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	
	
	
	
	
}
