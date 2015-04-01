package com.lumina.impl;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

import com.lumina.model.Admin;
import com.lumina.model.Cuenta;
import com.lumina.model.Password;
import com.lumina.model.Persona;
import com.lumina.model.Usuario;


public class FileHandler {
	private HashSet<Usuario> usuarios;
	private ArrayList<Admin> admines;
	static final String PATH = "C:/Users/lsmolenski/Desktop/Cuentas y usuarios/usuarios.txt"; 
	static final String adminPATH = "C:/Users/lsmolenski/Desktop/Cuentas y usuarios/admines.txt";
	final static String separador = "-";
	
	private static Admin admin;	//El admin que estaria en uso si se logea uno.
	
	public FileHandler(){
		this.usuarios = new HashSet<Usuario>();
		
	}
	
	
	public void addAdmin(Admin admin) throws IOException{
		String[] cadenaConSeparadores = new String[1];
		
		cadenaConSeparadores[0] = (admin.getNombreAdministrador() + separador + admin.getPasswordAdmin().getPass());
		ArchivoTexto archivoAdmines = new ArchivoTexto();
		archivoAdmines.EscibirEnArchivo(adminPATH, cadenaConSeparadores,false);
		
		
	}
	
	public void cargarAdminDeArchivo() throws Exception {
		ArchivoTexto archivoAdminesPrecargados = new ArchivoTexto();
		String[] adminesCargados = archivoAdminesPrecargados.leerArchivo(adminPATH);
		this.admines = new ArrayList<Admin>();
		
		Admin admin;
		StringTokenizer token;
		Password passwordAdmin;

		for (int i = 0; i < adminesCargados.length; i++) {
			token = new StringTokenizer(adminesCargados[i], separador);
			passwordAdmin = new Password();
			String nombreAdmin = token.nextToken();
		//	System.out.println(nombreAdmin);
			passwordAdmin.setPassEncriptada(token.nextToken());
		//	System.out.println(passwordAdmin.getPassDesencriptada());
			admin = new Admin(nombreAdmin, passwordAdmin);
			
			admines.add(admin);
		}

	}
	
	public ArrayList<Admin> getAdmines(){
		return this.admines;
	}
	
	
	
	
	
	
	
	
	
	public boolean addUsuario(Usuario user) throws IOException{
		
		if(!this.usuarios.contains(user))
		{
			this.usuarios.add(user);
			String[] cadenaConSeparadores = new String[1];
			cadenaConSeparadores[0] = agregarSeparador(user);
			ArchivoTexto archivoUsuarios = new ArchivoTexto();
			archivoUsuarios.EscibirEnArchivo(PATH, cadenaConSeparadores, true);
			
		}
		else
			return false;
		
		return true;
		
	}
	
	
	
	
	
	private static String agregarSeparador(Usuario usuario){
		
		String cadenaPreparada = "";
		cadenaPreparada+= usuario.getPersona().getDni().toString() + separador;
		cadenaPreparada+= usuario.getPersona().getNombre() + separador;
		cadenaPreparada+= usuario.getPersona().getApellido() + separador;
		cadenaPreparada+= usuario.getPersona().getCuenta().getNumeroDeCuenta() + separador;
		cadenaPreparada+= usuario.getPersona().getCuenta().getSaldo().toString() + separador;
		cadenaPreparada+= usuario.getPass().getPass() + separador;
		cadenaPreparada+= usuario.getUserName() + separador;
		cadenaPreparada+= usuario.getMensaje() + separador;
		cadenaPreparada+= usuario.getEstado();
		return cadenaPreparada;
		
		
	}
	
	
	public  String [] ArrayStringPrepararGrabacionEnArchivo(){
		String cadena [] = new String[this.usuarios.size()];
		int cont = 0;
		
		for(Usuario user : this.usuarios)
			cadena[cont++] = agregarSeparador(user);
					
		return cadena;
					
	}

	public HashSet<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public void borrarUsuario(Integer dni) throws Exception{
		ArchivoTexto archivoUsuarios = new ArchivoTexto();
	
		for(Usuario user : this.usuarios)
			if(user.getPersona().getDni() == dni)
				this.usuarios.remove(user);
		
		String[] cadenaAGrabar = ArrayStringPrepararGrabacionEnArchivo();
		
		//con FALSE, reescribe el archivo por completo
		archivoUsuarios.EscibirEnArchivo(PATH, cadenaAGrabar, false);	
		
		
	}
	
	public void guardarListaUsuarios(String PATH ) throws IOException{
		ArchivoTexto archivoUsuarios = new ArchivoTexto();
		String[] cadenaAGrabar = ArrayStringPrepararGrabacionEnArchivo();
		archivoUsuarios.EscibirEnArchivo(PATH, cadenaAGrabar, false);	
	}
	
	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public void setAdmines(ArrayList<Admin> admines) {
		this.admines = admines;
	}


	public void cargarUsuariosDesdeArchivo(String  PATH) throws Exception{
		ArchivoTexto archivoUsuariosPrecargados = new ArchivoTexto();
		String[] usuariosLeidos = archivoUsuariosPrecargados.leerArchivo(PATH);
		
		Usuario usuario;
		Persona persona;
		Cuenta cuentaBancaria;
		Password pass;
		
		StringTokenizer token;
		
		for  (int i = 0; i < usuariosLeidos.length; i++){
			token = new StringTokenizer(usuariosLeidos[i],"-");
			
			usuario = new Usuario();
			persona = new Persona();
			pass = new Password();
			
			persona.setDni(Integer.parseInt(token.nextToken()));
			persona.setNombre(token.nextToken());
			persona.setApellido(token.nextToken());

			cuentaBancaria = new Cuenta(true, Integer.parseInt(token.nextToken()), new BigDecimal(token.nextToken()));
			pass.setPassEncriptada(token.nextToken());
			usuario.setUserName(token.nextToken());
			usuario.setMensaje(token.nextToken());
			
			persona.setCuenta(cuentaBancaria);
			usuario.setPersona(persona);
			usuario.setPass(pass);
			usuario.setEstado((Boolean.valueOf(token.nextToken())));
		
			this.usuarios.add(usuario);
	
		}		
		
	
		
		
	}
	
}
