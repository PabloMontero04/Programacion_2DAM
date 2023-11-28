package Mochila;

import java.util.ArrayList;
import java.util.List;

public class MochilaBacktracking {
	
	
	
	// Método principal para encontrar soluciones al problema de la mochila
	public static List<List<Integer>> encontrarSoluciones(int[] pesos, int capacidad) {
		List<List<Integer>> soluciones = new ArrayList<>();
		List<Integer> solucionActual = new ArrayList<>();

		backtrack(0, pesos, capacidad, 0, 0, solucionActual, soluciones);

		return soluciones;
	}
	

	// Función recursiva para realizar el backtracking y encontrar las soluciones
	private static void backtrack(int indice, int[] pesos, int capacidad, int pesoActual, int limitePeso, List<Integer> solucionActual, List<List<Integer>> soluciones) {
		if (indice == pesos.length) {
			soluciones.add(new ArrayList<>(solucionActual));
			return;
		}

		// Prueba si se puede agregar el elemento actual a la mochila
		if (pesoActual + pesos[indice] <= capacidad) {
			solucionActual.add(indice);
			// Llama a la función recursivamente con el elemento actual incluido
			backtrack(indice + 1, pesos, capacidad, pesoActual + pesos[indice], limitePeso, solucionActual, soluciones);
			solucionActual.remove(solucionActual.size() - 1);
		}

		// Llama a la función recursivamente sin el elemento actual
		if (limitePeso + pesos[indice] <= capacidad) {
			backtrack(indice + 1, pesos, capacidad, pesoActual, limitePeso + pesos[indice], solucionActual, soluciones);
		}
	}
	
	

	public static void main(String[] args) {
		int[] pesos = {10, 20, 30};
		int capacidad = 50;

		// Encuentra las soluciones al problema de la mochila
		List<List<Integer>> soluciones = MochilaBacktracking.encontrarSoluciones(pesos, capacidad);

		// Imprime las soluciones con los pesos de los elementos seleccionados
		for (List<Integer> solucion : soluciones) {
			System.out.print("Pesos seleccionados: ");
			int pesoTotal = 0;
			for (int indice : solucion) {
				System.out.print(pesos[indice] + " ");
				pesoTotal += pesos[indice];
			}
			System.out.println("\nPeso Total: " + pesoTotal + "\n");
		}
	}
}
