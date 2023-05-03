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


public class Ejer26{
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
		Ejer26 ejer26= new Ejer26();
		JsonValue json = ejer26.leeJSON(link+"subjects/award:hugo_award=novel.json");
		
		String tituloM ="";
		int cantidadM = 0;
		int contador=0;
		
		JsonArray libros = json.asJsonObject().getJsonArray("works");
		for(JsonValue libro :libros) {
			JsonString titulo = libro.asJsonObject().getJsonString("title");
			JsonArray estilos = libro.asJsonObject().getJsonArray("subject");
			for(JsonValue estilo : estilos) {
				contador++;
			}
			
			if(contador> cantidadM) {
				tituloM=titulo.getString();
			}
		}
		
		System.out.println(tituloM);
	}
	
	public static void main(String[] args)  {
		Ejer26 ejer26= new Ejer26();
		ejer26.mostrarJSON("https://openlibrary.org/");

	}
}