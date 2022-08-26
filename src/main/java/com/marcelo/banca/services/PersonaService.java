package com.marcelo.banca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.marcelo.banca.entities.Persona;
import com.marcelo.banca.repositories.IPersonaRepository;



@Service("personaService")
public class PersonaService {
	
	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;
	
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}
	
	public void save(Persona persona) {
		personaRepository.save(persona);
	}

}
