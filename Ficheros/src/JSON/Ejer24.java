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


public class Ejer24{
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
		Ejer24 ejer24= new Ejer24();
		JsonValue json = ejer24.leeJSON(link+"subjects/award:hugo_award=novel.json");
		JsonArray ganadores = json.asJsonObject().getJsonArray("works");
		for(JsonValue obra : ganadores) {
			JsonString titulo = obra.asJsonObject().getJsonString("title");
			System.out.println("Obra: "+titulo.getString());
			JsonArray autores = obra.asJsonObject().getJsonArray("authors");
			System.out.println("Autores: ");
			for(JsonValue autor : autores) {
				JsonString nombreAutor = autor.asJsonObject().getJsonString("name");
				System.out.println("\t-"+nombreAutor.getString());
			}
		}
	}
	
	public static void main(String[] args)  {
		Ejer24 ejer24= new Ejer24();
		ejer24.mostrarJSON("https://openlibrary.org/");

	}
}