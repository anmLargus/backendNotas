package com.app.backendNotas.security.models;

import java.io.Serializable;

//Equivale a la clase AuthenticationReq de jcabello@itana
public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L; // No comprendo la función de esto
	
	private String usuario;
	private String clave;

	//Constructor
	public Login(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}
	
	//Getters y Setters

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}	

}
