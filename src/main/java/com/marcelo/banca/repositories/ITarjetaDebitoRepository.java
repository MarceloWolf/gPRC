package com.marcelo.banca.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.marcelo.banca.entities.TarjetaDebito;


@Repository("tarjetaDebitoRepository")
public interface ITarjetaDebitoRepository extends JpaRepository<TarjetaDebito, Long> {
	@Query("SELECT t FROM TarjetaDebito t WHERE t.numero = (:numero)")
	public abstract TarjetaDebito findByNumero(@Param("numero") long numero);
	
	@Query("SELECT t FROM TarjetaDebito t INNER JOIN FETCH t.clienteBanca p WHERE p.dniPersona = (:dni)")
	public abstract List<TarjetaDebito> listar(@Param("dni") long dni);
	
	@Query("SELECT v FROM TarjetaDebito v ORDER BY v.numero ASC")
	public abstract List<TarjetaDebito> buscarOrdenado();
	

}
