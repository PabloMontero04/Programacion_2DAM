package FuncionesLambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1_FiltradoDeDatos {

    public static void main(String[] args) {
        // Lista de números enteros
        List<Integer> numeros = Arrays.asList(23, 17, 4, 13, 8, 19, 16, 7, 29, 31);

        // Filtrar números primos utilizando stream y lambda
        List<Integer> numerosPrimosOrdenados = numeros.stream()
                .filter(Ejercicio1_FiltradoDeDatos::esPrimo)
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());

        // Mostrar los resultados
        System.out.println("Números primos ordenados de mayor a menor: " + numerosPrimosOrdenados);
    }

    // Función para verificar si un número es primo
    private static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}

