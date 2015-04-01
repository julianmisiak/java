package com.lumina.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import com.lumina.impl.FileHandler;

public class Admin {
	private String nombre;
	private Password pass;

	
	
	
	public Admin (String nombre, String pass){
		this.nombre = nombre;
		this.pass = new Password(pass);
		
	}
	
	public Admin (String nombre, Password pass){
		this.nombre = nombre;
		this.pass = pass;
		

	}
	
	public Admin(){
		nombre = "administrador";
		pass = new Password("C4P0MaFia");
		

	}
	
	/*public void setUsuarios (HashSet<Usuario> usuarios){
		this.usuarios = usuarios;
	}*/
	
	//El admin tiene guardado la lista de usarios, por lo que puede eliminar cualquier usuario 
	//y pedirle cosas a la lista. 
	
	
	//Devuelve true si el usuario pasado por parametro
	//es eliminado de la lista de usuarios.
	
	public boolean borrarUsuario(String nombreUsuarioABorrar, HashSet<Usuario> usuarios){
		Usuario userACambiar;
		for (Usuario user : usuarios){
			if (user.getUserName().equals(nombreUsuarioABorrar))
			{
				user.setAbierto();
				return true;
			}
		}
		
		
	
		
			return false;
	}
	
	
	public void reporteDeCuentas(HashSet<Usuario> usuarios){
		for (Usuario user : usuarios){
			System.out.println("Usuario: " + user.getUserName() + "\tNumero de cuenta: " + user.getPersona().getCuenta()
								+ "\n\tTipo: " + " " + "\n\tSaldo: " + user.getPersona().getCuenta().getSaldo() 
								+ "\n\t Estado cuenta: " + user.getPersona().getCuenta().isCuenta()
								+ "\n\tUsuario Activo = " + user.getEstado() + "\n");
			//seguir imprimiendo el estado de cada user.
		
		}
	}
	
	
	public float getPorcenCuentasCerradas(HashSet<Usuario> usuarios) {
		int totalCuentas = usuarios.size();
		int cantidadCuentasCerradas = 0;

		for (Usuario user : usuarios) {
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
	
	public float getCuentasConDeuda(HashSet<Usuario> usuarios){
		int cont=0;
		for (Usuario user : usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 )
				cont++;			
		}		
		return ((float)cont*(float)100/(float) usuarios.size());
	}
	
	
	public void verCuentasSaldoPositivo(HashSet<Usuario> usuarios){
		System.out.println("Cuentas Saldo positivo: ");
		for (Usuario user : usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) > 0 ){
				System.out.println("\t" + user);
			}
		}
		
	}
	
	public Usuario getMayorDeudor(HashSet<Usuario> usuarios){
		BigDecimal saldoNegativo = new BigDecimal(0);
		Usuario deudor = null;
		
		for (Usuario user : usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(saldoNegativo) < 0  ){
				saldoNegativo = user.getPersona().getCuenta().getSaldo();
				deudor = user;
			}
			
		}
		return deudor;
	}
	
	
	public void notificarDeudorMensaje(String mensaje, HashSet<Usuario> usuarios){
		Usuario deudor = getMayorDeudor(usuarios);
		
		deudor.setMensaje(mensaje);
		
	}
	
	
	public BigDecimal getSaldoTotal(HashSet<Usuario> usuarios) {
		BigDecimal saldoTotal = new BigDecimal(0);
		for (Usuario user : usuarios) {
			saldoTotal = saldoTotal.add(user.getPersona().getCuenta().getSaldo());
		}

		return saldoTotal;
	}
	
	
	public void verGananciasPerdidas(HashSet<Usuario> usuarios){
		BigDecimal ganancia = new BigDecimal(0);
		BigDecimal perdida = new BigDecimal(0);
		
		for (Usuario user : usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 ){
				perdida = perdida.add(user.getPersona().getCuenta().getSaldo());
				
			}
			else ganancia = ganancia.add(user.getPersona().getCuenta().getSaldo());
		}
		
		System.out.println( "Perdida: " + perdida);
		System.out.println( "Ganancia: " + ganancia);
		System.out.println( "Balance: " + perdida.add(ganancia));
	}
	
	
	public void cerrarCuentasSaldoNegativo(HashSet<Usuario> usuarios){
		
		for (Usuario user : usuarios){
			if (user.getPersona().getCuenta().getSaldo().compareTo(new BigDecimal(0)) < 0 ){
				user.getPersona().getCuenta().cerrarCuenta();
				
			}
		
		}
		
	}
	
	public void cerrarCuenta(String usuario, HashSet<Usuario> usuarios){
		for (Usuario user : usuarios){
			if (user.getUserName().equals(usuario)){
				user.getPersona().getCuenta().setCuenta(true);
			}
		}
	}
	
	
	public void importarArchivo(String path, HashSet<Usuario> usuarios) throws Exception{

		
		FileHandler manejadorUsuario = new FileHandler();
		manejadorUsuario.cargarUsuariosDesdeArchivo(path);
		usuarios =  manejadorUsuario.getUsuarios();
	}
	
	
	public void exportarUsuarios(String path, HashSet<Usuario> usuarios) throws IOException {
		FileHandler manejadorUsuario = new FileHandler();
		manejadorUsuario.setUsuarios(usuarios);
		manejadorUsuario.guardarListaUsuarios(path);

	}
	
	
}
