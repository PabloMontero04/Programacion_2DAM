package FicherosPruebaInicial;

import java.io.*;

public class Ejercicio3 {

	public static void main(String[] args) {
		try {
		    String texto;
		    File ficheroEntero = new File("txt\\lectura.txt");
		    FileReader fr = new FileReader(ficheroEntero);
		    BufferedReader bf = new BufferedReader(fr);

		    /* Archivo a escribir */
		    FileWriter ficheroVacio = new FileWriter("txt\\escritura.txt");
		    PrintWriter pw = new PrintWriter(ficheroVacio);
		    
		    /* Leer y escribir */
		    while ((texto = bf.readLine()) != null) {	        
		        pw.println(texto);
		    }
		    /* Cerrar archivos */
		    if (bf != null) {
		        bf.close();
		    }
		    if (fr != null) {
		        fr.close();
		    }
		    if (pw != null) {
		        pw.close();
		    }
		    if (ficheroVacio != null) {
		        ficheroVacio.close();
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}
 
	}
}

