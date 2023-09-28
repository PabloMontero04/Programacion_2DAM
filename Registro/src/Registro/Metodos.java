package Registro;

import java.util.ArrayList;
import java.util.Scanner;

class Usuario {
    private String nombreUsuario;
    private String correo;
    private String contrasena;

    public Usuario(String nombreUsuario, String correo, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}

class MenuRegistro {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void registrarUsuario(String nombreUsuario, String correo, String contrasena) {
        usuarios.add(new Usuario(nombreUsuario, correo, contrasena));
        System.out.println("Usuario registrado con exito.");
    }
    
    

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                System.out.println("Inicio de sesion exitoso.");
                return true;
            }
        }
        System.out.println("Usuario o contrasena incorrectos.");
        return false;
    }
    
    

    public void verListaUsuarios() {
        System.out.println("Lista de usuarios registrados:");
        for (Usuario usuario : usuarios) {
            System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
            System.out.println("Correo electronico: " + usuario.getCorreo());
            System.out.println();
        }
    }
}



