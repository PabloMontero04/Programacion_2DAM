package CalculadoraInterfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;

public class CalculadoraInterfaz {

    private JFrame frame;
    private String cadenaNumeros = "0";
    private String operacion = "nula";
    private double primerNumero, resultado;
    private boolean activado = true;
    private boolean punto = true;
    private JLabel etiquetaNumero;

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

    public CalculadoraInterfaz() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Calculadora");
        frame.getContentPane().setBackground(new Color(211, 211, 211));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton Numero1 = new JButton("1");
        Numero1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        Numero1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("1");
            }
        });
        Numero1.setBounds(46, 193, 59, 23);
        frame.getContentPane().add(Numero1);

        JButton Numero0 = new JButton("0");
        Numero0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("0");
            }
        });
        Numero0.setBounds(46, 227, 59, 23);
        frame.getContentPane().add(Numero0);

        JButton Numero2 = new JButton("2");
        Numero2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("2");
            }
        });
        Numero2.setBounds(132, 193, 59, 23);
        frame.getContentPane().add(Numero2);

        JButton Numero3 = new JButton("3");
        Numero3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("3");
            }
        });
        Numero3.setBounds(221, 193, 59, 23);
        frame.getContentPane().add(Numero3);

        JButton Numero4 = new JButton("4");
        Numero4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("4");
            }
        });
        Numero4.setBounds(46, 159, 59, 23);
        frame.getContentPane().add(Numero4);

        JButton Numero5 = new JButton("5");
        Numero5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("5");
            }
        });
        Numero5.setBounds(132, 159, 59, 23);
        frame.getContentPane().add(Numero5);

        JButton Numero6 = new JButton("6");
        Numero6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("6");
            }
        });
        Numero6.setBounds(221, 159, 59, 23);
        frame.getContentPane().add(Numero6);

        JButton Numero7 = new JButton("7");
        Numero7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("7");
            }
        });
        Numero7.setBounds(46, 125, 59, 23);
        frame.getContentPane().add(Numero7);

        JButton Numero8 = new JButton("8");
        Numero8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("8");
            }
        });
        Numero8.setBounds(132, 125, 59, 23);
        frame.getContentPane().add(Numero8);

        JButton Numero9 = new JButton("9");
        Numero9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNumero("9");
            }
        });
        Numero9.setBounds(221, 125, 59, 23);
        frame.getContentPane().add(Numero9);

        JButton Punto = new JButton(".");
        Punto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (punto) {
                    cadenaNumeros += ".";
                    etiquetaNumero.setText(cadenaNumeros);
                    punto = false;
                }
            }
        });
        Punto.setBounds(132, 227, 59, 23);
        frame.getContentPane().add(Punto);

        JButton Resta = new JButton("-");
        Resta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("-");
            }
        });
        Resta.setBounds(305, 193, 59, 23);
        frame.getContentPane().add(Resta);

        JButton Multiplicacion = new JButton("x");
        Multiplicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("*");
            }
        });
        Multiplicacion.setBounds(305, 159, 59, 23);
        frame.getContentPane().add(Multiplicacion);

        JButton Dividir = new JButton("/");
        Dividir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("/");
            }
        });
        Dividir.setBounds(305, 125, 59, 23);
        frame.getContentPane().add(Dividir);

        JButton Borrar = new JButton("CE");
        Borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadenaNumeros = "0";
                etiquetaNumero.setText(cadenaNumeros);
                activado = true;
                punto = true;
                operacion = "nula";
            }
        });
        Borrar.setBounds(305, 91, 59, 23);
        frame.getContentPane().add(Borrar);


        JButton Suma = new JButton("+");
        Suma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("+");
            }
        });
        Suma.setBounds(305, 227, 59, 23);
        frame.getContentPane().add(Suma);

        JButton Resultado = new JButton("=");
        Resultado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });
        Resultado.setBounds(221, 227, 59, 23);
        frame.getContentPane().add(Resultado);

        etiquetaNumero = new JLabel("0");
        etiquetaNumero.setBackground(new Color(255, 255, 255));
        etiquetaNumero.setFont(new Font("Tahoma", Font.BOLD, 15));
        etiquetaNumero.setHorizontalAlignment(SwingConstants.RIGHT);
        etiquetaNumero.setBounds(63, 42, 249, 38);
        frame.getContentPane().add(etiquetaNumero);
        
        JButton Raiz = new JButton("√");
        Raiz.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		calcularRaiz();
        	}
        });
        Raiz.setBounds(221, 91, 59, 23);
        frame.getContentPane().add(Raiz);
    }

    private void agregarNumero(String numero) {
        if (etiquetaNumero.getText().equals("0") || !activado) {
            cadenaNumeros = numero;
            activado = true;
        } else {
            if (cadenaNumeros.equals("0")) {
                cadenaNumeros = numero;
            } else {
                cadenaNumeros += numero;
            }
        }
        etiquetaNumero.setText(cadenaNumeros);
    }


    private void realizarOperacion(String nuevaOperacion) {
        if (!operacion.equals("nula")) {
            calcularResultado();
        }
        primerNumero = Double.parseDouble(cadenaNumeros);
        operacion = nuevaOperacion;
        etiquetaNumero.setText(cadenaNumeros); // Mostrar el número actual en la etiqueta
        cadenaNumeros = "0";
        punto = true;
    }

    private void calcularResultado() {
        double segundoNumero = Double.parseDouble(cadenaNumeros);
        switch (operacion) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "*":
                resultado = primerNumero * segundoNumero;
                break;
            case "/":
                if (segundoNumero != 0) {
                    resultado = primerNumero / segundoNumero;
                } else {
                    etiquetaNumero.setText("Error");
                    return;
                }
                break;
        }
        etiquetaNumero.setText(Double.toString(resultado));
        cadenaNumeros = Double.toString(resultado);
        activado = false;
        punto = true;
        operacion = "nula";
    }
    private void calcularRaiz() {
        double numero = Double.parseDouble(cadenaNumeros);
        if (numero >= 0) {
            double resultadoRaiz = Math.sqrt(numero);
            etiquetaNumero.setText(Double.toString(resultadoRaiz));
            cadenaNumeros = Double.toString(resultadoRaiz);
            activado = false;
            punto = true;
            operacion = "nula";
        } else {
            etiquetaNumero.setText("Error");
        }
    }
    
}
