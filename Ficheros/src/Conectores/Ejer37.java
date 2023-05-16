package Conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ejer37{
	
	private Connection conexion;
	
	int filasBorradas=0;
	public void consultaNaves1(String pais) throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		PreparedStatement ps=null;
		int contador=0;
		
		String query= "SELECT * FROM naves WHERE pais = ? ";
		if(ps==null) {
			ps=conexion.prepareStatement(query);
		}
		ps.setString(1, pais);
		
		ResultSet resu= ps.executeQuery();
		
		while (resu.next ()) {
				contador++;
		}
			System.out.println("Hay "+contador+" de ese pais");

	}
	
	public void consultaNaves2() throws SQLException{
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		PreparedStatement ps=null;
		int contador;
		List<String> estados = new ArrayList<String>();
		

			String query= "SELECT DISTINCT Estado FROM naves";
			if(ps==null) {
				ps=conexion.prepareStatement(query);
			}
			
			ResultSet resu= ps.executeQuery();
			
			while (resu.next ()) {
				estados.add(resu.getString("Estado"));
			}
			
			for(String es : estados) {
				contador=0;
				query= "SELECT * from naves WHERE Estado=\""+es+"\"";
				ps=conexion.prepareStatement(query);
				
				resu= ps.executeQuery();
				
				while (resu.next ()) {
					contador++;
				}
				System.out.println("Hay "+contador+" en estado "+es);
			}
			
			

	}
	
	public void consultaNaves3() throws SQLException{
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		PreparedStatement ps=null;

		
		String query= "SELECT * FROM naves";
		if(ps==null) {
			ps=conexion.prepareStatement(query);
		}
		
		ResultSet resu= ps.executeQuery();
			while (resu.next ()) {
				if(resu.getString("Estado").equals("")) {
					System.out.println("La nave "+resu.getString("nombre")+" no tiene estado");
				}
			}

	}
	
	public void consultaNaves4() throws SQLException{
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		PreparedStatement ps=null;


			String query= "SELECT * FROM naves";
			if(ps==null) {
				ps=conexion.prepareStatement(query);
			}
			ResultSet resu= ps.executeQuery();
			while (resu.next ()) {
				if(resu.getInt("id")==2 || resu.getInt("id")==4) {
					
					System.out.println("Volumen de carga: "+(resu.getFloat("volumenUtil")+1));
				}
			}
	}
	
	public static void main(String[] args) throws SQLException  {
		Ejer37 ejer37= new Ejer37();
		//ejer37.consultaNaves1("USA");
		ejer37.consultaNaves2();
		//ejer37.consultaNaves3();
		//ejer37.consultaNaves4();
	}
}
