package AdivinarNumero;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AdivinarNumero {

    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;
    private int numeroAdivinar;
    private int intentosRestantes;
    private boolean juegoTerminado;
    private JButton reiniciar; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdivinarNumero window = new AdivinarNumero();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdivinarNumero() {
        initialize();
        iniciarJuego();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(135, 206, 235));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBackground(new Color(224, 255, 255));
        textField.setBounds(164, 54, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton jugar = new JButton("Jugar");
        jugar.setBackground(new Color(173, 216, 230));
        jugar.setBounds(164, 167, 89, 23);
        frame.getContentPane().add(jugar);
        jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!juegoTerminado) {
                    int intento = Integer.parseInt(textField.getText());
                    verificarIntento(intento);
                }
            }
        });

        reiniciar = new JButton("Reiniciar");
        reiniciar.setBackground(new Color(173, 216, 230));
        reiniciar.setBounds(164, 216, 89, 23);
        frame.getContentPane().add(reiniciar);
        reiniciar.setVisible(false); 
        reiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        textArea = new JTextArea();
        textArea.setBackground(new Color(176, 224, 230));
        textArea.setBounds(10, 85, 414, 71);
        frame.getContentPane().add(textArea);
    }

    private void iniciarJuego() {
        numeroAdivinar = new Random().nextInt(101); // Genera un número aleatorio del 0 al 100
        intentosRestantes = 10; // Número de intentos permitidos
        juegoTerminado = false;
        reiniciar.setVisible(false); 
        textArea.setText("Adivina un numero del 0 al 100. Tienes " + intentosRestantes + " intentos.");
        frame.getContentPane().setBackground(new Color(135, 206, 235)); // Restaurar el color de fondo
    }

    private void verificarIntento(int intento) {
        if (intento < 0 || intento > 100) {
            textArea.setText("El numero debe estar entre 0 y 100.");
            return;
        }

        intentosRestantes--;

        if (intento == numeroAdivinar) {
            textArea.setText("¡Felicitaciones! Has adivinado el numero " + numeroAdivinar + " en " + (10 - intentosRestantes) + " intentos.");
            juegoTerminado = true;
            reiniciar.setVisible(true); 
            frame.getContentPane().setBackground(new Color(0, 128, 0));
        } else if (intentosRestantes == 0) {
            textArea.setText("¡Agotaste tus intentos! El numero era " + numeroAdivinar + ". Presiona Reiniciar para jugar de nuevo.");
            juegoTerminado = true;
            reiniciar.setVisible(true); 
            frame.getContentPane().setBackground(new Color(255, 0, 0));
        } else if (intento < numeroAdivinar) {
            textArea.setText("El número " + intento + " es mayor. Intentos restantes: " + intentosRestantes);
        } else {
            textArea.setText("El número " + intento + " es menor. Intentos restantes: " + intentosRestantes);
        }

        textField.setText("");
    } 
}
