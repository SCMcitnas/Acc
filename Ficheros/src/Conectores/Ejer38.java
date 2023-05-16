package Conectores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
DELIMITER //
CREATE PROCEDURE getPaises (IN cad VARCHAR(10))
BEGIN
SELECT * FROM naves WHERE pais LIKE CONCAT("%",cad,"%");
END //
DELIMITER ;*/


public class Ejer38{
	
	private Connection conexion;
	
	public void consultaNaves1(String pais) throws SQLException {
		Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true","root", "");
		
		CallableStatement cs = conexion.prepareCall("CALL getPaises(?)");
		
		cs.setString(1, pais);
		ResultSet resultado = cs.executeQuery();
		
		while(resultado.next()) {
			System.out.println(resultado.getString("nombre"));
		}
	}
	

	
	
	public static void main(String[] args) throws SQLException  {
		Ejer38 ejer38= new Ejer38();
		ejer38.consultaNaves1("USA");

	}
}
