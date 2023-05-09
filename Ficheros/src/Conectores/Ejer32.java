package Conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejer32{
	
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

	public void consultaNaves(String columna, Object dato) {
		Object guardar;
		abrirConexion("add","localhost", "root","");
		
		try (Statement stmt=this.conexion.createStatement()){
			String query="select * from naves";
		
			ResultSet rs= stmt.executeQuery(query);
		
			
			while (rs.next ()) {
				guardar="";
				try {
					if(rs.getObject(columna).getClass().equals(String.class)) {
						guardar=rs.getString(columna);
					}else{
						guardar=rs.getObject(columna);
					}
					
					if(guardar.toString().equals(dato.toString())) {
						System.out.println (rs.getInt(1) + "\t" + rs.getString("nombre") +"\t" + rs.getString("pais"));
					}
				}catch(NullPointerException e) {
					guardar=rs.getObject(columna);
					if(dato == null && guardar == null) {
						System.out.println (rs.getInt(1) + "\t" + rs.getString("nombre") +"\t" + rs.getString("pais"));
					}
				}

			}
			
		} catch (SQLException e) {
			System.out.println("Se ha producido un error: "+e.getLocalizedMessage());
			
		} finally {
			cerrarConexion(); 
		}
	}
	
	public static void main(String[] args)  {
		Ejer32 ejer32= new Ejer32();
		ejer32.consultaNaves("longitud",null);

	}
}
