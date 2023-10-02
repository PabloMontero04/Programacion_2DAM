package Iterador_Hash;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class Iterador_Hash<T> {
    private Hashtable<String, T> hashtable = new Hashtable<String, T>();

    public void insertarDato(String key, T value) {
        hashtable.put(key, value);
    }

    public void borrarDato(String key) {
        hashtable.remove(key);
    }

    public void mostrarDatos() {
        Iterator<String> iterator = hashtable.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            T value = hashtable.get(key);
            System.out.println("Materia: " + key + ", Calificacion: " + value);
        }
    }

    public static void main(String[] args) {
        Iterador_Hash<String> materias = new Iterador_Hash<String>();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Insertar materia");
            System.out.println("2. Mostrar calificaciones");
            System.out.println("3. Borrar materia");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la materia: ");
                    String materia = scanner.nextLine();
                    System.out.print("Ingrese la calificacion: ");
                    String calificacion = scanner.nextLine();
                    materias.insertarDato(materia, calificacion);
                    System.out.println("Materia insertada.");
                    break;

                case 2:
                    System.out.println("Calificaciones de materias:");
                    materias.mostrarDatos();
                    break;

                case 3:
                    System.out.print("Ingrese el nombre de la materia a eliminar: ");
                    String materiaAEliminar = scanner.nextLine();
                    materias.borrarDato(materiaAEliminar);
                    System.out.println("Materia eliminada.");
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



