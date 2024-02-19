package Semaforo;

import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		
		
long inicio = System.currentTimeMillis();
        
        Imprimir1000Ceros ceros = new Imprimir1000Ceros();
        Imprimir1000Unos unos = new Imprimir1000Unos();
        
        ceros.start();
        unos.start();

        try {
            ceros.join();
            unos.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" ");
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total de ejecución: " + (fin - inicio) + " milisegundos");
    	 	
	} 
	
}

class Imprimir1000Ceros extends Thread {
		public void run() {
			for(int i=0;i<1000;i++) {
				System.out.print("0");
			}
		}
	}
	
	class Imprimir1000Unos extends Thread {
		public void run() {
			for(int i=0;i<1000;i++) {
				System.out.print("1");
			}
		}
	}