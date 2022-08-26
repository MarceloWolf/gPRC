package com.marcelo.banca.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marcelo.banca.entities.ClienteBanca;




@Repository("clienteBancaRepository")
public interface IClienteBancaRepository extends JpaRepository<ClienteBanca, Long> {
	@Query("SELECT p FROM Persona p WHERE p.dniPersona = (:dni)")
	public abstract ClienteBanca findByDni(@Param("dni") long dni);
	

}
