package Cifrado;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nombre del archivo donde se almacenará el hash de la contraseña
        String nombreArchivo = "contrasena_cifrada.txt";

        // Verificar si el archivo ya existe
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("No se ha encontrado el hash de la contraseña. Por favor, configurela.");
            System.out.print("Ingrese una nueva contraseña: ");
            String nuevaContrasena = scanner.nextLine();

            // Generar el hash de la contraseña y almacenarlo en el archivo
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(nuevaContrasena.getBytes());

                // Convertir el hash en una representación hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }

                FileWriter fileWriter = new FileWriter(nombreArchivo);
                fileWriter.write(sb.toString());
                fileWriter.close();
                System.out.println("Hash de contraseña configurado correctamente.");
            } catch (NoSuchAlgorithmException | IOException e) {
                System.err.println("Error al generar y escribir el hash de la contraseña.");
                e.printStackTrace();
            }
        } else {
            System.out.print("Ingrese la contraseña: ");
            String contrasenaIngresada = scanner.nextLine();

            // Leer el hash de la contraseña desde el archivo
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(contrasenaIngresada.getBytes());

                // Convertir el hash en una representación hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }

                FileReader fileReader = new FileReader(nombreArchivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String hashAlmacenado = bufferedReader.readLine();
                bufferedReader.close();

                // Verificar si el hash de la contraseña ingresada coincide con el almacenado
                if (sb.toString().equals(hashAlmacenado)) {
                    System.out.println("Contraseña correcta. Acceso permitido.");
                } else {
                    System.out.println("Contraseña incorrecta. Acceso denegado.");
                }
            } catch (NoSuchAlgorithmException | IOException e) {
                System.err.println("Error al generar y comparar el hash de la contraseña.");
                e.printStackTrace();
            }
        }
    }
}

