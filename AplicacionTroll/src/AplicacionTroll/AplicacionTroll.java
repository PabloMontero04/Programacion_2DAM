package AplicacionTroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AplicacionTroll {
    private JFrame frame;
    private JButton button;

    public AplicacionTroll() {
        frame = new JFrame("AplicacionTroll");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        button = new JButton("Pulsame");
        button.setBounds(150, 150, 100, 50);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanasEmergentes();
            }
        });

        moverBotonAleatoriamente();

        frame.setVisible(true);
    }

    private void mostrarVentanasEmergentes() {
        Timer timer = new Timer();
        for (int i = 0; i < 40; i++) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    JFrame VentanaEmergente = new JFrame("");
                    VentanaEmergente.setSize(200, 100);
                    VentanaEmergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Genera ubicaciones aleatorias para las ventanas emergentes en toda la pantalla
                    int maxX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - VentanaEmergente.getWidth());
                    int maxY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - VentanaEmergente.getHeight());
                    int x = (int) (Math.random() * maxX);
                    int y = (int) (Math.random() * maxY);
                    VentanaEmergente.setLocation(x, y);

                    JLabel label = new JLabel("( ͡❛ ͜ʖ ͡❛)");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    VentanaEmergente.add(label);

                    VentanaEmergente.setVisible(true);

                    // Agregar animación para mover las ventanas emergentes
                    Timer movimientoTimer = new Timer();
                    movimientoTimer.schedule(new TimerTask() {
                        int PosicionX = 2; // Cambia la posición horizontal
                        int PosicionY = 2; // Cambia la posición vertical
                        int PosicionXActual = x;
                        int PosicionYActual = y;

                        @Override
                        public void run() {
                            PosicionXActual += PosicionX;
                            PosicionYActual += PosicionY;
                            VentanaEmergente.setLocation(PosicionXActual, PosicionYActual);

                            // Invierte la dirección si la ventana llega al borde
                            if (PosicionXActual <= 0 || PosicionXActual + VentanaEmergente.getWidth() >= maxX) {
                                PosicionX *= -1;
                            }
                            if (PosicionYActual <= 0 || PosicionYActual + VentanaEmergente.getHeight() >= maxY) {
                                PosicionY *= -1;
                            }
                        }
                    }, 0, 50);

                }
            }, i * 400); // Espera 2 segundos entre cada ventana emergente
        }
    }

    private void moverBotonAleatoriamente() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int maxX = frame.getWidth() - button.getWidth();
                int maxY = frame.getHeight() - button.getHeight();
                int x = (int) (Math.random() * maxX);
                int y = (int) (Math.random() * maxY);
                button.setLocation(x, y);
            }
        }, 0, 1000); 

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionTroll();
            }
        });
    }
}