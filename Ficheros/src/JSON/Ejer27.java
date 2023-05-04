package JSON;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

import org.w3c.dom.Document;


public class Ejer27{
	public JsonValue leeJSON(String ruta) {
		JsonReader reader=null;
		JsonValue jsonV=null;
		try {
			if (ruta.toLowerCase().startsWith("http://")){
				URL url = new URL(ruta);
				URLConnection hc = url.openConnection();
				hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:52.0) Gecko/20100101 Firefox/52.0");
				InputStream is = url.openStream();
				reader = Json.createReader(is);
			} 
			else if (ruta.toLowerCase().startsWith("https://")) 
			{
				URL url = new URL(ruta);
				HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
				InputStream is = conn.getInputStream();
				reader = Json.createReader(is);
			} 
			else 
			{
				reader = Json.createReader(new FileReader(ruta));
			}
			
			jsonV = reader.read();
		} catch (IOException e){
			System.out.println("Error procesando documento Json" + e.getLocalizedMessage());
	}
		
		if (reader!=null) {
			reader.close();
		}
		
		return jsonV;
	}
	
	public void mostrarJSON(String link) {
		Ejer27 ejer27= new Ejer27();
		JsonValue json = ejer27.leeJSON(link+"subjects/award:hugo_award=novel.json");
		
		int contadorM=0;
		String estiloM="";
		int contador=0;
		List<String> lista = new ArrayList<String>();
		
		JsonArray libros = json.asJsonObject().getJsonArray("works");
		for(JsonValue libro :libros) {

			JsonArray estilos = libro.asJsonObject().getJsonArray("subject");
			
			for(JsonString estilo : estilos.getValuesAs(JsonString.class)) {
				lista.add(estilo.getString());
			}
		}
		
		for(int i=0; i< lista.size(); i++) {
			contador=0;
			
			for(int j=0; j<lista.size(); j++) {
				if(lista.get(i).equals(lista.get(j))) {
					contador++;
				}
			}
			
			if(contadorM<contador) {
				estiloM=lista.get(i);
				contadorM=contador;
			}
		}
		
		System.out.println("El estilo "+estiloM+" con "+contadorM);
	}
	
	public static void main(String[] args)  {
		Ejer27 ejer27= new Ejer27();
		ejer27.mostrarJSON("https://openlibrary.org/");

	}
}