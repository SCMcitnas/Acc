package DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer5{
	

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
		Node raiz,competicion,dato,grupo,evento,team;
		NodeList competiciones,datos,grupos,eventos,clasificacion;
		
		raiz=doc.getFirstChild();
		System.out.printf(raiz.getNodeName()+"\n");
		
		competiciones=raiz.getChildNodes();
		for(int i=0; i<competiciones.getLength();i++) {
			competicion=competiciones.item(i);
			
			if(competicion.getNodeType()==Node.ELEMENT_NODE) {
				datos=competicion.getChildNodes();
				
				for(int j=0; j<datos.getLength(); j++) {
					dato=datos.item(j);
					
					if(dato.getNodeType()==Node.ELEMENT_NODE) {
						//System.out.printf(dato.getNodeName()+"\n");
						
						if(dato.getNodeName()=="grupos") {
							grupos=dato.getChildNodes();
							
							for(int k=0; k<grupos.getLength();k++) {
								grupo=grupos.item(k);
								
								if(grupo.getNodeType()==Node.ELEMENT_NODE) {
									
									if(grupo.getNodeName()=="grupo") {
										eventos=grupo.getChildNodes();
									
										for(int l=0; l<eventos.getLength();l++) {
											evento=eventos.item(l);
											System.out.println(evento.getNodeName());
											if(evento.getNodeType()==Node.ELEMENT_NODE) {
												//System.out.printf(evento.getNodeName()+"\n");
											}
										}
									}
									else if(grupo.getNodeName()=="clasificacion") {
										clasificacion=grupo.getChildNodes();
										
										for(int l=0; l<clasificacion.getLength();l++) {
											team=clasificacion.item(l);
											
											if(team.getNodeType()==Node.ELEMENT_NODE) {
												System.out.printf(team.getNodeName()+"\n");
											}
										}
									}
								}
							}
						}
						
						
						
					}
					
				}
				
				
			}
		}
	}
	
	public void recorreDom2(Document doc) {
		NodeList eventos=doc.getElementsByTagName("evento");
		for(int i=0;i<eventos.getLength();i++){
			
			NodeList hijos=eventos.item(i).getChildNodes();
			for(int j=0;j<hijos.getLength();j++){
				if (hijos.item(j).getNodeName().equals("equipolocal")) {
					System.out.println(hijos.item(j).getFirstChild().getNodeValue());
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args)  {
		Ejer5 ejer5= new Ejer5();
		Document doc = ejer5.creaArbol("https://www.marca.com/marcador/futbol/iphone/1/2016_17/fase0/jornada_31/marcador.xml");
		ejer5.recorreDom2(doc);
	}
}