package com.marcelo.banca.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.marcelo.banca.entities.TarjetaCredito;


@Repository("tarjetaCreditoRepository")
public interface ITarjetaCreditoRepository extends JpaRepository<TarjetaCredito, Long> {
	@Query("SELECT t FROM TarjetaCredito t WHERE t.numero = (:numero)")
	public abstract TarjetaCredito findByNumero(@Param("numero") long numero);
	
	@Query("SELECT t FROM TarjetaCredito t INNER JOIN FETCH t.clienteBanca p WHERE p.dniPersona = (:dni)")
	public abstract List<TarjetaCredito> listar(@Param("dni") long dni);
	
	@Query("SELECT v FROM TarjetaCredito v ORDER BY v.numero ASC")
	public abstract List<TarjetaCredito> buscarOrdenado();
	

}
