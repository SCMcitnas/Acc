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


public class Ejer18{
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
		System.out.println(json.asJsonObject().getString("name"));
		System.out.println(json.asJsonObject().getString("region"));
		System.out.println(json.asJsonObject().getString("coatOfArms"));
	}
	
	public static void main(String[] args)  {
		Ejer18 ejer18= new Ejer18();
		ejer18.mostrarJSON(ejer18.leeJSON("https://anapioficeandfire.com/api/houses/362"));
		ejer18.mostrarJSON(ejer18.leeJSON("https://anapioficeandfire.com/api/houses/378"));

	}
}