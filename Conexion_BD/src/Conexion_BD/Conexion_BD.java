package Conexion_BD;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Conexion_BD {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String bd = "";
	private static final String servidor = "localhost:3306/"+bd;
	private static final String url = "jdbc:mysql://"+servidor;
	private static final String USUARIO = "" ;
	private static final String PASSWORD = "";

	public static Connection conexionBBDD() {
		Connection conec = null; 
		try {
			Class.forName(DRIVER);
			conec = DriverManager.getConnection(url, USUARIO, PASSWORD);
		} catch (Exception errores) {
			System.err.println("Se ha producido un error al conectar con la base de datos.\n" + errores);
		}
		return conec;
	}

	public static void cerrarConexion(Connection conection) {
		try {
			conection.close();
			System.out.println("Conexion Cerrada");
		} catch (SQLException e) {
			System.out.println("Error");
		}
	}



	public static void AltaAsistente(String DNI, String Nombre, String Centro, int Edad) {
		Connection conec = conexionBBDD();
		try {
			Statement consulta = conec.createStatement();
			String query = "INSERT INTO Asistente (DNI, Nombre, Centro, Edad,) VALUES ('" + DNI + "', '" + Nombre + "', '" +
					Centro + "', '" + Edad + "')";

			consulta.executeUpdate(query);
			System.out.println("Datos insertados correctamente.");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Error al insertar datos: " + e.getMessage());
		}
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}


	public static void CambiarDni(String DniActual, String NuevoDni) {
	    Connection conec = conexionBBDD();
	    try {
	        Statement consultaVerificar = conec.createStatement();
	        String verificarQuery = "SELECT * FROM Asistente WHERE DNI = '" + DniActual + "'";
	        ResultSet resultado = consultaVerificar.executeQuery(verificarQuery);

	        if (!resultado.next()) {
	            System.out.println("El DNI actual no existe en la base de datos.");
	            return;
	        }

	        Statement consultaActualizar = conec.createStatement();
	        String actualizarQuery = "UPDATE Asistente SET DNI = '" + NuevoDni + "' WHERE DNI = '" + DniActual + "'";
	        int filasAfectadas = consultaActualizar.executeUpdate(actualizarQuery);

	        if (filasAfectadas > 0) {
	            System.out.println("DNI actualizado correctamente.");
	        } else {
	            System.out.println("No se pudo actualizar el DNI.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cambiar el DNI: " + e.getMessage());
	    } finally {
	        cerrarConexion(conec);
	    }
	}


	public static  void MostrarDatos() {
		Connection conec = conexionBBDD(); 
		try {
			Statement consulta = conec.createStatement();
			ResultSet Select = consulta.executeQuery("SELECT * FROM Asistente");
			while (Select.next()) {
				String DNI = Select.getString("DNI");
				String Nombre = Select.getString("Nombre");
				String Centro = Select.getString("Centro");
				int Edad = Select.getInt("Edad");

				System.out.println("DNI " + DNI);
				System.out.println("Nombre: " + Nombre);
				System.out.println("Centro" + Centro);
				System.out.println("Edad: " + Edad);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	} 


	public static void eliminarAsistente() {
		Connection conec = conexionBBDD();
		try {
			Statement consulta = conec.createStatement();

			System.out.println("Ingrese el DNI del asistente a eliminar:");
			Scanner scanner = new Scanner(System.in);
			String Dni = scanner.toString();

			String query = "DELETE FROM Asistente WHERE DNI = " + Dni;

			int filasAfectadas = consulta.executeUpdate(query);
			if (filasAfectadas > 0) {
				System.out.println("Dni eliminado correctamente");
			} else {
				System.out.println("No se encontro el dni");
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar dni: " + e.getMessage());
		}
	}
}
