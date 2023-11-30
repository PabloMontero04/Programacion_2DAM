package Conecta4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JugadoresVsMaquina extends JFrame {
    private static final int FILAS = 6;
    private static final int COLUMNAS = 7;
    private static final int TAMAÑO_CUADRADO = 100;

    private int[][] tablero;
    private int jugadorActual; // 1: Jugador, 2: Máquina

    private JButton[] botonesColumna;
    private JButton[][] botonesTablero;

    public JugadoresVsMaquina() {
        tablero = new int[FILAS][COLUMNAS];
        jugadorActual = 1;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(COLUMNAS * TAMAÑO_CUADRADO, (FILAS + 1) * TAMAÑO_CUADRADO);
        setTitle("Conecta 4 - Jugador vs Máquina");

        botonesColumna = new JButton[COLUMNAS];
        botonesTablero = new JButton[FILAS][COLUMNAS];

        for (int i = 0; i < COLUMNAS; i++) {
            botonesColumna[i] = new JButton("Colocar");
            botonesColumna[i].setPreferredSize(new Dimension(TAMAÑO_CUADRADO, TAMAÑO_CUADRADO / 2));
            final int col = i;
            botonesColumna[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jugadorActual == 1) {
                        colocarFicha(col);
                        if (verificarGanador()) {
                            JOptionPane.showMessageDialog(null, "¡El Jugador " + jugadorActual + " gana!");
                            reiniciarJuego();
                        } else if (tableroCompleto()) {
                            JOptionPane.showMessageDialog(null, "¡Es un empate!");
                            reiniciarJuego();
                        } else {
                            cambiarJugador();
                            actualizarInterfaz();
                            if (jugadorActual == 2) {
                                // Turno de la máquina
                                realizarMovimientoMaquina();
                                cambiarJugador();
                                actualizarInterfaz();
                            }
                        }
                    }
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

    private void realizarMovimientoMaquina() {
        int mejorColumna = obtenerMejorMovimiento();
        if (mejorColumna != -1) {
            colocarFicha(mejorColumna);
        }
    }

    private int obtenerMejorMovimiento() {
        // Buscar una columna que permita ganar en el próximo movimiento
        for (int col = 0; col < COLUMNAS; col++) {
            if (esMovimientoValido(col)) {
                tablero[FILAS - 1][col] = jugadorActual; // Simular el movimiento
                if (verificarGanador()) {
                    tablero[FILAS - 1][col] = 0; // Deshacer el movimiento
                    return col;
                }
                tablero[FILAS - 1][col] = 0; // Deshacer el movimiento
            }
        }

        // Buscar una columna que evite que el jugador gane en su próximo movimiento
        for (int col = 0; col < COLUMNAS; col++) {
            if (esMovimientoValido(col)) {
                tablero[FILAS - 1][col] = 3 - jugadorActual; // Simular el movimiento del jugador
                if (verificarGanador()) {
                    tablero[FILAS - 1][col] = 0; // Deshacer el movimiento
                    return col;
                }
                tablero[FILAS - 1][col] = 0; // Deshacer el movimiento
            }
        }

        // Si no hay un movimiento crítico, elegir una columna al azar
        Random random = new Random();
        int columnaAleatoria;
        int intentos = 0;
        do {
            columnaAleatoria = random.nextInt(COLUMNAS);
            intentos++;
        } while (!esMovimientoValido(columnaAleatoria) && intentos < COLUMNAS * 2); // Intenta evitar un bucle infinito

        if (intentos == COLUMNAS * 2) {
            // No se encontró una columna válida, el tablero está lleno
            return -1;
        }

        return columnaAleatoria;
    }

    private boolean esMovimientoValido(int col) {
        for (int fila = 0; fila < FILAS; fila++) {
            if (tablero[fila][col] == 0) {
                return true;
            }
        }
        return false;
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
        // Verificar en horizontal, vertical y diagonal
        return verificarGanadorHorizontal() || verificarGanadorVertical() || verificarGanadorDiagonalAscendente() || verificarGanadorDiagonalDescendente();
    }

    private boolean verificarGanadorHorizontal() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 && ficha == tablero[fila][col + 1] && ficha == tablero[fila][col + 2] && ficha == tablero[fila][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }
        return false;
    }

    private boolean verificarGanadorVertical() {
        for (int col = 0; col < COLUMNAS; col++) {
            for (int fila = 0; fila < FILAS - 3; fila++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 && ficha == tablero[fila + 1][col] && ficha == tablero[fila + 2][col] && ficha == tablero[fila + 3][col]) {
                    return true; // Hay un ganador
                }
            }
        }
        return false;
    }

    private boolean verificarGanadorDiagonalAscendente() {
        for (int fila = 3; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 && ficha == tablero[fila - 1][col + 1] && ficha == tablero[fila - 2][col + 2] && ficha == tablero[fila - 3][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }
        return false;
    }

    private boolean verificarGanadorDiagonalDescendente() {
        for (int fila = 0; fila < FILAS - 3; fila++) {
            for (int col = 0; col < COLUMNAS - 3; col++) {
                int ficha = tablero[fila][col];
                if (ficha != 0 && ficha == tablero[fila + 1][col + 1] && ficha == tablero[fila + 2][col + 2] && ficha == tablero[fila + 3][col + 3]) {
                    return true; // Hay un ganador
                }
            }
        }
        return false;
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
        actualizarInterfaz();
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

        // Habilitar los botones del tablero para el jugador humano
        for (int i = 0; i < COLUMNAS; i++) {
            botonesColumna[i].setEnabled(jugadorActual == 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JugadoresVsMaquina());
    }
}
