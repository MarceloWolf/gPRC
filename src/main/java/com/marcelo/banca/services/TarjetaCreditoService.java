package com.marcelo.banca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import com.marcelo.banca.entities.TarjetaCredito;
import com.marcelo.banca.repositories.ITarjetaCreditoRepository;



@Service("tarjetaCreditoService")
public class TarjetaCreditoService {
	
	@Autowired
	@Qualifier("tarjetaCreditoRepository")
	private ITarjetaCreditoRepository tarjetaCreditoRepository;
	
	public List<TarjetaCredito> getAll() {
		return tarjetaCreditoRepository.findAll();
	}
	
	public List<TarjetaCredito> buscOrd() {
		return tarjetaCreditoRepository.buscarOrdenado();
	}


	public TarjetaCredito buscar(long id) {
		return tarjetaCreditoRepository.findById(id).orElse(null);
	}
	
	public List<TarjetaCredito> listar(long dniPersona) {
		return tarjetaCreditoRepository.listar(dniPersona);
	}
	

	public void save(TarjetaCredito tarjetaCredito) {
		tarjetaCreditoRepository.save(tarjetaCredito);
	}


	public void eliminar(long id) {
		tarjetaCreditoRepository.deleteById(id);
	}
	

	public TarjetaCredito findByNumero(@Param("numero") long numero) {
		return tarjetaCreditoRepository.findByNumero(numero);
	};


}