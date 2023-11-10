package GestionInventarioMaps;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class GestionInventarioMaps {

    private JFrame frame;

    private Map<String, Producto> productos;  


    public GestionInventarioMaps() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        productos = new HashMap<>();
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 261);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton Añadir = new JButton("Añadir");
        Añadir.setBounds(165, 27, 89, 23);
        panel.add(Añadir);
        Añadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                if (producto != null) {
                    try {
                        String precioStr = JOptionPane.showInputDialog("Ingrese el precio para " + producto + ":");
                        String unidadesStr = JOptionPane.showInputDialog("Ingrese las unidades del producto " + producto + ":");

                        double precio = Double.parseDouble(precioStr);
                        double unidades = Double.parseDouble(unidadesStr);

                        Producto nuevoProducto = new Producto();
                        nuevoProducto.setPrecio(precio);
                        nuevoProducto.setUnidades(unidades);

                        productos.put(producto, nuevoProducto);

                        JOptionPane.showMessageDialog(frame, "Producto " + producto + " agregado con éxito.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Ingrese un precio y unidades válidos.");
                    }
                }
            }
        });

        
        JButton Listar = new JButton("Listar");
        Listar.setBounds(165, 82, 89, 23);
        panel.add(Listar);
        Listar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder listaProductos = new StringBuilder("Lista de productos:\n");
                for (Map.Entry<String, Producto> entry : productos.entrySet()) {
                    String nombreProducto = entry.getKey();
                    Producto producto = entry.getValue();

                    listaProductos.append(nombreProducto).append(": ")
                                   .append("Precio: ").append(producto.getPrecio()).append(", ")
                                   .append("Unidades: ").append(producto.getUnidades()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, listaProductos.toString());
            }
        });
 
        
        JButton Vender = new JButton("Vender");
        Vender.setBounds(165, 143, 89, 23);
        panel.add(Vender);
        Vender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto a vender:");
                if (producto != null) {
                    if (productos.containsKey(producto)) {
                        try {
                            Producto productoSeleccionado = productos.get(producto);

                            String unidadesVendidasStr = JOptionPane.showInputDialog("Ingrese la cantidad de unidades a vender para " + producto + ":");
                            double unidadesVendidas = Double.parseDouble(unidadesVendidasStr);

                            if (unidadesVendidas > 0 && unidadesVendidas <= productoSeleccionado.getUnidades()) {
                                productoSeleccionado.setUnidades(productoSeleccionado.getUnidades() - unidadesVendidas);
                                JOptionPane.showMessageDialog(frame, "Venta de " + unidadesVendidas + " unidades de " + producto + " realizada con éxito.");
                            } else {
                                JOptionPane.showMessageDialog(frame, "La cantidad de unidades a vender no es válida.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Ingrese una cantidad válida de unidades.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "El producto no existe en la lista.");
                    }
                }
            }
        });
        
        
        
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        Eliminar.setBounds(165, 195, 89, 23);
        panel.add(Eliminar);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto a eliminar:");
                if (producto != null) {
                    if (productos.containsKey(producto)) {
                    	productos.remove(producto);
                        JOptionPane.showMessageDialog(frame, "Producto " + producto + " eliminado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "El producto no existe en la lista.");
                    }
                }
            }
        });
        
        
    }

    public static void main(String[] args) {
        GestionInventarioMaps window = new GestionInventarioMaps();
        window.frame.setVisible(true);
    }
}





