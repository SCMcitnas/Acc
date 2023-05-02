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


public class Ejer23{
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
	
	public void mostrarJSON(JsonValue casa1, JsonValue casa2) {
		JsonString nombreCasa1= casa1.asJsonObject().getJsonString("name");
		JsonString nombreCasa2= casa2.asJsonObject().getJsonString("name");
		
		int contador1 = 0;
		int contador2 = 0;
		
		JsonArray miembrosCasa1 = casa1.asJsonObject().getJsonArray("swornMembers");
		JsonArray miembrosCasa2 = casa2.asJsonObject().getJsonArray("swornMembers");
		
		for(JsonValue miembroCasa1 : miembrosCasa1) {
			contador1++;
		}
		
		for(JsonValue miembroCasa2 : miembrosCasa2) {
			contador2++;
		}
		
		if(contador1>contador2) {
			System.out.println("Tiene mas miembros la casa "+nombreCasa1.getString());
		}else if(contador1<contador2){
			System.out.println("Tiene mas miembros la casa "+nombreCasa2.getString());
		}else {
			System.out.println("Tienen los mismos miembros");
		}
	}
	
	public static void main(String[] args)  {
		Ejer23 ejer23= new Ejer23();
		ejer23.mostrarJSON(ejer23.leeJSON("https://anapioficeandfire.com/api/houses/362"), ejer23.leeJSON("https://anapioficeandfire.com/api/houses/378"));

	}
}