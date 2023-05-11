package Conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ejer36{
	
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
	public void consultaNaves1(String pais) {
		abrirConexion("add","localhost", "root","");
		int contador=0;
		
		try (Statement stmt=this.conexion.createStatement()){
			String query= "SELECT * FROM naves WHERE pais=\""+pais+"\"";
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next ()) {
					contador++;
				
			}
			
			System.out.println("Hay "+contador+" de ese pais");
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public void consultaNaves2() {
		abrirConexion("add","localhost", "root","");
		int contador;
		List<String> estados = new ArrayList<String>();
		
		try (Statement stmt=this.conexion.createStatement()){
			String query= "SELECT DISTINCT Estado FROM naves";
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next ()) {
				estados.add(rs.getString("Estado"));
			}
			
			for(String es : estados) {
				contador=0;
				query= "SELECT * from naves WHERE Estado=\""+es+"\"";
				rs= stmt.executeQuery(query);
				
				while (rs.next ()) {
					contador++;
				}
				System.out.println("Hay "+contador+" en estado "+es);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public void consultaNaves3() {
		abrirConexion("add","localhost", "root","");

		
		try (Statement stmt=this.conexion.createStatement()){
			String query= "SELECT * FROM naves";
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next ()) {
				if(rs.getString("Estado").equals("")) {
					System.out.println("La nave "+rs.getString("nombre")+" no tiene estado");
				}
			}

			
			
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public void consultaNaves4() {
		abrirConexion("add","localhost", "root","");

		
		try (Statement stmt=this.conexion.createStatement()){
			String query= "SELECT * FROM naves";
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next ()) {
				if(rs.getInt("id")==2 || rs.getInt("id")==4) {
					
					System.out.println("Volumen de carga: ");
				}
			}

			
			
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public static void main(String[] args)  {
		Ejer36 ejer36= new Ejer36();
		//ejer36.consultaNaves1("USA");
		//ejer36.consultaNaves2();
		//ejer36.consultaNaves3();
		ejer36.consultaNaves4();
	}
}
