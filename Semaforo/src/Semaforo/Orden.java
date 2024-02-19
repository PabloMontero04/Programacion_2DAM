package Semaforo;

import java.util.concurrent.Semaphore;

class Orden {
    // Definir los semáforos
    private static Semaphore semaforo1 = new Semaphore(0);
    private static Semaphore semaforo2 = new Semaphore(0);

    // Clase interna para gestionar el primer hilo
    static class Hilo1 extends Thread {
        public void run() {
            try {
                // Espera a que se libere el semáforo 2 antes de continuar
                semaforo2.acquire();
                System.out.println("Hilo 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Clase interna para gestionar el segundo hilo
    static class Hilo2 extends Thread {
        public void run() {
            System.out.println("Hilo 2");
            // Libera el semáforo 2
            semaforo2.release();
            // Libera el semáforo 1
            semaforo1.release();
        }
    }

    public static void main(String[] args) {
        // Crear los hilos
        Hilo1 hilo1 = new Hilo1();
        Hilo2 hilo2 = new Hilo2();

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
    }
}
