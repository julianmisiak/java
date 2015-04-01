package com.lumina.model;



public class Usuario {
	private Password pass;
	private String userName;
	private String mail;
	private String mensaje;
	private Persona persona;
	private boolean estadoActivo;
	
	
	
	public Usuario(Persona persona, Password pass, String userName,
			String mail) {
		super();
		this.persona = persona;
		this.pass = pass;
		this.userName = userName;
		this.mail = mail;
		this.mensaje = "Defoult";
		this.estadoActivo = true;
	}
	
	public Usuario (){
		
		this.persona = null;
		this.pass = null;
		this.userName = "";
		this.mail = "";
		this.mensaje = "Default";
		
	}
	
	public void setAbierto(){
		this.estadoActivo = true;
	}
	
	public void setCerrado (){
		this.estadoActivo = false;
	}
	
	public void setEstado(boolean estado){
		this.estadoActivo = estado;
	}
	
	public boolean getEstado(){
		return this.estadoActivo;
	}
	
	
	public Persona getPersona() {
		return persona;
	}





	public void setPersona(Persona persona) {
		this.persona = persona;
	}





	public Password getPass() {
		return pass;
	}





	public void setPass(Password pass) {
		this.pass = pass;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	public String getMail() {
		return mail;
	}





	public void setMail(String mail) {
		this.mail = mail;
	}





	public String getMensaje() {
		return mensaje;
	}





	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}





	


	
	
	
	@Override
	public int hashCode() {
		if (this.mail == null)
			return super.hashCode(); // si es null llama al hashCode() del
										// object

		return this.mail.hashCode(); // este retorna al equals
	}


	public boolean equals(Object obj) {
	Usuario otroUser = (Usuario)obj;
		
		return (	this.persona.getDni() == otroUser.persona.getDni() && 
					this.persona.getNombre().equals(otroUser.persona.getNombre()) &&
					this.mail.equals(otroUser.mail) &&
					this.persona.getCuenta().getNumeroDeCuenta() == otroUser.persona.getCuenta().getNumeroDeCuenta()
				
				);

	}





	@Override
	public String toString() {
		return "Usuario [persona=" + persona.getNombre() + ", pass=" + pass.getPassDesencriptada() + ", userName="
				+ userName + ", mail=" + mail + ", mensaje=" + mensaje + "  " + "Usuario Activo= " + estadoActivo+ "]";
	}
	
	
	
	
	

}
