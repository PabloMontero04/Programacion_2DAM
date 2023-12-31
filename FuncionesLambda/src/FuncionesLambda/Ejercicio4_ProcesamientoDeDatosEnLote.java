package FuncionesLambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio4_ProcesamientoDeDatosEnLote {

    public static void main(String[] args) {
        // Cambia la ruta del archivo CSV según tu caso
        String rutaArchivoCSV = "src/archivos/productos.csv";

        try {
            // Leer la lista de productos desde el archivo CSV
            List<Producto> productos = leerArchivoCSV(rutaArchivoCSV);

            // Mostrar la lista de productos
            System.out.println("Lista de productos:");
            productos.forEach(producto -> System.out.println(producto.getNombre() + ": " + producto.getPrecio()));

            // Utiliza una función lambda para calcular el precio medio
            double precioMedio = calcularPrecioMedio(productos);

            // Mostrar el precio medio de todos los productos
            System.out.println("\nEl precio medio de todos los productos es: " + precioMedio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Producto> leerArchivoCSV(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer líneas del archivo, omitir la primera línea si contiene encabezados
            return br.lines()
                    .skip(1)
                    .map(linea -> {
                        // Dividir cada línea por comas y crear un objeto Producto
                        String[] partes = linea.trim().split(",");
                        if (partes.length >= 2) {
                            return new Producto(partes[0], Double.parseDouble(partes[1]));
                        } else {
                            // Imprimir un mensaje de error si hay un problema al analizar la línea
                            System.err.println("Error parsing line: " + linea);
                            return null;
                        }
                    })
                    .filter(producto -> producto != null)
                    .collect(Collectors.toList());
        }
    }

    private static double calcularPrecioMedio(List<Producto> productos) {
        // Calcular el precio medio utilizando expresiones lambda y API de Streams
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
    }

    // Clase para representar un producto
    static class Producto {
        private String nombre;
        private double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }
}
