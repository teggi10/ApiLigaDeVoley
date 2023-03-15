package com.ligavoley.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ligavoley.dto.EquipoDto;
import com.ligavoley.dto.Mensaje;
import com.ligavoley.exceptions.CustomException;
import com.ligavoley.model.Equipo;
import com.ligavoley.repository.EquipoRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class EquipoService {

	 @Autowired
	    EquipoRepository equipoRepository;

	    public List<Equipo> list(){
	        return equipoRepository.findAll();
	    }

	    public Equipo getOne(int id){
	        return equipoRepository.findById(id).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese equipo no existe"));
	    }

	    public Equipo getByNombre(String nombre){
			return equipoRepository.findByNombre(nombre).orElseThrow( () -> new CustomException(HttpStatus.NOT_FOUND, "Ese equipo no existe"));
	    }

	    public Mensaje  save(EquipoDto equipoDto){
			if(equipoRepository.existsByNombre(equipoDto.getNombre()))
			throw new CustomException(HttpStatus.BAD_REQUEST, "Ese nombre ya existe");
			Equipo equipo = new Equipo(null,equipoDto.getNombre(),equipoDto.getLocalidad(),
			equipoDto.getSexo(),equipoDto.getPuntos(),equipoDto.getNombreClave(),equipoDto.getCategoria(),equipoDto.getJugadores(),equipoDto.getUsuario());
	        equipoRepository.save(equipo);
			return new Mensaje("Equipo creado");
	    }
	   
		public Mensaje update(int id, EquipoDto equipoDto){
			if(!equipoRepository.existsById(id))
			throw new CustomException(HttpStatus.BAD_REQUEST, "Ese equipo no existe");
			Optional<Equipo> eOptional = equipoRepository.findByNombre(equipoDto.getNombre());
			if(eOptional.isPresent() && eOptional.get().getIdEquipo() != id)
				throw new CustomException(HttpStatus.BAD_REQUEST, "Ese nombre ya existe");
			Equipo equipo = getOne(id);
			equipo.setNombre(equipoDto.getNombre());
			equipo.setLocalidad(equipoDto.getLocalidad());
			equipo.setJugadores(equipoDto.getJugadores());
			equipo.setPuntos(equipoDto.getPuntos());
			equipo.setSexo(equipoDto.getSexo());
			equipo.setNombreClave(equipoDto.getNombreClave());
			equipo.setCategoria(equipoDto.getCategoria());
			equipoRepository.save(equipo);

			return new Mensaje("Equipo actualizado");
		}

	    public Mensaje delete(int id){
			Equipo equipo = getOne(id);
	        equipoRepository.delete(equipo);
			return new Mensaje("Equipo eliminado");
	    }

	    public boolean existsById(int id){
	        return equipoRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return equipoRepository.existsByNombre(nombre);
	    }
}
