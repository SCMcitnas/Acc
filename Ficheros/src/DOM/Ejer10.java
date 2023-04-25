package DOM;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer10{
	

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
		NodeList clasificacion,hijos, eventos, equipo;
		NamedNodeMap id;
		int rank=0;
		String guardarName="";
		String name="";
		
		clasificacion=doc.getElementsByTagName("team");
		for(int i=0; i<clasificacion.getLength(); i++) {
			hijos= clasificacion.item(i).getChildNodes();
			for(int j=0; j<hijos.getLength(); j++) {
				if(hijos.item(j).getNodeName().equals("name")) {
					guardarName= hijos.item(j).getFirstChild().getNodeValue();
				}
				
				if(hijos.item(j).getNodeName().equals("rank")) {
					if(Integer.parseInt(hijos.item(j).getFirstChild().getNodeValue())>rank) {
						name=guardarName;
					}
				}
			}
		}
		
		eventos=doc.getElementsByTagName("evento");
		for(int i=0; i<eventos.getLength(); i++) {
			boolean aparece=false;
			
			equipo= eventos.item(i).getChildNodes();
			for(int j=0; j<equipo.getLength(); j++) {
				if(equipo.item(j).getNodeName().equals("equipolocal")) {
					if(equipo.item(j).getFirstChild().getNodeValue().equals(name)) {
						aparece=true;
					}
				}
				
				if(equipo.item(j).getNodeName().equals("equipovisitante")) {
					if(equipo.item(j).getFirstChild().getNodeValue().equals(name)) {
						aparece=true;
					}
				}
			}
			
			if(aparece) {
				id=eventos.item(i).getAttributes();
				System.out.println(id.item(0));
			}
		}
	}
	
	public static void main(String[] args)  {
		Ejer10 ejer10= new Ejer10();
		Document doc = ejer10.creaArbol("https://www.marca.com/marcador/futbol/iphone/1/2016_17/fase0/jornada_31/marcador.xml");
		ejer10.recorreDom(doc);
	}
}