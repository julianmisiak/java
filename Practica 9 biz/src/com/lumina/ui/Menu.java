package com.lumina.ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;

import com.lumina.impl.FileHandler;
import com.lumina.model.Admin;
import com.lumina.model.CajaDeAhorro;
import com.lumina.model.CuentaCorriente;
import com.lumina.model.Password;
import com.lumina.model.Persona;
import com.lumina.model.Usuario;

public class Menu {

	static Scanner in = new Scanner(System.in);
	static FileHandler manejadorUsuario = new FileHandler();
	static Usuario userRegistrado = null;
	static Admin admin = new Admin();
	
	public static boolean logearse(){
		String nombreUsuario, password;
		
		System.out.println("Ingrese nombre de usuario: ");
		nombreUsuario = in.next();
		System.out.println("Ingrese password: ");
		password = in.next();
		
		//Crear la condicion de : si el nombre de usuario esta en la
		//lista de ADMINES, ingresar como admin
		for (Usuario user : manejadorUsuario.getUsuarios()){
			if ( user.getUserName().equals(nombreUsuario) && user.getPass().getPassDesencriptada().equals(password)){
				{
					System.out.println("Bienvenido " + user.getPersona().getNombre() + " " + user.getPersona().getApellido());
					userRegistrado = user;
				
				}
				return true;
			}
	
		}
		
		return false;
		
			
		
	}
	
	
	
	
	public static boolean registrarse() {

		String nombre, apellido, nombreUsuario, mail;
		Integer dni;
		int numeroDeCuenta = 0;
		Password pass = new Password();

		System.out.print("\nNombre: ");
		nombre = in.next();

		System.out.print("\nApellido: ");
		apellido = in.next();

		System.out.print("\nDNI: ");
		dni = in.nextInt();

		System.out.print("\nNombre de Usuario: ");
		nombreUsuario = in.next();

		System.out.print("\nMail: ");
		mail = in.next();

		System.out.print("\nPassword: ");
		pass.setPass(in.next());

		System.out.print("\nNro de cuenta: ");
		numeroDeCuenta = in.nextInt();

		System.out.println("Cuenta");
		System.out.println("1-Cuenta corriente");
		System.out.println("2-Caja de ahorro\n");
		Persona persona;
		Usuario user = null;

		switch (in.nextInt()) {
		case 1:
			persona = new Persona(apellido, new CuentaCorriente(false,
					numeroDeCuenta, new BigDecimal(0)), dni, nombre);
			user = new Usuario(persona, pass, nombreUsuario, mail);

		
		case 2:
			persona = new Persona(apellido, new CajaDeAhorro(false,
					numeroDeCuenta, new BigDecimal(0)), dni, nombre);
			user = new Usuario(persona, pass, nombreUsuario, mail);
		}

		
		try 
		{
			manejadorUsuario.addUsuario(user);
			return true;
		} catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
	

	
	
	//public static void 
	
	
	public static void main(String[] args) {
		//Abre el archivo de usuarios guardado
		try {
			manejadorUsuario.addAdmin(admin);//Cargo los admines que hay en el sistema guardados
			manejadorUsuario.cargarUsuariosDesdeArchivo();		
			manejadorUsuario.cargarAdminDeArchivo();
			//System.out.println(manejadorUsuario.getAdmines().size());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
				
		
		char opcion;
		opcion = 'k';
		while(opcion != 'X' ){
			System.out.println("	****MENU PRINCIPAL**** 	");
			
			if(userRegistrado  == null)
			{			
				System.out.println("\t(1) LOGEARSE");
				System.out.println("\t(2) REGISTRARSE");
				System.out.println("\t(3) CERRAR SESION");	
				System.out.println("\t(X) SALIR");
				opcion = in.next().toUpperCase().charAt(0);
				
				switch (opcion) {
				case '1':
					//Hacer la funcion que verifique si se logea como admin o como usuario
					while (!logearse());
					
					break;

				case '2':
					registrarse();
					break;
					
				case '6':
					try {
							System.out.print("\nIngrese DNI de usuario a borrar");			
							manejadorUsuario.borrarUsuario(in.nextInt());
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
				} 
			}
			else
			{
				System.out.println("\t\t\t\t\t\tUSUARIO: " + userRegistrado.getUserName());
				System.out.println("\t(1) RECUERAR PASS");
				System.out.println("\t(2) DEPOSITAR");
				System.out.println("\t(3) CERRAR CUENTA(si saldo es cero)");
				System.out.println("\t(4) BORRARSE");
				System.out.println("\t(X) SALIR");
				opcion = in.next().toUpperCase().charAt(0);
				
				
				switch (opcion) {
				case '1':
					System.out.println("Recuperar password");
					break;
								
				case '2':
					System.out.println("Depositar");
					break;
				
				default:
					break;
				}
			
			}	
			
		
			
		
			
			
			System.out.println("\n\n\n\n");
		}
		
		for(Usuario usuario : manejadorUsuario.getUsuarios())
			System.out.println("dni: " + usuario.toString());
		
	}
	
	
	
	public void recuperarPass(){
		
	}

}
