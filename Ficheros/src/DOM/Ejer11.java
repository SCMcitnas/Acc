package DOM;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer11{
	

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
		NodeList clasificacion,teams;
		int empates=0;
		String name="";
		
		clasificacion=doc.getElementsByTagName("team");
		for(int i=0; i<clasificacion.getLength();i++) {
			teams=clasificacion.item(i).getChildNodes();
			for(int j=0; j<teams.getLength();j++) {
				if(teams.item(j).getNodeName().equals("drawn")) {
					if(Integer.parseInt(teams.item(j).getFirstChild().getNodeValue())>empates) {
						empates=Integer.parseInt(teams.item(j).getFirstChild().getNodeValue());
					}
				}
			}
			
			for(int j=0; j<teams.getLength();j++) {
				boolean guardar=false;
				if(teams.item(j).getNodeName().equals("name")) {
					name=teams.item(j).getFirstChild().getNodeValue();
				}
				
				if(teams.item(j).getNodeName().equals("drawn")) {
					if(Integer.parseInt(teams.item(j).getFirstChild().getNodeValue())==empates) {
						System.out.println(name+": "+empates);
					}
				}
			}
		}
	}

	public static void main(String[] args)  {
		Ejer11 ejer11= new Ejer11();
		Document doc = ejer11.creaArbol("https://www.marca.com/marcador/futbol/iphone/1/2016_17/fase0/jornada_31/marcador.xml");
		ejer11.recorreDom(doc);
	}
}
	