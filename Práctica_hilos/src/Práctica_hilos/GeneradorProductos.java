package Práctica_hilos;

import java.util.Random;

public class GeneradorProductos extends Thread {
    CadenaMontaje cadenaMontaje;
    Random rand;

    public GeneradorProductos(CadenaMontaje cadenaMontaje) {
        this.cadenaMontaje = cadenaMontaje;
        this.rand = new Random();
    }

    public void run() {
        int productosEmpaquetados = 0;
        while (productosEmpaquetados < Main.MAX_PRODUCTOS_EMPAQUETADOS) {
            try {
                // Simula tiempo de fabricación de un producto
                Thread.sleep(rand.nextInt(2000) + 500);

                // Verificar si se ha alcanzado el límite máximo de productos
                if (productosEmpaquetados >= Main.MAX_PRODUCTOS_EMPAQUETADOS) {
                    break; // Salir del bucle si se alcanza el límite
                }

                // Crear un producto aleatorio y colocarlo en la cadena
                Producto producto = new Producto(rand.nextInt(3) + 1);
                cadenaMontaje.colocarProducto(producto);
                System.out.println("Producto tipo " + producto.tipo + " creado y colocado en la cadena.");
                productosEmpaquetados++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se han empaquetado " + productosEmpaquetados + " productos. Deteniendo generación de productos.");
    }
}