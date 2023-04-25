package DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer8{
	

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
		String nEquipo="";
		String nGuardado="";
		int goles=0;
		NodeList clasificacion,hijos;
		
		clasificacion=doc.getElementsByTagName("team");
		for(int i=0;i<clasificacion.getLength();i++) {
			hijos=clasificacion.item(i).getChildNodes();
			
			for(int j=0;j<hijos.getLength();j++) {
				
				if(hijos.item(j).getNodeName().equals("name")) {
					nGuardado=hijos.item(j).getFirstChild().getNodeValue();
				}
				
				if(hijos.item(j).getNodeName().equals("goals_scored")) {
					if(Integer.parseInt(hijos.item(j).getFirstChild().getNodeValue())>goles) {
						goles=Integer.parseInt(hijos.item(j).getFirstChild().getNodeValue());
						nEquipo=nGuardado;
					}
				}
			}
		}
		System.out.println(nEquipo+": "+goles);
		
	}
	
	public static void main(String[] args)  {
		Ejer8 ejer8= new Ejer8();
		Document doc = ejer8.creaArbol("https://www.marca.com/marcador/futbol/iphone/1/2016_17/fase0/jornada_31/marcador.xml");
		ejer8.recorreDom(doc);
	}
}