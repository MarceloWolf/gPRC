package com.marcelo.banca.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.marcelo.banca.entities.ClienteBanca;

@Entity
@Table(name = "tarjetaCredito")
public class TarjetaCredito {
	
	@Id	
	@Column(name = "idTarjeta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Pattern(regexp="^[0-9]{16}", message="Formato invalido. El número consta de 16 dígitos")
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 16 dígitos al frente de la tarjeta")
    private long idTarjeta;
	
	
	
	@Column(name = "numero")
	//@Pattern(regexp="^[0-9]{16}", message="Formato invalido. El número consta de 16 dígitos")
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 16 dígitos al frente de la tarjeta")
    private long numero;
	
	@Column(name = "empresa")
	@NotEmpty(message = "Ingrese la empresa emisora")
    private String empresa;
	
	@Column(name = "mes")	
	//@Pattern(regexp="^[0-9]{2}", message="Formato invalido. El número consta de 2 dígitos")
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 2 dígitos")
    private int mes;

	@Column(name = "anio")	
	//@Pattern(regexp="^[0-9]{2}", message="Formato invalido. El número consta de 2 dígitos")
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 2 dígitos")
    private int anio;
	
	@Column(name = "codSeg")	
	//@Pattern(regexp="^[0-9]{3}", message="Formato invalido. El número consta de 3 dígitos")
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 3 dígitos")
    private int codSeg;
	 
	@ManyToOne
	@JoinColumn(name = "cliente_dniPersona")
    private ClienteBanca clienteBanca;
	
	@Column(name = "limite")
	@NotNull(message = "Ingrese el límite disponible")
    private long limiteMensual;
    
    public TarjetaCredito() {}

	public TarjetaCredito(long numero,String empresa, int mes, int anio, int codSeg, ClienteBanca clienteBanca, long limiteMensual) {
		this.numero = numero;
		this.empresa = empresa;
		this.mes = mes;
		this.anio = anio;
		this.codSeg =codSeg;
		this.clienteBanca = clienteBanca;
		this.limiteMensual = limiteMensual;
		
	}


	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getCodSeg() {
		return codSeg;
	}

	public void setCodSeg(int codSeg) {
		this.codSeg = codSeg;
	}

	
	public ClienteBanca getClienteBanca() {
		return clienteBanca;
	}

	public void setClienteBanca(ClienteBanca clienteBanca) {
		this.clienteBanca = clienteBanca;
	}


	public long getLimiteMensual() {
		return limiteMensual;
	}

	public void setLimiteMensual(long limiteMensual) {
		this.limiteMensual = limiteMensual;
	}

	@Override
	public String toString() {
		return "TarjetaCredito [numero=" + numero + ", empresa=" + empresa + ", mes=" + mes + ", anio=" + anio
				+ ", codSeg=" + codSeg +  clienteBanca + " montoDisponible=" + limiteMensual + "]";
	}

	

	
    
    
    
    

}
