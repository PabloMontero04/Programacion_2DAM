

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class CrearXML {
    public static void main(String[] args) {
        // Crear instancia de la clase que representa la biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Añadir libros a la biblioteca
        añadirLibro(biblioteca, "Rebelión en la paris", "George Orwell", "Ficción", 1945, "Secker Warburg", "978-0451526342");
        añadirLibro(biblioteca, "Fahrenheit 451", "Ray Bradbury", "Ficción", 1953, "Ballantine Books", "978-1451673319");
        añadirLibro(biblioteca, "Un mundo feliz", "Aldous Huxley", "Ficción", 1932, "Chatto Windus", "978-0060850524");
        añadirLibro(biblioteca, "Los juegos del hambre", "Suzanne Collins", "Ficción", 2008, "Scholastic Press", "978-0439023481");
        añadirLibro(biblioteca, "Crónicas marcianas", "Ray Bradbury", "Ficción", 1950, "Bantam Books", "978-0553278224");

        añadirLibro(biblioteca, "Harry Potter y la piedra filosofal", "J.K. Rowling", "Fantasía", 1997, "Bloomsbury", "978-0747532699");
        añadirLibro(biblioteca, "El Hobbit", "J.R.R. Tolkien", "Fantasía", 1937, "George Allen y Unwin", "978-0261102217");
        añadirLibro(biblioteca, "Narnia: El león, la bruja y el armario", "C.S. Lewis", "Fantasía", 1950, "Geoffrey Bles", "978-0064404990");
        añadirLibro(biblioteca, "El nombre del viento", "Patrick Rothfuss", "Fantasía", 2007, "Daw Books", "978-0756404741");
        añadirLibro(biblioteca, "Las crónicas de Narnia: El sobrino del mago", "C.S. Lewis", "Fantasía", 1955, "Geoffrey Bles", "978-0064407649");

        añadirLibro(biblioteca, "Los miserables", "Víctor Hugo", "Drama", 1862, "Penguin Classics", "978-0140444308");
        añadirLibro(biblioteca, "Anna Karenina", "León Tolstói", "Drama", 1877, "Penguin Classics", "978-0143035008");
        añadirLibro(biblioteca, "Madame Bovary", "Gustave Flaubert", "Drama", 1856, "Penguin Classics", "978-0143106494");
        añadirLibro(biblioteca, "El retrato de Dorian Gray", "Oscar Wilde", "Drama", 1890, "Penguin Classics", "978-0141439570");
        añadirLibro(biblioteca, "En busca del tiempo perdido", "Marcel Proust", "Drama", 1913, "Penguin Classics", "978-0142437964");

        añadirLibro(biblioteca, "Romeo y Julieta", "William Shakespeare", "Romance", 1597, "Penguin Classics", "978-0141396476");
        añadirLibro(biblioteca, "Orgullo y prejuicio", "Jane Austen", "Romance", 1813, "Penguin Classics", "978-0141439518");
        añadirLibro(biblioteca, "Cumbres borrascosas", "Emily Brontë", "Romance", 1847, "Penguin Classics", "978-0141439556");
        añadirLibro(biblioteca, "Lo que el viento se llevó", "Margaret Mitchell", "Romance", 1936, "Macmillan Publishers", "978-1416548898");
        añadirLibro(biblioteca, "El paciente inglés", "Michael Ondaatje", "Romance", 1992, "Picador", "978-0679745204");
        
        añadirLibro(biblioteca, "Neuromante", "William Gibson", "Ciencia ficción", 1984, "Ace Books", "978-0441569595");
        añadirLibro(biblioteca, "Un mundo devastado", "Octavia E. Butler", "Ciencia ficción", 1993, "Aspect", "978-0446603775");
        añadirLibro(biblioteca, "El fin de la eternidad", "Isaac Asimov", "Ciencia ficción", 1955, "Doubleday", "978-0553290097");
        
        añadirLibro(biblioteca, "El asesinato de Roger Ackroyd", "Agatha Christie", "Misterio", 1926, "Collins Crime Club", "978-0062073563");
        añadirLibro(biblioteca, "La chica del tren", "Paula Hawkins", "Misterio", 2015, "Riverhead Books", "978-1594633669");
        añadirLibro(biblioteca, "El silencio de los corderos", "Thomas Harris", "Misterio", 1988, "St. Martin's Press", "978-0312924584");
        
        añadirLibro(biblioteca, "El último mohicano", "James Fenimore Cooper", "Ficción histórica", 1826, "H.C. Carey & I. Lea", "978-1976330144");
        añadirLibro(biblioteca, "El médico", "Noah Gordon", "Ficción histórica", 1986, "Simon & Schuster", "978-1453271102");
        añadirLibro(biblioteca, "La sombra del viento", "Carlos Ruiz Zafón", "Ficción histórica", 2001, "Penguin Books", "978-0143034902");

        // Llamar al método para convertir a XML y especificar el nombre del archivo
        convertirAXML(biblioteca, "biblioteca.xml");
    }

    public static void añadirLibro(Biblioteca biblioteca, String titulo, String autor, String genero, int anio, String editorial, String isbn) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setGenero(genero);
        libro.setAnio(anio);
        libro.setEditorial(editorial);
        libro.setIsbn(isbn);

        biblioteca.getLibros().add(libro);
    }

    public static void convertirAXML(Biblioteca biblioteca, String nombreArchivo) {
        try {
            // Crear contexto JAXB
            JAXBContext context = JAXBContext.newInstance(Biblioteca.class);

            // Crear marshaller
            Marshaller marshaller = context.createMarshaller();

            // Configurar el formato de salida
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crear el archivo con el nombre proporcionado
            File archivo = new File(nombreArchivo);

            // Marshalling y creación del archivo
            marshaller.marshal(biblioteca, archivo);

            // Imprimir mensaje de éxito
            System.out.println("Archivo XML creado con éxito: " + archivo.getAbsolutePath());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}