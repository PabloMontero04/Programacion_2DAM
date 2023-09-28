package CalculadoraInterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class CalculadoraInterfaz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraInterfaz window = new CalculadoraInterfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculadoraInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.scrollbar);
		textArea.setBounds(66, 42, 277, 38);
		frame.getContentPane().add(textArea);
		
		JButton Numero1 = new JButton("1");
		Numero1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Numero1.setBounds(46, 193, 59, 23);
		frame.getContentPane().add(Numero1);
		
		JButton Numero4 = new JButton("4");
		Numero4.setBounds(46, 159, 59, 23);
		frame.getContentPane().add(Numero4);
		
		JButton Numero7 = new JButton("7");
		Numero7.setBounds(46, 125, 59, 23);
		frame.getContentPane().add(Numero7);
		
		JButton Numero8 = new JButton("8");
		Numero8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Numero8.setBounds(132, 125, 59, 23);
		frame.getContentPane().add(Numero8);
		
		JButton Numero5 = new JButton("5");
		Numero5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Numero5.setBounds(132, 159, 59, 23);
		frame.getContentPane().add(Numero5);
		
		JButton Numero2 = new JButton("2");
		Numero2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Numero2.setBounds(132, 193, 59, 23);
		frame.getContentPane().add(Numero2);
		
		JButton Numero9 = new JButton("9");
		Numero9.setBounds(221, 125, 59, 23);
		frame.getContentPane().add(Numero9);
		
		JButton Numero6 = new JButton("6");
		Numero6.setBounds(221, 159, 59, 23);
		frame.getContentPane().add(Numero6);
		
		JButton Numero3 = new JButton("3");
		Numero3.setBounds(221, 193, 59, 23);
		frame.getContentPane().add(Numero3);
		
		JButton Resultado = new JButton("=");
		Resultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Resultado.setBounds(221, 227, 59, 23);
		frame.getContentPane().add(Resultado);
		
		JButton Punto = new JButton(".");
		Punto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Punto.setBounds(132, 227, 59, 23);
		frame.getContentPane().add(Punto);
		
		JButton Numero0 = new JButton("0");
		Numero0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Numero0.setBounds(46, 227, 59, 23);
		frame.getContentPane().add(Numero0);
		
		JButton Dividir = new JButton(" /");
		Dividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Dividir.setBounds(305, 125, 59, 23);
		frame.getContentPane().add(Dividir);
		
		JButton Multiplicacion = new JButton("x");
		Multiplicacion.setBounds(305, 159, 59, 23);
		frame.getContentPane().add(Multiplicacion);
		
		JButton Resta = new JButton("-");
		Resta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Resta.setBounds(305, 193, 59, 23);
		frame.getContentPane().add(Resta);
		
		JButton Suma = new JButton("+");
		Suma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Suma.setBounds(305, 227, 59, 23);
		frame.getContentPane().add(Suma);
		
		JButton Borrar = new JButton("CE");
		Borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Borrar.setBounds(305, 91, 59, 23);
		frame.getContentPane().add(Borrar);
		
		JTextArea txtrCalculadora = new JTextArea("CALCULADORA");
		txtrCalculadora.setEnabled(false);
		txtrCalculadora.setFont(new Font("Rockwell", Font.BOLD, 18));
		txtrCalculadora.setEditable(false);
		txtrCalculadora.setForeground(Color.RED);
		txtrCalculadora.setBackground(UIManager.getColor("CheckBox.light"));
		txtrCalculadora.setBounds(132, 9, 157, 22);
		frame.getContentPane().add(txtrCalculadora);
	}
}
