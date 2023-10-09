package Cifrado;

import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nombre del archivo donde se almacenará la contraseña
        String nombreArchivo = "contrasena.txt";

        // Verificar si el archivo ya existe
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("No se ha encontrado la contrasena. Por favor, configurela.");
            System.out.print("Ingrese una nueva contrasena: ");
            String nuevaContrasena = scanner.nextLine();

            // Escribir la contrasena en el archivo
            try {
                FileWriter fileWriter = new FileWriter(nombreArchivo);
                fileWriter.write(nuevaContrasena);
                fileWriter.close();
                System.out.println("Contrasena configurada correctamente.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo.");
                e.printStackTrace();
            }
        } else {
            System.out.print("Ingrese la contrasena: ");
            String contrasenaIngresada = scanner.nextLine();

            // Leer la contrasena desde el archivo
            try {
                FileReader fileReader = new FileReader(nombreArchivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String contrasenaAlmacenada = bufferedReader.readLine();
                bufferedReader.close();

                // Verificar si la contrasena ingresada coincide con la almacenada
                if (contrasenaIngresada.equals(contrasenaAlmacenada)) {
                    System.out.println("Contrasena correcta. Acceso permitido.");
                } else {
                    System.out.println("Contrasena incorrecta. Acceso denegado.");
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo.");
                e.printStackTrace();
            }
        }
    }
}


