package com.marcelo.banca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "persona", uniqueConstraints = { @UniqueConstraint(columnNames = { "dniPersona" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long dniPersona;
	
	
	@Column(name = "nombre")
	@NotEmpty(message="Ingrese el nombre por favor!")
	protected String nombre;
	
	@Column(name = "apellido")
	@NotEmpty(message = "Ingrese el apellido por favor!")
	protected String apellido;
    
	

    public Persona() {}

    
    public Persona(String nombre, String apellido, long dniPersona) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dniPersona=dniPersona;
	
	}




	public long getDniPersona() {
		return dniPersona;
	}


	public void setDniPersona(long dniPersona) {
		this.dniPersona = dniPersona;
	}


	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	



	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" +dniPersona+"]";
	}

    

}
