package Práctica_hilos;


import java.util.concurrent.Semaphore;

class CadenaMontaje {
    int capacidad;
    Semaphore mutex;
    Semaphore espaciosDisponibles;
    Producto[] productos;

    public CadenaMontaje(int capacidad) {
        this.capacidad = capacidad;
        mutex = new Semaphore(1);
        espaciosDisponibles = new Semaphore(capacidad);
        productos = new Producto[capacidad];
    }

    public void colocarProducto(Producto producto) throws InterruptedException {
        espaciosDisponibles.acquire();
        mutex.acquire();
        for (int i = 0; i < capacidad; i++) {
            if (productos[i] == null) {
                if (producto.tipo == 1 || producto.tipo == 2 || producto.tipo == 3) {
                    productos[i] = producto;
                    System.out.println("Producto tipo " + producto.tipo + " colocado en la cadena.");
                    mutex.release(); // Liberar el mutex después de colocar el producto
                    return; // Salir del método después de colocar el producto
                }
            }
        }
    }

    public Producto retirarProducto(int tipoOperario) throws InterruptedException {
        mutex.acquire();
        for (int i = 0; i < capacidad; i++) {
            if (productos[i] != null && productos[i].tipo == tipoOperario) {
                Producto producto = productos[i];
                productos[i] = null;
                System.out.println("Producto tipo " + producto.tipo + " retirado de la cadena.");
                mutex.release();
                espaciosDisponibles.release();
                return producto;
            }
        }
        mutex.release();
        return null;
    }
}