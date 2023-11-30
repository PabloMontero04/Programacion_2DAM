package FuncionesLambda;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class Ejercicio3_EventosEnUnaAplicaciónGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ejercicio3_EventosEnUnaAplicaciónGUI frame = new Ejercicio3_EventosEnUnaAplicaciónGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Ejercicio3_EventosEnUnaAplicaciónGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton mostrarFecha = new JButton("Mostrar Fecha");
        mostrarFecha.setBackground(SystemColor.activeCaption);
        mostrarFecha.setForeground(SystemColor.desktop);

        //  función lambda para el boton
        mostrarFecha.addActionListener(e -> mostrarFechaActual());

        mostrarFecha.setBounds(145, 105, 129, 42);
        contentPane.add(mostrarFecha);
    }

    // Método para mostrar la fecha actual en un mensaje
    private void mostrarFechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm 'del' dd 'de' MMMM 'del' yyyy");
        String mensaje = "Las " + formatoFecha.format(fechaActual);
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
