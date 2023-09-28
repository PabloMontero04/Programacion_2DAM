package Botones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Botones {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Botones window = new Botones();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Botones() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(90, 11, 234, 39);
        frame.getContentPane().add(textArea);

        JButton Numero1 = new JButton("Numero 1");
        Numero1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("1");
            }
        });
        Numero1.setBackground(Color.YELLOW);
        Numero1.setBounds(123, 61, 164, 39);
        frame.getContentPane().add(Numero1);

        JButton Numero2 = new JButton("Numero 2");
        Numero2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("2");
            }
        });
        Numero2.setBackground(Color.LIGHT_GRAY);
        Numero2.setBounds(123, 111, 164, 39);
        frame.getContentPane().add(Numero2);

        JButton Numero3 = new JButton("Numero 3");
        Numero3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("3");
            }
        });
        Numero3.setBackground(Color.GREEN);
        Numero3.setBounds(123, 166, 164, 39);
        frame.getContentPane().add(Numero3);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (keyChar != '1' && keyChar != '2' && keyChar != '3') {
                   e.consume();
                }
            }
        });

        JButton Reiniciar = new JButton("Reiniciar");
        Reiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        Reiniciar.setBackground(Color.PINK);
        Reiniciar.setBounds(161, 216, 89, 23);

        frame.getContentPane().add(Reiniciar);
        frame.setFocusable(true);
        frame.requestFocus();
    }
}

