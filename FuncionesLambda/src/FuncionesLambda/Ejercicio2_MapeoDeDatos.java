package FuncionesLambda;

import java.util.Arrays;
import java.util.List;


public class Ejercicio2_MapeoDeDatos {
    public static void main(String[] args) {
        // Lista de nombres de personas
        List<String> nombres = Arrays.asList("Juan", "María", "Carlos", "Ana", "Luis");

        // Mapeo de cada nombre a su longitud y mostrar la lista resultante
        nombres.stream()
               .map(String::length)
               .forEach(System.out::println);

        // Calcular y mostrar la suma de todas las longitudes
        int sumaLongitudes = nombres.stream()
                                    .map(String::length)
                                    .reduce(0, Integer::sum);

        System.out.println("Suma de longitudes: " + sumaLongitudes);
    }
}
