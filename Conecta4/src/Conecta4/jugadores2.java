package Conecta4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jugadores2 extends JFrame {
    private static final int FILAS = 6;
    private static final int COLUMNAS = 7;
    private static final int TAMAÑO_CUADRADO = 100;

    private int[][] tablero;
    private int jugadorActual;

    private JButton[] botonesColumna;
    private JButton[][] botonesTablero;

    public jugadores2() {
        tablero = new int[FILAS][COLUMNAS];
        jugadorActual = 1;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(COLUMNAS * TAMAÑO_CUADRADO, (FILAS + 1) * TAMAÑO_CUADRADO);
        setTitle("Conecta 4");

        botonesColumna = new JButton[COLUMNAS];
        botonesTablero = new JButton[FILAS][COLUMNAS];

        for (int i = 0; i < COLUMNAS; i++) {
            botonesColumna[i] = new JButton("Colocar");
            botonesColumna[i].setPreferredSize(new Dimension(TAMAÑO_CUADRADO, TAMAÑO_CUADRADO / 2));
            final int col = i;
            botonesColumna[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    colocarFicha(col);
                    if (verificarGanador()) {
                        JOptionPane.showMessageDialog(null, "¡El Jugador " + jugadorActual + " gana!");
                        reiniciarJuego();
                    } else if (tableroCompleto()) {
                        JOptionPane.showMessageDialog(null, "¡Es un empate!");
                        reiniciarJuego();
                    } else {
                        cambiarJugador();
                    }
                    actualizarInterfaz();
                }
            });
        }

        JPanel panelBotones = new JPanel(new GridLayout(1, COLUMNAS));
        for (JButton boton : botonesColumna) {
            panelBotones.add(boton);
        }

        JPanel panelTablero = new JPanel(new GridLayout(FILAS, COLUMNAS));
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setPreferredSize(new Dimension(TAMAÑO_CUADRADO, TAMAÑO_CUADRADO));
                botonesTablero[i][j].setEnabled(false); // Desactivar los botones del tablero
                panelTablero.add(botonesTablero[i][j]);
            }
        }

        add(panelBotones, BorderLayout.SOUTH);
        add(panelTablero, BorderLayout.CENTER);

        setVisible(true);
    }

    private void colocarFicha(int col) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (tablero[fila][col] == 0) {
                tablero[fila][col] = jugadorActual;
                break;
            }
        }
    }

    private boolean verificarGanador() {
        // Verificar en horizontal
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 &&
                    ficha == tablero[fila][col + 1] &&
                    ficha == tablero[fila][col + 2] &&
                    ficha == tablero[fila][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }

        // Verificar en vertical
        for (int col = 0; col < COLUMNAS; col++) {
            for (int fila = 0; fila < FILAS - 3; fila++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 &&
                    ficha == tablero[fila + 1][col] &&
                    ficha == tablero[fila + 2][col] &&
                    ficha == tablero[fila + 3][col]) {
                    return true; // Hay un ganador
                }
            }
        }

        // Verificar en diagonal (ascendente)
        for (int fila = 3; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 &&
                    ficha == tablero[fila - 1][col + 1] &&
                    ficha == tablero[fila - 2][col + 2] &&
                    ficha == tablero[fila - 3][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }

        // Verificar en diagonal (descendente)
        for (int fila = 0; fila < FILAS - 3; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 &&
                    ficha == tablero[fila + 1][col + 1] &&
                    ficha == tablero[fila + 2][col + 2] &&
                    ficha == tablero[fila + 3][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }

        return false; // No hay un ganador
    }


    private boolean tableroCompleto() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                if (tablero[fila][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reiniciarJuego() {
        tablero = new int[FILAS][COLUMNAS];
        jugadorActual = 1;
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == 1) ? 2 : 1;
    }

    private void actualizarInterfaz() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                if (tablero[fila][col] == 1) {
                    botonesTablero[fila][col].setBackground(Color.RED);
                } else if (tablero[fila][col] == 2) {
                    botonesTablero[fila][col].setBackground(Color.YELLOW);
                } else {
                    botonesTablero[fila][col].setBackground(null);
                }
            }
        }
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new jugadores2());
    }
}
