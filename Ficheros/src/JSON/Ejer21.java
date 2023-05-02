package JSON;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

import org.w3c.dom.Document;


public class Ejer21{
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
	
	public void mostrarJSON(JsonValue json) {
		Ejer21 ejer21= new Ejer21();
		if(json.asJsonObject().containsKey("books")) {
			JsonArray libros = json.asJsonObject().getJsonArray("books");
			
			for(JsonString libro : libros.getValuesAs(JsonString.class)) {
				String link= libro.getString();
				JsonObject datosLibro= ejer21.leeJSON(link).asJsonObject();
				
				System.out.printf("Nombre: %s\nISBN: %s\n", datosLibro.getString("name"),datosLibro.getString("isbn"));
				
				JsonArray autores = datosLibro.getJsonArray("authors");
				System.out.println("Autores:");
				for(JsonString autor: autores.getValuesAs(JsonString.class)) {
					System.out.println("\t-"+autor.getString());
				}
				
				System.out.println("Numero de paginas: "+datosLibro.getInt("numberOfPages"));
				System.out.println("Tipo de libro: "+datosLibro.getString("mediaType"));
				System.out.println("Fecha lanzamiento: "+datosLibro.getString("released")+"\n");
			}
			
		}
		
		if(json.asJsonObject().containsKey("povBooks")) {
			JsonArray libros = json.asJsonObject().getJsonArray("povBooks");
			
			for(JsonString libro : libros.getValuesAs(JsonString.class)) {
				String link= libro.getString();
				JsonObject datosLibro= ejer21.leeJSON(link).asJsonObject();
				
				System.out.printf("Nombre: %s\nISBN: %s\n", datosLibro.getString("name"),datosLibro.getString("isbn"));
				
				JsonArray autores = datosLibro.getJsonArray("authors");
				System.out.println("Autores:");
				for(JsonString autor: autores.getValuesAs(JsonString.class)) {
					System.out.println("\t-"+autor.getString());
				}
				
				System.out.println("Numero de paginas: "+datosLibro.getInt("numberOfPages"));
				System.out.println("Tipo de libro: "+datosLibro.getString("mediaType"));
				System.out.println("Fecha lanzamiento: "+datosLibro.getString("released")+"\n");
			}
			
		}
	}
	
	public static void main(String[] args)  {
		Ejer21 ejer21= new Ejer21();
		ejer21.mostrarJSON(ejer21.leeJSON("https://anapioficeandfire.com/api/characters/583"));
		ejer21.mostrarJSON(ejer21.leeJSON("https://anapioficeandfire.com/api/characters/271"));

	}
}