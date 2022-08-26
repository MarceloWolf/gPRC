package com.marcelo.banca.entities;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.marcelo.banca.entities.TarjetaCredito;
import com.marcelo.banca.entities.TarjetaDebito;


@Entity
@Table(name = "clienteBanca")
@PrimaryKeyJoinColumn(referencedColumnName = "dniPersona")
public class  ClienteBanca extends Persona {
	
	

	
	
	/*@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarjetaCredito_numero", nullable = true)
	//private Set<TarjetaCredito> tarjetaCredito;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarjetaDebito_numero", nullable = true)
	private Set<TarjetaDebito> tarjetaDebito;*/
	
	public ClienteBanca() {}
	
	


	public ClienteBanca(String nombre, String apellido, int dniPersona) {
		super(nombre, apellido, dniPersona);
		
	
	}



	public Set<TarjetaCredito> tarjetaCredito() {
		return tarjetaCredito();
	}


	/*public void setTarjetaCredito(Set<TarjetaCredito> tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}
	
	public boolean agregarTC(TarjetaCredito tarjetaCreditos) {
		return tarjetaCredito.add(tarjetaCreditos);
	}*/
	
	//--------------------------------------------
	
	public Set<TarjetaDebito> tarjetaDebito() {
		return tarjetaDebito();
	}


	/*public void setTarjetaDebito(Set<TarjetaDebito> tarjetaDebito) {
		this.tarjetaDebito = tarjetaDebito;
	}
	
	public boolean agregarTD(TarjetaDebito tarjetaDebitos) {
		return tarjetaDebito.add(tarjetaDebitos);
	}
	*/

}