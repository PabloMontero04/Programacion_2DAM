package NotaEstudiantesMaps;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NotaEstudiantesMaps {

    private JFrame frame;
    private Map<String, Double> notasEstudiantes;  

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NotaEstudiantesMaps window = new NotaEstudiantesMaps();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NotaEstudiantesMaps() {
        initialize();
        notasEstudiantes = new HashMap<>();  
    }
    
    

    private void initialize() {
        frame = new JFrame("Notas");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 250);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton Añadir = new JButton("Añadir");
        Añadir.setBounds(112, 11, 187, 37);
        panel.add(Añadir);
        Añadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
                if (nombre != null) {
                    String notaStr = JOptionPane.showInputDialog("Ingrese la nota de " + nombre + ":");
                    if (notaStr != null) {
                        try {
                            double nota = Double.parseDouble(notaStr);
                            notasEstudiantes.put(nombre, nota);
                            JOptionPane.showMessageDialog(frame, "Nota de " + nombre + " agregada con éxito.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Ingrese una nota válida.");
                        }
                    }
                }
            }
        });

        JButton Listar = new JButton("Listar");
        Listar.setBounds(112, 66, 187, 37);
        panel.add(Listar);
        Listar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder listaNotas = new StringBuilder("Lista de notas de estudiantes:\n");
                for (Map.Entry<String, Double> entry : notasEstudiantes.entrySet()) {
                    listaNotas.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, listaNotas.toString());
            }
        });

        JButton Modificar = new JButton("Modificar");
        Modificar.setBounds(112, 119, 187, 42);
        panel.add(Modificar);
        Modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a modificar:");
                if (nombre != null) {
                    if (notasEstudiantes.containsKey(nombre)) {
                        String nuevaNotaStr = JOptionPane.showInputDialog("Ingrese la nueva nota de " + nombre + ":");
                        if (nuevaNotaStr != null) {
                            try {
                                double nuevaNota = Double.parseDouble(nuevaNotaStr);
                                notasEstudiantes.put(nombre, nuevaNota);
                                JOptionPane.showMessageDialog(frame, "Nota de " + nombre + " modificada con éxito.");
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Ingrese una nota válida.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "El estudiante no existe en la lista.");
                    }
                }
            }
        });

        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(112, 172, 187, 42);
        panel.add(Eliminar);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a eliminar:");
                if (nombre != null) {
                    if (notasEstudiantes.containsKey(nombre)) {
                        notasEstudiantes.remove(nombre);
                        JOptionPane.showMessageDialog(frame, "Nota de " + nombre + " eliminada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "El estudiante no existe en la lista.");
                    }
                }
            }
        });
    }
}
