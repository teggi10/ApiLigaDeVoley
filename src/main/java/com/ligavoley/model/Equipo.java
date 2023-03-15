package com.ligavoley.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ligavoley.security.model.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEquipo;
	
	@Column(name="nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name="localidad", length = 50, nullable = false)
	private String localidad;
	
	@Column(name="sexo", length = 50, nullable = false)
	private String sexo;
	
	@Column(name="puntos")
	private Integer puntos;
	
	@Column(name="nombreClave", length = 50, nullable = false)
	private String nombreClave;
	
	@Column(name="Categoria", length = 10, nullable = false)
	private String categoria;

	@OneToOne(mappedBy = "usuario")
    private Usuario usuario;
	
	@OneToMany(mappedBy="equipo", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Jugador> jugadores = new HashSet<>();

	public Equipo() {
		super();
	}

	public Equipo(Integer idEquipo, String nombre, String localidad,String sexo, Integer puntos,String nombreClave,String categoria, Set<Jugador> jugadores, Usuario usuario) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.localidad = localidad;
		this.puntos = puntos;
		this.jugadores = jugadores;
		this.nombreClave = nombreClave;
		this.sexo = sexo;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public String getNombreClave() {
		return nombreClave;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setNombreClave(String nombreClave) {
		this.nombreClave = nombreClave;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	
	public void eliminarJugador(Jugador jugador) {
		this.jugadores.remove(jugador);
		jugador.setEquipo(null);
	}

	@JsonManagedReference
	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
	//	this.jugadores = jugadores;
	this.jugadores.addAll(jugadores);
	for(Jugador jugador: jugadores) {
		jugador.setEquipo(this);
	}
	}
}
