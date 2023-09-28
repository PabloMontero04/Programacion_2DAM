package Registro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MenuRegistro menu = new MenuRegistro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Ver lista de usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre de usuario: ");
                    String nombreUsuario = scanner.next();
                    System.out.print("Ingrese correo electronico: ");
                    String correo = scanner.next();
                    System.out.print("Ingrese contrasena: ");
                    String contrasena = scanner.next();
                    menu.registrarUsuario(nombreUsuario, correo, contrasena);
                    break;
                case 2:
                    System.out.print("Ingrese nombre de usuario: ");
                    String usuarioLogin = scanner.next();
                    System.out.print("Ingrese contrasena: ");
                    String contrasenaLogin = scanner.next();
                    menu.iniciarSesion(usuarioLogin, contrasenaLogin);
                    break;
                case 3:
                    menu.verListaUsuarios();
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
    }
}


