package Ventanas;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventanas {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventanas window = new Ventanas();
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
	public Ventanas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton Boton = new JButton("Pulse para abrir una ventana");
		Boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirOtraVentana();
			}
		});
		Boton.setBackground(Color.ORANGE);
		Boton.setBounds(94, 90, 232, 45);
		frame.getContentPane().add(Boton);
	}
	
	public void AbrirOtraVentana() {
		
		JDialog dialog = new JDialog (frame,"Ventana 2",true);
		dialog.setSize(450,300);
		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 125  ));
		
		
		JLabel label = new JLabel("Ventana 2");
		dialog.getContentPane().add(label);
		
		dialog.setVisible(true);
		
		
	}
}
