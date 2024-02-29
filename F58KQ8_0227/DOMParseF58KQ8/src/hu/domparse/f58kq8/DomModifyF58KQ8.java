package hu.domparse.f58kq8;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyF58KQ8 {

	public static void main(String[] args) {
		try {
			//xml fájl megnyitása ahonnan beolvassuk az adatokat
			File file = new File("XMLF58KQ8.xml");
	
			//dokumentum létrehozása a beolvasott fájlból
	        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = documentBuilder.parse(file);
	        
	      //dokumentum normalizálásáa
	        document.getDocumentElement().normalize();
	        
	        //1. módosítás - a 'szép-kártyával' történõ fizetések átírása 'bankkártyára':
	        NodeList fizetesNList = document.getElementsByTagName("fizetés");
	        for (int temp = 0; temp < fizetesNList.getLength(); temp++) {
				Node node = fizetesNList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {
						
						Node childNode = childNodes.item(j);	
						
						if ("fizetés_módja".equals(childNode.getNodeName())) {
	
							if ("szép-kártya".equals(childNode.getTextContent())) {
								childNode.setTextContent("bankkártya");							
							}
						}
					}	
				}
			}
	        
	      //2. módosítás - a harmadik megrendelés megrendelõjét a harmadik megrendelõre változtatjuk, az elsõ megrendelõ helyett:
	        NodeList rendelNList = document.getElementsByTagName("rendelés");
	        for (int temp = 0; temp < rendelNList.getLength(); temp++) {
				Node node = rendelNList.item(temp);
				
				NamedNodeMap attr = node.getAttributes();
				Node nodeAttrM = attr.getNamedItem("MID");
				Node nodeAttrR = attr.getNamedItem("RID");
				
				if (nodeAttrR.getTextContent().equals("3")) {
					nodeAttrM.setTextContent("3");
				}
			}
	        
	        //3. módosítás - a 10% leárazású akciók növelése 20-ra
	        NodeList akcioNList = document.getElementsByTagName("akció");
	        for (int temp = 0; temp < akcioNList.getLength(); temp++) {
				Node node = akcioNList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {
						
						Node childNode = childNodes.item(j);	
						
						if ("leárazás".equals(childNode.getNodeName())) {
							
							if ("10".equals(childNode.getTextContent())) {
								
								childNode.setTextContent("20");							
							}
						}
					}	
				}
			}
	        
	        //4. módosítás - a Piedone nevû pizza árának csökkentése 500 forinttal
	        NodeList etelNList = document.getElementsByTagName("étel");
	        for (int temp = 0; temp < etelNList.getLength(); temp++) {
				Node node = etelNList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {					
						Node childNode = childNodes.item(j);	
						
						if ("név".equals(childNode.getNodeName())) {						
							if ("Piedone".equals(childNode.getTextContent())) {
								
								for (int k = 0; k < childNodes.getLength(); k++) {
									childNode = childNodes.item(k);	
									if ("ár".equals(childNode.getNodeName())) {
										childNode.setTextContent("1750");		
									}
								}												
							}
						}
					}	
				}
			}
	        
	        //5. módosítás - azok a megrendelõk, akik eddig nem voltak visszatérõ vendégek, azokat azzá módosítani
	        NodeList megrendeloNList = document.getElementsByTagName("megrendelõ");
	        for (int temp = 0; temp < megrendeloNList.getLength(); temp++) {
				Node node = megrendeloNList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {					
						Node childNode = childNodes.item(j);	
						
						if ("visszatérõ_vendég".equals(childNode.getNodeName())) {											
								
							if ("nem".equals(childNode.getTextContent())) {
								childNode.setTextContent("igen");							
							}
								
						}
					}	
				}
			}
	        
	        //a módosított adatok kiírása mind konzolra mind fájlba
	        File modFile = new File("MOD_XMLF58KQ8.xml");
	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	
	        DOMSource source = new DOMSource(document);
	
	        System.out.println("---- Modositott fajl -----");       
	        
	        StreamResult consoleResult = new StreamResult(System.out);
	        StreamResult resultModFile = new StreamResult(modFile);
	        
	        transformer.transform(source, consoleResult);
	        transformer.transform(source, resultModFile );
	        
		} catch (Exception e ) {
            e.printStackTrace();
        }
	}
}
