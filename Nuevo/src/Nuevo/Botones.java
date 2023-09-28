package Nuevo;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

public class Botones {

	JFrame frame;
	JButton BotonAzul;
	JButton BotonRojo;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Botones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BotonAzul = new JButton("Boton");
		BotonAzul.setForeground(new Color(0, 128, 255));
		BotonAzul.setBackground(new Color(0, 0, 255));
		BotonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAzul.setEnabled(false);
				BotonAzul.setBackground(new Color(255, 255, 255));
				BotonRojo.setEnabled(true);
				BotonRojo.setBackground(new Color(255, 0, 0));
			}
		});
		frame.getContentPane().add(BotonAzul, BorderLayout.WEST);

		BotonRojo = new JButton("Boton");
		BotonRojo.setForeground(new Color(255, 128, 128));
		BotonRojo.setBackground(new Color(255, 0, 0));
		BotonRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonRojo.setEnabled(false);
				BotonRojo.setBackground(new Color(255, 255, 255));
				BotonAzul.setEnabled(true);
				BotonAzul.setBackground(new Color(0, 0, 255));

			}
		});
		frame.getContentPane().add(BotonRojo, BorderLayout.EAST);
	}
}

