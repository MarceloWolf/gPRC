package com.marcelo.banca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.marcelo.banca.entities.ClienteBanca;
import com.marcelo.banca.repositories.IClienteBancaRepository;





@Service("clienteBancaService")
public class ClienteBancaService {
	
	@Autowired
	@Qualifier("clienteBancaRepository")
	private IClienteBancaRepository clienteBancaRepository;
	
	public List<ClienteBanca> getAll() {
		return clienteBancaRepository.findAll();
	}


	public void save(ClienteBanca clienteBanca) {
		clienteBancaRepository.save(clienteBanca);
	}
	
	public ClienteBanca findByDni(@Param("dni") long dni) {
		return clienteBancaRepository.findByDni(dni);
	}
	
	public ClienteBanca buscar(long id) {
		return clienteBancaRepository.findById(id).orElse(null);
	}
	

	public void eliminar(long id) {
		clienteBancaRepository.deleteById(id);
	}

}
