

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {

    private JPanel panelBienvenida;
    private JPanel panelBotones;
    private JPanel panelLibros;
    private JPanel panelAdmin;
    private JPanel panelAyuda;
    private Metodos metodos;

    public Interfaz() {
        metodos = new Metodos();
        
        String rutaArchivoXML = "biblioteca.xml";


        setTitle("Biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular la posición para centrar la ventana en la pantalla
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        // Establecer la posición de la ventana
        setLocation(x, y);

        setLayout(new CardLayout());
        
        

        // Cargar la imagen de fondo
        ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\fondo3.jpg");
        final Image image = imageIcon.getImage();

        // Panel de bienvenida
        panelBienvenida = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JButton botonBienvenida = new RoundButton("Bienvenidos");
        configurarBotonRedondo(botonBienvenida);

        botonBienvenida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.next(getContentPane());
            }
        });

        panelBienvenida.setLayout(new GridBagLayout());
        GridBagConstraints gbcBienvenida = new GridBagConstraints();
        gbcBienvenida.gridx = 0;
        gbcBienvenida.gridy = 0;
        panelBienvenida.add(botonBienvenida, gbcBienvenida);


        // Panel de botones
        panelBotones = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.gridx = 0;
        gbcBotones.gridy = 0;
        gbcBotones.insets = new Insets(10, 0, 10, 0);

        JButton botonLibros = new RoundButton("Libros");
        JButton botonAdmin = new RoundButton("Admin");
        JButton botonAyuda = new RoundButton("Otros");

        configurarBotonRedondo(botonLibros);
        configurarBotonRedondo(botonAyuda);
        configurarBotonRedondo(botonAdmin);

        Dimension buttonSize = new Dimension(200, 50);
        botonLibros.setPreferredSize(buttonSize);
        botonAyuda.setPreferredSize(buttonSize);
        botonAdmin.setPreferredSize(buttonSize);

        botonLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "libros");
            }
        });

        botonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "admin");
            }
        });

        botonAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "Otros");
            }
        });

        panelBotones.add(botonLibros, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(botonAdmin, gbcBotones);
        gbcBotones.gridy++;
        panelBotones.add(botonAyuda, gbcBotones);
        gbcBotones.gridy++;

        panelBotones.add(crearBotonVolver("bienvenida"), gbcBotones);

        // Panel de libros
        panelLibros = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelLibros.setLayout(new GridBagLayout());
        GridBagConstraints gbcLibros = new GridBagConstraints();
        gbcLibros.gridx = 0;
        gbcLibros.gridy = 0;
        gbcLibros.insets = new Insets(10, 0, 10, 0);

        JButton botonMostrarLibros = new RoundButton("Mostrar Libros");
        JButton botonBuscarLibro = new RoundButton("Buscar Libro");
        JButton botonPorGenero = new RoundButton("Por Género");

        configurarBotonRedondo(botonMostrarLibros);
        configurarBotonRedondo(botonBuscarLibro);
        configurarBotonRedondo(botonPorGenero);

        botonMostrarLibros.setPreferredSize(buttonSize);
        botonBuscarLibro.setPreferredSize(buttonSize);
        botonPorGenero.setPreferredSize(buttonSize);

        botonMostrarLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.mostrarTodosLosLibros();
            }
        });

        botonBuscarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.buscarLibrosPorNombre();
            }
        });

        botonPorGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.buscarLibrosPorGenero();
            }
        });

        botonAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "ayuda");
            }
        });

        panelLibros.add(botonMostrarLibros, gbcLibros);
        gbcLibros.gridy++;
        panelLibros.add(botonBuscarLibro, gbcLibros);
        gbcLibros.gridy++;
        panelLibros.add(botonPorGenero, gbcLibros);
        gbcLibros.gridy++;
        panelLibros.add(crearBotonVolver("botones"), gbcLibros);

        // Panel de administrador
        panelAdmin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelAdmin.setLayout(new GridBagLayout());
        GridBagConstraints gbcAdmin = new GridBagConstraints();
        gbcAdmin.gridx = 0;
        gbcAdmin.gridy = 0;
        gbcAdmin.insets = new Insets(10, 0, 10, 0);

        JButton botonAnadir = new RoundButton("Añadir");
        JButton botonModificar = new RoundButton("Modificar");
        JButton botonEliminar = new RoundButton("Eliminar");

        configurarBotonRedondo(botonAnadir);
        configurarBotonRedondo(botonModificar);
        configurarBotonRedondo(botonEliminar);

        botonAnadir.setPreferredSize(buttonSize);
        botonModificar.setPreferredSize(buttonSize);
        botonEliminar.setPreferredSize(buttonSize);

        botonAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.añadirLibro();
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.modificarLibro();
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.eliminarLibro();
            }
        });

        panelAdmin.add(botonAnadir, gbcAdmin);
        gbcAdmin.gridy++;
        panelAdmin.add(botonModificar, gbcAdmin);
        gbcAdmin.gridy++;
        panelAdmin.add(botonEliminar, gbcAdmin);
        gbcAdmin.gridy++;
        panelAdmin.add(crearBotonVolver("botones"), gbcAdmin);

        // Panel de ayuda
        panelAyuda = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelAyuda.setLayout(new GridBagLayout());
        GridBagConstraints gbcAyuda = new GridBagConstraints();
        gbcAyuda.gridx = 0;
        gbcAyuda.gridy = 0;
        gbcAyuda.insets = new Insets(10, 0, 10, 0);

        JButton botonXML = new RoundButton("XML");
        JButton botonConsultas = new RoundButton("Consultas");
        JButton botonSalir = new RoundButton("Salir");
        JButton botonAyudaExtra = new RoundButton("Ayuda");

        configurarBotonRedondo(botonXML);
        configurarBotonRedondo(botonConsultas);
        configurarBotonRedondo(botonSalir);
        configurarBotonRedondo(botonAyudaExtra);

        botonXML.setPreferredSize(buttonSize);
        botonConsultas.setPreferredSize(buttonSize);
        botonSalir.setPreferredSize(buttonSize);
        botonAyudaExtra.setPreferredSize(buttonSize);

        botonXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.mostrarContenidoXML(rutaArchivoXML);
            }
        });

        botonConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.consultarXPath();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Colocar aquí la lógica para manejar el botón Salir
                // Por ahora, simplemente volvemos al panel de botones
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "botones");
            }
        });

        botonAyudaExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metodos.Mostrarayuda();
            }
        });

        panelAyuda.add(botonXML, gbcAyuda);
        gbcAyuda.gridy++;
        panelAyuda.add(botonConsultas, gbcAyuda);
        gbcAyuda.gridy++;
        panelAyuda.add(botonAyudaExtra, gbcAyuda);
        gbcAyuda.gridy++;
        panelAyuda.add(botonSalir, gbcAyuda);
        
        

        // Agregar los paneles a la ventana
        add(panelBienvenida, "bienvenida");
        add(panelBotones, "botones");
        add(panelLibros, "libros");
        add(panelAdmin, "admin");
        add(panelAyuda, "ayuda");
    }

    private JButton crearBotonVolver(final String panelAnterior) {
        JButton botonVolver = new RoundButton("Volver");
        configurarBotonRedondo(botonVolver);

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), panelAnterior);
            }
        });

        Dimension buttonSize = new Dimension(200, 50);
        botonVolver.setPreferredSize(buttonSize);

        return botonVolver;
    }

    private void configurarBotonRedondo(JButton boton) {
        boton.setBackground(new Color(169, 50, 38));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interfaz programa = new Interfaz();
                programa.setVisible(true);
            }
        });
    }

    private static class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.DARK_GRAY);
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 20, 20);
        }
    }
}
