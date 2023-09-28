package Conexion_BD;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("MENU:");
            System.out.println("1. Alta Asistente");
            System.out.println("2. Cambiar DNI");
            System.out.println("3. Mostrar Datos");
            System.out.println("4. Eliminar Asistente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos del asistente:");
                    System.out.print("DNI: ");
                    String dni = scanner.next();
                    System.out.print("Nombre: ");
                    String nombre = scanner.next();
                    System.out.print("Centro: ");
                    String centro = scanner.next();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    Conexion_BD.AltaAsistente(dni, nombre, centro, edad);
                    break;
                case 2:
                    System.out.print("Ingrese el DNI actual: ");
                    String dniActual = scanner.next();
                    System.out.print("Ingrese el nuevo DNI: ");
                    String nuevoDni = scanner.next();
                    Conexion_BD.CambiarDni(dniActual, nuevoDni);
                    break;
                case 3:
                    Conexion_BD.MostrarDatos();
                    break;
                case 4:
                    Conexion_BD.eliminarAsistente();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
}
