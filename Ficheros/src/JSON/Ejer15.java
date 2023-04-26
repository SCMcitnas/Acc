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
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

import org.w3c.dom.Document;

import DOM.Ejer5;

public class Ejer15{
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
		JsonArray titulos = json.asJsonObject().getJsonArray("titles");
		
		for(JsonValue titulo : titulos.getValuesAs(JsonValue.class)) {
			System.out.println("Titulo: "+titulo.toString());
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args)  {
		Ejer15 ejer15= new Ejer15();
		ejer15.mostrarJSON(ejer15.leeJSON("https://anapioficeandfire.com/api/characters/583"));
		ejer15.mostrarJSON(ejer15.leeJSON("https://anapioficeandfire.com/api/characters/271"));
		ejer15.mostrarJSON(ejer15.leeJSON("https://anapioficeandfire.com/api/houses/362"));
		ejer15.mostrarJSON(ejer15.leeJSON("https://anapioficeandfire.com/api/houses/378"));
	}
}