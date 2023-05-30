package tema55;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hola")
public class Hola {
	
		
		private String marca;
		private String modelo;
		
		public String getMarca() {
		return marca;
		}
		public void setMarca(String marca) {
		this.marca = marca;
		}
		public String getModelo() {
		return modelo;
		}
		public void setModelo(String modelo) {
		this.modelo = modelo;
		}
	
	
	
// Se ejecuta este método si se pide un Accept de tipo TEXT_PLAIN
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String textHola() {
		return "Hola Rest Soy un texto";
	}

// Se ejecuta este método si se pide un Accept de tipo TEXT_HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String htmlHola() {
		return "<html><title> Hola Rest</title><body>" + "<h1> Hola Rest</h1>" + "</body></html>";
	}

// En los dos métodos siguientes el contenido se crea “a mano” y el valor
// devuelto es un String genérico. En ejemplos siguientes veremos como
// mejorar esto
// Se ejecuta este método si se pide un Accept de tipo APPLICATION_XML
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String xmlHola() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<hola>Hola Rest. Soy un XML</hola>";
	}

	@Path("/aa/{x}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public String aa(@PathParam("x") String cad) {
		System.err.println(cad);
		return cad;
	}
	
// Se ejecuta este método si se pide un Accept de tipo APPLICATION_JSON
	@Path("/cocheDefecto")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	@Produces( MediaType.APPLICATION_ATOM_XML )
	public Hola getCarText(@FormParam("marca") String marca,
	@FormParam("modelo") String model) {
		
		if (marca.length()==0 || model.length()==0)
			throw new WebApplicationException(Response.Status.NO_CONTENT);
		Hola coche=new Hola();
		coche.setMarca(marca);
		coche.setModelo(model);
		
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
		
		return coche;
	}
	
	/*
    You have to set the “request header” section of the Firefox plugin to have a “name” = “Content-Type” and “value” = “application/x-www-form-urlencoded”
    Now, you are able to submit parameter like “name=mynamehere&title=TA” in the “request body” text area field
*/
	
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
	
	}
	
	
}