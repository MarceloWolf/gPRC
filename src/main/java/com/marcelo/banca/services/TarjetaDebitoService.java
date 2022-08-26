package com.marcelo.banca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import com.marcelo.banca.entities.TarjetaDebito;
import com.marcelo.banca.repositories.ITarjetaDebitoRepository;



@Service("tarjetaDebitoService")
public class TarjetaDebitoService {
	
	@Autowired
	@Qualifier("tarjetaDebitoRepository")
	private ITarjetaDebitoRepository tarjetaDebitoRepository;
	
	public List<TarjetaDebito> getAll() {
		return tarjetaDebitoRepository.findAll();
	}
	
	public List<TarjetaDebito> buscOrd() {
		return tarjetaDebitoRepository.buscarOrdenado();
	}


	public TarjetaDebito buscar(long id) {
		return tarjetaDebitoRepository.findById(id).orElse(null);
	}
	
	public List<TarjetaDebito> listar(long dniPersona) {
		return tarjetaDebitoRepository.listar(dniPersona);
	}
	

	public void save(TarjetaDebito tarjetaDebito) {
		tarjetaDebitoRepository.save(tarjetaDebito);
	}


	public void eliminar(long id) {
		tarjetaDebitoRepository.deleteById(id);
	}
	

	public TarjetaDebito findByNumero(@Param("numero") long numero) {
		return tarjetaDebitoRepository.findByNumero(numero);
	};


}