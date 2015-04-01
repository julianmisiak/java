package com.lumina.model;

import java.util.ArrayList;

public class Persona {

	private String apellido;
	private Cuenta Cuenta;
	private Integer dni;
	private String nombre;

	public Persona(String apellido, Cuenta cuenta,
			Integer dni, String nombre) {
		super();
		this.apellido = apellido;
		Cuenta = cuenta;
		this.dni = dni;
		this.nombre = nombre;
	}

	public Persona() {
		super();
	}

	public void devolverUsuario(ArrayList<Persona> personaList, int numeroCuenta) {

		for (Persona persona : personaList) {

			if (persona.getCuenta().getNumeroDeCuenta() == numeroCuenta) {
				this.apellido = persona.apellido;
				this.dni = persona.dni;
				this.nombre = persona.nombre;
				this.Cuenta = persona.Cuenta;
			}

		}

	}
	
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}
