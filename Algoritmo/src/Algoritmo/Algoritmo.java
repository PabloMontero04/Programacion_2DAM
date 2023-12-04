package Algoritmo;

public class Algoritmo {

    public static int sumaMatriz(int[][] matriz) {
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                suma += matriz[i][j];
            }
        }
        return suma;
    }

    public static void main(String[] args) {
        int[][] miMatriz = {
            {17, 23, 34},
            {42, 55, 63},
            {47, 18, 19}
        };

        int resultado = sumaMatriz(miMatriz);

        System.out.println("La suma de todos los elementos en la matriz es: " + resultado);
    }
}

