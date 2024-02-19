

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Metodos {

    private List<Libro> libros;


    public Metodos() {
        this.libros = new ArrayList<>();
        cargarLibrosDesdeXML(); // Cargar los libros desde el archivo XML
    }

    public void mostrarTodosLosLibros() {
        StringBuilder mensaje = new StringBuilder("<html><body><h2>Lista de Libros:</h2><br>");

        for (Libro libro : libros) {
            mensaje.append("<b>Título:</b> ").append(libro.getTitulo()).append("<br>");
            mensaje.append("<b>Autor:</b> ").append(libro.getAutor()).append("<br>");
            mensaje.append("<b>Género:</b> ").append(libro.getGenero()).append("<br>");
            mensaje.append("<b>Año:</b> ").append(libro.getAnio()).append("<br>");
            mensaje.append("<b>Editorial:</b> ").append(libro.getEditorial()).append("<br>");
            mensaje.append("<b>ISBN:</b> ").append(libro.getIsbn()).append("<br><br>");
        }

        mensaje.append("</body></html>");

        mostrarMensaje("Todos los libros", mensaje.toString());
    }

    public void buscarLibrosPorGenero() {
        // Opciones de género
        String[] opciones = {"Ficción", "Fantasía", "Drama", "Romance", "Ciencia ficción", "Misterio", "Ficción histórica"};

        // Mostrar cuadro de diálogo de entrada con lista desplegable
        String generoElegido = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un género:",
                "Buscar Libros por Género",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0] // Valor predeterminado
        );

        // Verificar si el usuario eligió un género
        if (generoElegido != null) {
            // Llamar al método buscarLibrosPorGenero con el género seleccionado
            buscarLibrosPorGenero(generoElegido);
        }
    }

    public void buscarLibrosPorGenero(String genero) {
        StringBuilder mensaje = new StringBuilder("<html><body><h2>Libros del género '" + genero + "':</h2><br>");

        for (Libro libro : libros) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                mensaje.append("<b>Título:</b> ").append(libro.getTitulo()).append("<br>");
                mensaje.append("<b>Autor:</b> ").append(libro.getAutor()).append("<br>");
                mensaje.append("<b>Género:</b> ").append(libro.getGenero()).append("<br>");
                mensaje.append("<b>Año:</b> ").append(libro.getAnio()).append("<br>");
                mensaje.append("<b>Editorial:</b> ").append(libro.getEditorial()).append("<br>");
                mensaje.append("<b>ISBN:</b> ").append(libro.getIsbn()).append("<br><br>");
            }
        }

        mensaje.append("</body></html>");

        mostrarMensaje("Libros por Género", mensaje.toString());
    }



    public void buscarLibrosPorNombre() {
        // Mostrar cuadro de diálogo de entrada para que el usuario ingrese el nombre del libro
        String nombreLibro = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro:", "Buscar Libros por Nombre", JOptionPane.QUESTION_MESSAGE);

        // Verificar si el usuario ingresó un nombre de libro
        if (nombreLibro != null && !nombreLibro.isEmpty()) {
            // Llamar al método buscarLibrosPorNombre con el nombre de libro ingresado
            buscarLibrosPorNombre(nombreLibro);
        }
    }

    public void buscarLibrosPorNombre(String nombreLibro) {
        boolean libroEncontrado = false;  // Variable para rastrear si se encontró al menos un libro

        StringBuilder mensaje = new StringBuilder("<html><body><h2>Libros con el nombre '" + nombreLibro + "':</h2><br>");

        for (Libro libro : libros) {
            String tituloEnMinusculas = libro.getTitulo().toLowerCase();

            System.out.println("Comparando: " + tituloEnMinusculas + " con " + nombreLibro.toLowerCase());

            if (tituloEnMinusculas.equals(nombreLibro.toLowerCase())) {
                libroEncontrado = true;  // Se encontró al menos un libro

                mensaje.append("<b>Título:</b> ").append(libro.getTitulo()).append("<br>");
                mensaje.append("<b>Autor:</b> ").append(libro.getAutor()).append("<br>");
                mensaje.append("<b>Género:</b> ").append(libro.getGenero()).append("<br>");
                mensaje.append("<b>Año:</b> ").append(libro.getAnio()).append("<br>");
                mensaje.append("<b>Editorial:</b> ").append(libro.getEditorial()).append("<br>");
                mensaje.append("<b>ISBN:</b> ").append(libro.getIsbn()).append("<br><br>");
            }
        }

        mensaje.append("</body></html>");

        // Verificar si se encontró al menos un libro
        if (!libroEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron libros con el nombre '" + nombreLibro + "'", "Libros por Nombre", JOptionPane.INFORMATION_MESSAGE);
            return;  
        }

        mostrarMensaje("Libros por Nombre", mensaje.toString());
    }


    private void mostrarMensaje(String titulo, String mensaje) {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(mensaje);
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Establecer un tamaño preferido más grande para el JTextPane
        textPane.setPreferredSize(new Dimension(800, 600)); // Puedes ajustar según tus necesidades

        // Ajustar la política de redimensionamiento del panel
        scrollPane.setPreferredSize(new Dimension(800, 600));

        // Ajustar la vista del JScrollPane para que comience desde el principio
        textPane.setCaretPosition(0);

        // Cambiar el texto del botón de Aceptar a Volver
        Object[] options = {"Volver"};
        JOptionPane.showOptionDialog(null, scrollPane, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }




    private void cargarLibrosDesdeXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Cambia la ruta del archivo XML según tu configuración
            Document document = builder.parse(new File("biblioteca.xml"));

            NodeList nodeList = document.getElementsByTagName("libro");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String titulo = element.getElementsByTagName("titulo").item(0).getTextContent();
                    String autor = element.getElementsByTagName("autor").item(0).getTextContent();
                    String genero = element.getElementsByTagName("genero").item(0).getTextContent();
                    int anio = Integer.parseInt(element.getElementsByTagName("anio").item(0).getTextContent());
                    String editorial = element.getElementsByTagName("editorial").item(0).getTextContent();
                    String isbn = element.getElementsByTagName("isbn").item(0).getTextContent();

                    Libro libro = new Libro(titulo, autor, genero, anio, editorial, isbn);
                    libros.add(libro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        
    
    public void modificarLibro() {
        // Mostrar cuadro de diálogo de entrada para que el usuario ingrese el título o ISBN del libro a modificar
        String tituloOIsbn = JOptionPane.showInputDialog(null, "Ingrese el título o ISBN del libro a modificar:", "Modificar Libro", JOptionPane.QUESTION_MESSAGE);

        // Verificar si el usuario ingresó un título o ISBN
        if (tituloOIsbn != null && !tituloOIsbn.isEmpty()) {
            // Buscar el libro en la lista
            Libro libroAModificar = null;
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(tituloOIsbn) || libro.getIsbn().equals(tituloOIsbn)) {
                    libroAModificar = libro;
                    break;
                }
            }

            // Verificar si se encontró el libro
            if (libroAModificar != null) {
                boolean continuarModificando = true;

                while (continuarModificando) {
                    // Opciones para elegir qué campo modificar
                    String[] opciones = {"Título", "Autor", "Género", "Año", "Editorial", "ISBN", "Finalizar"};

                    // Mostrar cuadro de diálogo con lista desplegable para elegir qué campo modificar
                    String campoAModificar = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione el campo que desea modificar o seleccione 'Finalizar':",
                            "Modificar Libro",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0] // Valor predeterminado
                    );

                    // Verificar si el usuario seleccionó un campo o "Finalizar"
                    if (campoAModificar != null && !campoAModificar.equals("Finalizar")) {
                        // Mostrar cuadro de diálogo de entrada para que el usuario ingrese el nuevo valor del campo
                        String nuevoValor = JOptionPane.showInputDialog(null, "Ingrese el nuevo valor para " + campoAModificar + ":", "Modificar Libro", JOptionPane.QUESTION_MESSAGE);

                        // Verificar si el usuario ingresó un nuevo valor
                        if (nuevoValor != null && !nuevoValor.isEmpty()) {
                            // Actualizar el campo correspondiente del libro
                            switch (campoAModificar) {
                                case "Título":
                                    libroAModificar.setTitulo(nuevoValor);
                                    break;
                                case "Autor":
                                    libroAModificar.setAutor(nuevoValor);
                                    break;
                                case "Género":
                                    libroAModificar.setGenero(nuevoValor);
                                    break;
                                case "Año":
                                    int nuevoAnio = Integer.parseInt(nuevoValor);
                                    libroAModificar.setAnio(nuevoAnio);
                                    break;
                                case "Editorial":
                                    libroAModificar.setEditorial(nuevoValor);
                                    break;
                                case "ISBN":
                                    libroAModificar.setIsbn(nuevoValor);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción no válida.", "Modificar Libro", JOptionPane.ERROR_MESSAGE);
                                    return;
                            }

                            // Mostrar mensaje de éxito
                            JOptionPane.showMessageDialog(null, "Libro modificado exitosamente.", "Modificar Libro", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // Mostrar mensaje de error si no se ingresó un nuevo valor
                            JOptionPane.showMessageDialog(null, "No se ingresó un nuevo valor.", "Modificar Libro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        continuarModificando = false; // Salir del bucle si el usuario elige "Finalizar"
                    }
                }
            } else {
                // Mostrar mensaje de error si no se encontró el libro
                JOptionPane.showMessageDialog(null, "No se encontró el libro con el título o ISBN especificado.", "Modificar Libro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        guardarLibrosEnXML();

    }

    
    public void añadirLibro() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el título del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);
        String genero = JOptionPane.showInputDialog(null, "Ingrese el género del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);
        String anioStr = JOptionPane.showInputDialog(null, "Ingrese el año de publicación del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);
        int anio = 0;
        try {
            anio = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un año válido.", "Añadir Libro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String editorial = JOptionPane.showInputDialog(null, "Ingrese la editorial del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);
        String isbn = JOptionPane.showInputDialog(null, "Ingrese el ISBN del libro:", "Añadir Libro", JOptionPane.QUESTION_MESSAGE);

        // Verificar si todos los campos están llenos
        if (titulo != null && !titulo.isEmpty() && autor != null && !autor.isEmpty() && genero != null && !genero.isEmpty() &&
                editorial != null && !editorial.isEmpty() && isbn != null && !isbn.isEmpty()) {
            // Crear un nuevo libro y agregarlo a la lista
            Libro nuevoLibro = new Libro(titulo, autor, genero, anio, editorial, isbn);
            libros.add(nuevoLibro);
            JOptionPane.showMessageDialog(null, "Libro añadido correctamente.", "Añadir Libro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar un mensaje de error si algún campo está vacío
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Añadir Libro", JOptionPane.ERROR_MESSAGE);
        }
        
        guardarLibrosEnXML();

    }
    
    public void eliminarLibro() {
        String[] titulos = obtenerTitulosLibros(); // Obtener la lista de títulos de los libros
        if (titulos.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay libros para eliminar.", "Eliminar Libro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Mostrar una lista de títulos para que el usuario seleccione el libro a eliminar
        String tituloSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione el título del libro a eliminar:", "Eliminar Libro", JOptionPane.QUESTION_MESSAGE, null, titulos, titulos[0]);

        if (tituloSeleccionado != null) {
            // Buscar el libro en la lista y eliminarlo
            libros.removeIf(libro -> libro.getTitulo().equalsIgnoreCase(tituloSeleccionado));
            JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.", "Eliminar Libro", JOptionPane.INFORMATION_MESSAGE);
            guardarLibrosEnXML(); // Guardar los cambios en el archivo XML
        }
    }

    private String[] obtenerTitulosLibros() {
        String[] titulos = new String[libros.size()];
        for (int i = 0; i < libros.size(); i++) {
            titulos[i] = libros.get(i).getTitulo();
        }
        return titulos;
    }
    
    private void guardarLibrosEnXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Crear el documento XML
            Document document = builder.newDocument();
            Element rootElement = document.createElement("biblioteca");
            document.appendChild(rootElement);

            // Agregar cada libro al documento XML
            for (Libro libro : libros) {
                Element libroElement = document.createElement("libro");

                Element tituloElement = document.createElement("titulo");
                tituloElement.appendChild(document.createTextNode(libro.getTitulo()));
                libroElement.appendChild(tituloElement);

                Element autorElement = document.createElement("autor");
                autorElement.appendChild(document.createTextNode(libro.getAutor()));
                libroElement.appendChild(autorElement);

                Element generoElement = document.createElement("genero");
                generoElement.appendChild(document.createTextNode(libro.getGenero()));
                libroElement.appendChild(generoElement);

                Element anioElement = document.createElement("anio");
                anioElement.appendChild(document.createTextNode(String.valueOf(libro.getAnio())));
                libroElement.appendChild(anioElement);

                Element editorialElement = document.createElement("editorial");
                editorialElement.appendChild(document.createTextNode(libro.getEditorial()));
                libroElement.appendChild(editorialElement);

                Element isbnElement = document.createElement("isbn");
                isbnElement.appendChild(document.createTextNode(libro.getIsbn()));
                libroElement.appendChild(isbnElement);

                rootElement.appendChild(libroElement);
            }

            // Escribir el contenido del documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            // Cambia la ruta del archivo XML según tu configuración
            File archivoXML = new File("biblioteca.xml");
            OutputStream outputStream = new FileOutputStream(archivoXML);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);

            outputStream.close(); // Cerrar el flujo de salida

            System.out.println("Los cambios se han guardado en el archivo XML.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void mostrarContenidoXML(String rutaArchivoXML) {
        try {
            // Configurar el parser para leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(new File(rutaArchivoXML));

            // Configurar el transformador para imprimir el XML en un JTextPane
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Crear un JTextPane para mostrar el contenido del archivo XML
            JTextPane textPane = new JTextPane();
            textPane.setContentType("text/xml");

            // Transformar el documento XML en un objeto DOMSource
            DOMSource source = new DOMSource(document);

            // Crear un StringWriter para almacenar el resultado de la transformación
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            // Realizar la transformación y almacenar el resultado en el StringWriter
            transformer.transform(source, result);

            // Obtener el contenido del StringWriter como una cadena
            String xmlString = writer.toString();

            // Establecer el contenido del JTextPane como el contenido XML
            textPane.setText(xmlString);

            // Crear un JScrollPane para contener el JTextPane
            JScrollPane scrollPane = new JScrollPane(textPane);
            scrollPane.setPreferredSize(new Dimension(800, 600));

            // Mostrar el contenido del archivo XML en una ventana
            JOptionPane.showMessageDialog(null, scrollPane, "Contenido del Archivo XML", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void consultarXPath() {
        try {
            // Solicitar la expresión XPath al usuario
            String xpathExpression = JOptionPane.showInputDialog(null, "Ingrese la expresión XPath:", "Consulta XPath", JOptionPane.QUESTION_MESSAGE);
            if (xpathExpression == null || xpathExpression.isEmpty()) {
                // No se ingresó ninguna consulta XPath, salir del método
                return;
            }

            // Ruta del archivo XML (siempre es "biblioteca.xml")
            String rutaArchivoXML = "biblioteca.xml";

            // Parsear el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(rutaArchivoXML));

            // Configurar el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Compilar la expresión XPath
            javax.xml.xpath.XPathExpression expr = xpath.compile(xpathExpression);

            // Evaluar la expresión XPath
            Object result = expr.evaluate(document, XPathConstants.NODESET);

            // Verificar el tipo de resultado
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                // Si no hay resultados, no mostrar ninguna ventana
                if (nodeList.getLength() == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados para la consulta XPath.", "Consulta XPath", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                // Crear una cadena con los resultados
                StringBuilder resultString = new StringBuilder();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    resultString.append(nodeList.item(i).getNodeName()).append(": ").append(nodeList.item(i).getTextContent()).append("\n");
                }
                // Mostrar los resultados en una ventana con desplazamiento
                JTextArea textArea = new JTextArea(resultString.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(null, scrollPane, "Resultados de la consulta", JOptionPane.INFORMATION_MESSAGE);
            } else if (result instanceof String) {
                String stringValue = (String) result;
                JOptionPane.showMessageDialog(null, stringValue, "Resultado de la consulta", JOptionPane.INFORMATION_MESSAGE);
            } else if (result instanceof Number) {
                Number numberValue = (Number) result;
                JOptionPane.showMessageDialog(null, numberValue, "Resultado de la consulta", JOptionPane.INFORMATION_MESSAGE);
            } else if (result instanceof Boolean) {
                Boolean booleanValue = (Boolean) result;
                JOptionPane.showMessageDialog(null, booleanValue, "Resultado de la consulta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de resultado desconocido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta XPath: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void Mostrarayuda() {
        // Crear un JTextArea con el mensaje y aplicar estilos
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setForeground(new Color(50, 50, 50));
        textArea.setBackground(new Color(230, 230, 230));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setText(
            "Bienvenido al sistema de gestión de bibliotecas.\n\n" +
            "Este sistema te permite gestionar una biblioteca de libros de manera eficiente.\n\n" +
            "Para comenzar, puedes utilizar los siguientes botones:\n\n" +
            "- Libros: Permite acceder a funciones relacionadas con la gestión de libros.\n\n" +
            "- Admin: Proporciona funciones de administrador para añadir, modificar o eliminar libros.\n\n" +
            "- Ayuda: Aquí encontrarás información útil sobre el funcionamiento del sistema.\n"
        );

        // Colocar el JTextArea en un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Mostrar el mensaje de ayuda en un diálogo con estilos
        UIManager.put("OptionPane.background", new Color(230, 230, 230));
        UIManager.put("Panel.background", new Color(230, 230, 230));
        UIManager.put("OptionPane.messageForeground", new Color(50, 50, 50));
        JOptionPane.showMessageDialog(null, scrollPane, "Ayuda - Gestión de Bibliotecas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public static void convertXMLtoXLS(String xmlFilePath, String xlsFilePath) {
        try {
            // Cargar el archivo XML
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Crear el nuevo archivo XLS
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Biblioteca");

            // Obtener la lista de elementos "libro"
            NodeList nodeList = doc.getElementsByTagName("libro");

            // Iterar sobre los elementos "libro" y escribirlos en el archivo XLS
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Row row = sheet.createRow(i);

                    row.createCell(0).setCellValue(element.getElementsByTagName("titulo").item(0).getTextContent());
                    row.createCell(1).setCellValue(element.getElementsByTagName("autor").item(0).getTextContent());
                    row.createCell(2).setCellValue(element.getElementsByTagName("genero").item(0).getTextContent());
                    row.createCell(3).setCellValue(element.getElementsByTagName("anio").item(0).getTextContent());
                    row.createCell(4).setCellValue(element.getElementsByTagName("editorial").item(0).getTextContent());
                    row.createCell(5).setCellValue(element.getElementsByTagName("isbn").item(0).getTextContent());
                }
            }

            // Escribir el contenido en un archivo
            FileOutputStream fileOut = new FileOutputStream(xlsFilePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Archivo XLS creado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateHTMLFromXML(String xmlFilePath) {
        StringBuilder html = new StringBuilder();
        try {
            // Cargar el archivo XML
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Comenzar a construir la tabla HTML
            html.append("<table border='1'>");
            html.append("<tr><th>Título</th><th>Autor</th><th>Género</th><th>Año</th><th>Editorial</th><th>ISBN</th></tr>");

            // Obtener la lista de elementos "libro"
            NodeList nodeList = doc.getElementsByTagName("libro");

            // Iterar sobre los elementos "libro" y agregarlos a la tabla HTML
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    html.append("<tr>");
                    html.append("<td>").append(element.getElementsByTagName("titulo").item(0).getTextContent()).append("</td>");
                    html.append("<td>").append(element.getElementsByTagName("autor").item(0).getTextContent()).append("</td>");
                    html.append("<td>").append(element.getElementsByTagName("genero").item(0).getTextContent()).append("</td>");
                    html.append("<td>").append(element.getElementsByTagName("anio").item(0).getTextContent()).append("</td>");
                    html.append("<td>").append(element.getElementsByTagName("editorial").item(0).getTextContent()).append("</td>");
                    html.append("<td>").append(element.getElementsByTagName("isbn").item(0).getTextContent()).append("</td>");
                    html.append("</tr>");
                }
            }

            html.append("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return html.toString();
    }



}

