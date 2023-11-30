package FuncionesLambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio5_ProgramaciónConcurrente {

    public static void main(String[] args) {
        // Crear una lista grande de números enteros
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 30);

        // Número de partes en las que se dividirá la lista
        int numPartes = 4;

        // Dividir la lista en partes
        List<List<Integer>> particiones = dividirLista(numeros, numPartes);

        // Utilizar ForkJoinPool para realizar la suma en paralelo
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> resultadosParciales = pool.invoke(new TareaSuma(particiones));

        // Mostrar resultados parciales
        System.out.println("Resultados parciales: " + resultadosParciales);

        // Sumar los resultados parciales
        int resultadoFinal = resultadosParciales.stream().mapToInt(Integer::intValue).sum();

        // Mostrar resultado final
        System.out.println("Resultado final: " + resultadoFinal);
    }

    // Método para dividir la lista en partes
    private static List<List<Integer>> dividirLista(List<Integer> lista, int numPartes) {
        int tamano = lista.size();
        int tamanoParticion = (int) Math.ceil((double) tamano / numPartes);

        return IntStream.range(0, numPartes)
                .mapToObj(i -> lista.subList(i * tamanoParticion, Math.min((i + 1) * tamanoParticion, tamano)))
                .collect(Collectors.toList());
    }

    // Clase para realizar la suma en paralelo usando ForkJoinTask
    static class TareaSuma extends RecursiveTask<List<Integer>> {
        private final List<List<Integer>> particiones;

        TareaSuma(List<List<Integer>> particiones) {
            this.particiones = particiones;
        }

        @Override
        protected List<Integer> compute() {
            return particiones.parallelStream()
                    .map(particion -> particion.stream().mapToInt(Integer::intValue).sum())
                    .collect(Collectors.toList());
        }
    }
}
