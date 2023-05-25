package tema55;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hola")
public class Hola {
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

// Se ejecuta este método si se pide un Accept de tipo APPLICATION_JSON
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String jsonHola() {
		return "{\"hola\":\"Hola Rest. Soy un JSON\"}";
	}
}