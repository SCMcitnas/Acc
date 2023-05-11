package Conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejer35{
	
	private Connection conexion;
	public void abrirConexion(String bd, String servidor , String usuario, String password) {
		try {
			String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);

			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.conexion = DriverManager.getConnection(url, usuario, password);
			if (this.conexion != null) {
				System.out.println ("Conectado a "+bd+" en "+servidor);
			} else {
				System.out.println ("No conectado a "+bd+" en "+servidor);
			}
		} catch (SQLException e) {
				System.out.println("SQLException: " + e.getLocalizedMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Código error: " + e.getErrorCode());
		}
	}
	
	public void cerrarConexion (){
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: "+e.getLocalizedMessage());
		}
	}
	
	int filasBorradas=0;
	public void añadirNaves(String nombre, String pais, String fabricante, String sistLanzamiento, double longitud, short masaSeco, short masaLanzamiento, short cargaUtil, double volumenUtil, short cargaUtilR, double diametro, short potencia) {
		abrirConexion("add","localhost", "root","");
		
		try (Statement stmt=this.conexion.createStatement()){
			String query= "INSERT INTO naves VALUES (NULL,\""+nombre+"\",\""+pais+"\",\""+fabricante+"\",\""+sistLanzamiento+"\","+longitud+","+masaSeco+","+masaLanzamiento+","+cargaUtil+","+volumenUtil+","+cargaUtilR+","+diametro+","+potencia+",\"Activo\")";
			
			int filasAfectadas=stmt.executeUpdate(query);
			System.out.println("Filas actualizadas: "+filasAfectadas);
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public static void main(String[] args)  {
		Ejer35 ejer35= new Ejer35();

		short masaSeco= 3;
		short masaLanzamiento = 10;
		short cargaUtil = 5;
		short cargaUtilR = 3;
		short potencia = 4;
		 
		
		ejer35.añadirNaves("NavePrueba", "España", "Yo", "Y", 3.5, masaSeco, masaLanzamiento, cargaUtil, 23.4, cargaUtilR, 3.74, potencia);
	}
}
