package com.lumina.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ArchivoTexto {

	public void EscibirEnArchivo(String path, String[] cadena,
			boolean opcionEscritura) throws IOException {

		FileWriter archivo = null;
		PrintWriter pw = null;

		try 
		{
			archivo = new FileWriter(path, opcionEscritura);
			pw = new PrintWriter(archivo);
			llenarArchivo(pw, cadena);
		}
		catch (IOException e) 
		{
			throw e;
		}
		finally 
		{
			try 
			{
				if (archivo != null) 
				{
					archivo.close();
				}
			}
			catch (IOException e) 
			{
				throw e;
			}

		}
	}

	private PrintWriter llenarArchivo(PrintWriter pw, String[] cadena) {

		for (int i = 0; i < cadena.length; i++)
			pw.println(cadena[i]);

		return pw;

	}

	public String[] leerArchivo(String path) throws Exception {

		File archivo = null;
		FileReader reader = null;
		BufferedReader br = null;
		ArrayList<String> cadenaList = new ArrayList<String>();
		int count = 0;

		try {
			archivo = new File(path);
			if (archivo.exists()) {
				reader = new FileReader(archivo);
			} else {
				archivo.createNewFile();//
				reader = new FileReader(archivo);
				System.out.println("se creo por default");
			}
			br = new BufferedReader(reader);

			String linea;
			while ((linea = br.readLine()) != null) {
				cadenaList.add(linea);
				count++;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (null != reader) {
					reader.close();

				}
			} catch (Exception e2) {
				System.out.println("Se rompio");
			}
		}

		String cadena[] = new String[count];
		count = 0;
		for (String cad : cadenaList)
			cadena[count++] = cad;

		return cadena;
	}
	



		

	      
	


}