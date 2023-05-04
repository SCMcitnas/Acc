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


public class Ejer30{
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
		Ejer30 ejer30= new Ejer30();
		JsonValue json = ejer30.leeJSON(link+"subjects/fiction.json");
		
		JsonArray obras = json.asJsonObject().getJsonArray("works");
		for(JsonValue obra : obras) {
			JsonString titulo = obra.asJsonObject().getJsonString("title");
			JsonArray autores = obra.asJsonObject().getJsonArray("authors");
			System.out.println(titulo.getString());
			for(JsonValue autor : autores) {
				JsonString nombre = autor.asJsonObject().getJsonString("name");
				System.out.println("\t-"+nombre.getString());
			}
		}
		
	}
	
	public static void main(String[] args)  {
		Ejer30 ejer30= new Ejer30();
		ejer30.mostrarJSON("https://openlibrary.org/");

	}
}