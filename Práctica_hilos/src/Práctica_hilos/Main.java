package Práctica_hilos;

public class Main {
    // Número máximo de productos a empaquetar antes de detener el programa
    static final int MAX_PRODUCTOS_EMPAQUETADOS = 5;

    public static void main(String[] args) {
        int capacidadCadena = 5; // Longitud máxima de la cadena
        CadenaMontaje cadenaMontaje = new CadenaMontaje(capacidadCadena);

        // Crear operarios de diferentes tipos
        Operario operarioTipo1 = new Operario(cadenaMontaje, 1);
        Operario operarioTipo2 = new Operario(cadenaMontaje, 2);
        Operario operarioTipo3 = new Operario(cadenaMontaje, 3);

        // Iniciar los hilos de los operarios
        operarioTipo1.start();
        operarioTipo2.start();
        operarioTipo3.start();

        // Iniciar el hilo que genera productos automáticamente
        GeneradorProductos generador = new GeneradorProductos(cadenaMontaje);
        generador.start();

        // Esperar a que todos los operarios terminen
        try {
            operarioTipo1.join();
            operarioTipo2.join();
            operarioTipo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
