package com.lumina.model;

import java.util.StringTokenizer;

public class Password {

	private String pass;

	public void encriptarPass() {
		if (pass.length() < 128) {
			for (int i = pass.length(); i < 128; i++) {
				pass += "º";

			}

		}
		reemplazoDeCaracteres();
	}

	// ii. Reemplazar las vocales minúsculas con a=!; e=@; i=); o=&;u= #
	// iii. Reemplazar las vocales mayúsculas con A=$; E=- I=_; O=}; U=}

	// public void reemplazoDeCaracteres(){ -> Mal
	// pass.replaceAll("!", "a");
	// pass.replaceAll("@", "e");
	// pass.replaceAll(")", "i");
	// pass.replaceAll("&", "o");
	// pass.replaceAll("#", "u");
	// pass.replaceAll("$", "A");
	// pass.replaceAll("-", "E");
	// pass.replaceAll("_", "I");
	// pass.replaceAll("\"{\"", "O");
	// pass.replaceAll("\"}\"", "U");
	//
	// }
	//
	public Password() {
	}

	public void invertirCadena() {
		StringBuilder builder = new StringBuilder(pass);
		this.pass = builder.reverse().toString();

	}

	public void reemplazoDeCaracteres() {
		pass = pass.replaceAll("a", "!");
		pass = pass.replaceAll("e", "@");
		pass = pass.replaceAll("i", ")");
		pass = pass.replaceAll("o", "&");
		pass = pass.replaceAll("u", "#");
		pass = pass.replaceAll("A", "$");
		pass = pass.replaceAll("E", "-");
		pass = pass.replaceAll("I", "_");
		pass = pass.replaceAll("O", "{");
		pass = pass.replaceAll("U", "}");

	}

	public Password(String p1) {
		this.setPass(p1);

	}

	@Override
	public String toString() {
		return "Password [Pass=" + pass + "]";
	}

	// ---------------------------------------------------------------------

	public void agregarPipeEnPassword() {

		String passPipe = "";

		for (int i = 0; i < this.pass.length(); i++) {
			passPipe += this.pass.charAt(i);
			passPipe += "|";
		}

		this.pass = passPipe;

	}

	public void quitarPipePasswordTOKENISER() {

		String passSinPipe = "";
		StringTokenizer token = new StringTokenizer(this.pass, "|");

		while (token.hasMoreTokens())
			passSinPipe += token.nextToken();

		this.pass = passSinPipe;
	}

	public void cambiarAasciiSinModificarCaracEspeciales() {

		String cadAscii = "";
		for (int i = 0; i < this.pass.length(); i++) {
			if (this.pass.charAt(i) == 'º' || this.pass.charAt(i) == '|')
				cadAscii += this.pass.charAt(i);
			else
				cadAscii += (int) this.pass.charAt(i);

		}

		this.pass = cadAscii;

	}

	public void cambiarDeAsciiAcaracteres() {
		String cadAscii = "";
		String caracter = "";

		for (int i = 0; i < this.pass.length(); i++) {

			if (this.pass.charAt(i) != 'º' && this.pass.charAt(i) != '|')
				caracter += this.pass.charAt(i);
			else if (caracter != "") {
				cadAscii += (char) Integer.parseInt(caracter);
				caracter = "";
			}

		}

		this.pass = cadAscii;

	}

	public void setPass(String pass) {
		this.pass = pass;
		this.agregarPipeEnPassword();
		this.cambiarAasciiSinModificarCaracEspeciales();
		this.invertirCadena();

	}

	public void setPassEncriptada(String pass) {
		this.pass = pass;
	

	}
	
	public String getPass() {
		return this.pass;
	}

	public String getPassDesencriptada(){
		Password temp = new Password();
		temp.pass = this.pass;
		
		temp.invertirCadena();
		temp.cambiarDeAsciiAcaracteres();
	
		return temp.pass;
		
	}
	
	public static void main(String[] args) {
		Password p1 = new Password("YoCoMOenelRIO");



		System.out.println(p1.toString());
		System.out.println(p1.getPass());
		System.out.println("Descencriptada" + p1.getPassDesencriptada());
	}

}
