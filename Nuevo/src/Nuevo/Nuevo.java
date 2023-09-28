package Nuevo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class Nuevo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("Titulo_ventana");
		ventana.setVisible(true);
		ventana.setSize(600,400);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setLayout(null);
		ventana.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel etiqueta = new JLabel();
		etiqueta.setBackground(new Color(0, 128, 255));
		etiqueta.setText("Primera prueba");
		
		etiqueta.setBounds(100,-200,400,50);
		
		panel.add(etiqueta);
	}

	/**
	 * Create the application.
	 */
	public Nuevo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
