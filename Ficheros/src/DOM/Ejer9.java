package DOM;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer9{
	

	public Document creaArbol(String ruta) {
		Document doc=null;
		
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setIgnoringComments(true);
			DocumentBuilder builder = factoria.newDocumentBuilder();
			doc=builder.parse(ruta);
		} catch (Exception e) {
			System.out.println("Error generando el árbol DOM: " +e.getMessage());
		}
		
		return doc;
	}
	
	public void recorreDom(Document doc) {
		
		NodeList eventos, canal;
		ArrayList<String> canales= new ArrayList<String>();
		String prueba;
		
		
		eventos=doc.getElementsByTagName("evento");
		for(int i=0; i<eventos.getLength(); i++) {
			canal=eventos.item(i).getChildNodes();
			
			for(int j=0; j<canal.getLength(); j++) {
				if(canal.item(j).getNodeName().equals("tv")) {
					boolean añadir= true;
					
					for(int m=0; m<canales.size(); m++) {
						if(canal.item(j).getFirstChild().getNodeValue().equals(canales.get(m))) {
							añadir =false;
						}
					}
					
					if(añadir) {
						canales.add(canal.item(j).getFirstChild().getNodeValue());
					}
				}
			}
		}
		
		for(int m=0; m<canales.size(); m++) {
			System.out.println(canales.get(m));
		}
	}
	
	public static void main(String[] args)  {
		Ejer9 ejer9= new Ejer9();
		Document doc = ejer9.creaArbol("https://www.marca.com/marcador/futbol/iphone/1/2016_17/fase0/jornada_31/marcador.xml");
		ejer9.recorreDom(doc);
	}
}