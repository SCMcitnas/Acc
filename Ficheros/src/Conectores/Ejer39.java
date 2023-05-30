package Conectores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Ejer39{
	
	
	public void consultaNaves1(String pais) throws SQLException {
		
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		
		DatabaseMetaData dbmd = conexion.getMetaData();
		ResultSet bases = dbmd.getCatalogs();
		
		while(bases.next()) {
			System.out.println(bases.getString("DATABASE_NAME"));
		}
	}
	

	
	
	public static void main(String[] args) throws SQLException  {
		Ejer39 ejer39= new Ejer39();
		ejer39.consultaNaves1("USA");

	}
}
