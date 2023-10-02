package Iterador_Hash;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map;

public class Iterador_Hash<T> {
    private Hashtable<String, T> hashtable = new Hashtable<String, T>();

    private int capacidad = 10; 
    private int tamaño = 0; 

    public void insertarDato(String key) {
        if (tamaño >= capacidad) {
            System.out.println("La tabla hash esta llena. No se puede insertar mas elementos.");
            return;
        }

        int indice = hash(key);

        while (hashtable.containsKey(Integer.toString(indice))) {
            indice = (indice + 1) % capacidad;
        }

        hashtable.put(Integer.toString(indice), (T) key);
        tamaño++;
    }

    public void borrarDato(String key) {
        int indice = buscarIndice(key);

        if (indice != -1) {
            hashtable.remove(Integer.toString(indice));
            tamaño--;
        }
    }

    public void mostrarDatos() {
        for (int i = 0; i < capacidad; i++) {
            String key = Integer.toString(i);
            T value = hashtable.get(key);
            if (value != null) {
                System.out.println("Indice: " + key + ", Numero: " + value);
            } else {
                System.out.println("Indice: " + key + ", Numero: 0");
            }
        }
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i)) % capacidad;
        }
        return hash;
    }

    private int buscarIndice(String key) {
        int indice = hash(key);

        while (hashtable.containsKey(Integer.toString(indice))) {
            if (hashtable.get(Integer.toString(indice)).equals(key)) {
                return indice;
            }
            indice = (indice + 1) % capacidad;
        }

        return -1; 
    }

    public static void main(String[] args) {
        Iterador_Hash<String> numerosCodificados = new Iterador_Hash<String>();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Insertar numero");
            System.out.println("2. Mostrar tabla hash completa");
            System.out.println("3. Borrar numero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (numerosCodificados.tamaño < 10) {
                        System.out.print("Ingrese el numero: ");
                        String numero = scanner.nextLine();
                        numerosCodificados.insertarDato(numero);
                        System.out.println("Numero insertado con codificacion lineal.");
                    } else {
                        System.out.println("La tabla hash está llena. No se pueden insertar más elementos.");
                    }
                    break;

                case 2:
                    System.out.println("Tabla Hash completa:");
                    numerosCodificados.mostrarDatos();
                    break;

                case 3:
                    System.out.print("Ingrese el numero a eliminar: ");
                    String numeroAEliminar = scanner.nextLine();
                    numerosCodificados.borrarDato(numeroAEliminar);
                    System.out.println("Numero eliminado.");
                    break;

                case 4:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Gracias por usar el programa.");
    }
}
