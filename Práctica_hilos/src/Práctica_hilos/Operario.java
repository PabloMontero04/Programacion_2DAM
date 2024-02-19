package Práctica_hilos;

class Operario extends Thread {
    CadenaMontaje cadenaMontaje;
    int tipoOperario;
    int productosEmpaquetados;
    int[] productosPorTipo; // Contador para cada tipo de producto
    boolean esperando;

    public Operario(CadenaMontaje cadenaMontaje, int tipoOperario) {
        this.cadenaMontaje = cadenaMontaje;
        this.tipoOperario = tipoOperario;
        this.productosEmpaquetados = 0;
        this.productosPorTipo = new int[3]; // Tres tipos de productos
        this.esperando = false;
    }

    public void run() {
        while (productosEmpaquetados < Main.MAX_PRODUCTOS_EMPAQUETADOS) {
            try {
                if (!esperando) {
                    System.out.println("Operario tipo " + tipoOperario + ": Esperando por un producto de mi tipo...");
                    esperando = true;
                }

                // Esperar a que se coloque un producto de su tipo en la cadena
                Producto producto = cadenaMontaje.retirarProducto(tipoOperario);

                // Si se retiró un producto, empaquetarlo, actualizar el contador por tipo y cambiar estado de espera
                if (producto != null) {
                    productosEmpaquetados++;
                    productosPorTipo[producto.tipo - 1]++; // Restamos 1 porque los tipos de producto empiezan en 1
                    System.out.println("Operario tipo " + tipoOperario + ": Producto tipo " + producto.tipo +
                            " empaquetado. Total: " + productosEmpaquetados);
                    esperando = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Operario tipo " + tipoOperario + " ha empaquetado " + Main.MAX_PRODUCTOS_EMPAQUETADOS + " productos. Deteniendo operario.");

    }
}
