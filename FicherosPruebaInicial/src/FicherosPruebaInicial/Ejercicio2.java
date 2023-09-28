package FicherosPruebaInicial;

import java.io.*;


public class Ejercicio2 {

    public static void main(String[] args) {
        String nombreArchivo = "txt\\numeros.txt";
        try {
            double media = calcularMedia(nombreArchivo);
            System.out.println("La media de los numeros en el archivo es: " + media);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static double calcularMedia(String nombreArchivo) throws IOException {
        BufferedReader br = null;
        double suma = 0.0;
        int contador = 0;

        try {
            br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                double numero = Double.parseDouble(linea);
                suma += numero;
                contador++;
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        if (contador == 0) {
            throw new IllegalArgumentException("El archivo está vacio, no se pueden calcular numeros.");
        }

        return suma / contador;
    }
}

