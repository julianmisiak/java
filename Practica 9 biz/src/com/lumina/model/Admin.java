package com.lumina.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import com.lumina.impl.FileHandler;

public class Admin {
	private String nombre;
	private Password pass;
	private HashSet<Usuario> usuarios;
	
	
	
	public Admin (String nombre, String pass){
		this.nombre = nombre;
		this.pass = new Password(pass);
		usuarios = null;
	}
	
	public Admin (String nombre, Password pass){
		this.nombre = nombre;
		this.pass = pass;
		usuarios = null;

	}
	
	public Admin(){
		nombre = "administrador";
		pass = new Password("C4P0MaFia");
		usuarios = null;

	}
	
	public void setUsuarios (HashSet<Usuario> usuarios){
		this.usuarios = usuarios;
	}
	
	//El admin tiene guardado la lista de usarios, por lo que puede eliminar cualquier usuario 
	//y pedirle cosas a la lista. 
	
	
	//Devuelve true si el usuario pasado por parametro
	//es eliminado de la lista de usuarios.
	public Usuario borrarUsuario(String nombreUsuarioABorrar){
		for (Usuario user : this.usuarios){
			if (user.getUserName().equals(nombreUsuarioABorrar))
			{
				user.setCerrado();
				return user;
			}
		}
		return null;
	}
	
	
	public void reporteDeCuentas(){
		for (Usuario user : this.usuarios){
			System.out.println("Usuario: " + user.getUserName() + "\tNumero de cuenta: " + user.getPersona().getCuenta()
								+ "\n\tTipo: " + " " + "\n\tSaldo: " + user.getPersona().getCuenta().getSaldo() 
								+ "\n\t Estado cuenta: " + user.getPersona().getCuenta().isCuenta()
								+ "\n\tUsuario Activo = " + user.getEstado() + "\n");
			//seguir imprimiendo el estado de cada user.
		
		}
	}
	
	
	public float getPorcenCuentasCerradas() {
		int totalCuentas = this.usuarios.size();
		int cantidadCuentasCerradas = 0;

		for (Usuario user : this.usuarios) {
			if (user.getPersona().getCuenta().isCuenta()) {
				cantidadCuentasCerradas++;

			}
			
		}

		return ((float)cantidadCuentasCerradas * (float)100) / (float)totalCuentas;

	}
	
	
	public String getNombreAdministrador() {
		return this.nombre;
	}

	public Password getPasswordAdmin() {
		return this.pass;
	}
	
	public float getCuentasConDeuda(){
		int cont=0;
		for (Usuario user : this.usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 )
				cont++;			
		}		
		return ((float)cont*(float)100/(float)this.usuarios.size());
	}
	
	
	public void verCuentasSaldoPositivo(){
		System.out.println("Cuentas Saldo positivo: ");
		for (Usuario user : this.usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) > 0 ){
				System.out.println("\t" + user);
			}
		}
		
	}
	
	public Usuario getMayorDeudor(){
		BigDecimal saldoNegativo = new BigDecimal(0);
		Usuario deudor = null;
		
		for (Usuario user : this.usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(saldoNegativo) < 0  ){
				saldoNegativo = user.getPersona().getCuenta().getSaldo();
				deudor = user;
			}
			
		}
		return deudor;
	}
	
	
	public void notificarDeudorMensaje(String mensaje){
		Usuario deudor = getMayorDeudor();
		
		deudor.setMensaje(mensaje);
		
	}
	
	
	public BigDecimal getSaldoTotal() {
		BigDecimal saldoTotal = new BigDecimal(0);
		for (Usuario user : this.usuarios) {
			saldoTotal = saldoTotal.add(user.getPersona().getCuenta().getSaldo());
		}

		return saldoTotal;
	}
	
	
	public void verGananciasPerdidas(){
		BigDecimal ganancia = new BigDecimal(0);
		BigDecimal perdida = new BigDecimal(0);
		
		for (Usuario user : this.usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 ){
				perdida = perdida.add(user.getPersona().getCuenta().getSaldo());
				
			}
			else ganancia = ganancia.add(user.getPersona().getCuenta().getSaldo());
		}
		
		System.out.println( "Perdida: " + perdida);
		System.out.println( "Ganancia: " + ganancia);
		System.out.println( "Balance: " + perdida.add(ganancia));
	}
	
	
	public void cerrarCuentasSaldoNegativo(){
		
		for (Usuario user : this.usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 ){
				user.getPersona().getCuenta().cerrarCuenta();
				
			}
		
		}
		
	}
	
	public void cerrarCuenta(String usuario){
		for (Usuario user : this.usuarios){
			if (user.getUserName().equals(usuario)){
				user.getPersona().getCuenta().setCuenta(true);
			}
		}
	}
	
	
	public void importarArchivo(String path) throws Exception{

		
		FileHandler manejadorUsuario = new FileHandler();
		manejadorUsuario.cargarUsuariosDesdeArchivo(path);
		this.usuarios =  manejadorUsuario.getUsuarios();
	}
	
	
	public void exportarUsuarios(String path) throws IOException {
		FileHandler manejadorUsuario = new FileHandler();
		manejadorUsuario.setUsuarios(this.usuarios);
		manejadorUsuario.guardarListaUsuarios(path);

	}
	
	
}
