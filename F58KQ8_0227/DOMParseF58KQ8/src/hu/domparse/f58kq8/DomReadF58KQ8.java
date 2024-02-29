package hu.domparse.f58kq8;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadF58KQ8 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		//xml fájl megnyitása ahonnan beolvassuk az adatokat
		File xmlFile = new File("XMLF58KQ8.xml");
		
		//dokumentum létrehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//dokumentum létrehozása a beolvasott fájlból, majd annak a normalizálásáa
		Document document = dBuilder.parse(xmlFile);		
		document.getDocumentElement().normalize();
		
		System.out.println("Root element: " + document.getDocumentElement().getNodeName());
		
		//beolvasó metódusok meghívása
		getRendlés(document);
		
		getMegrendelõ(document);
		
		getFizetés(document);
		
		getKapcsolo(document);
		
		getÉtelek(document);
		
		getAkció(document);
		
		 //a módosított adatok kiírása txt fájlba fájlba
        File modFile = new File("XMLF58KQ8.txt");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);   
        
        StreamResult resultModFile = new StreamResult(modFile);        
        transformer.transform(source, resultModFile );
		
		
	}
	
	public static void getRendlés(Document doc) {
		NodeList nList = doc.getElementsByTagName("rendelés");
		
		System.out.println("\nMegrendelések:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String rid = elem.getAttribute("RID");
				String mid = elem.getAttribute("MID");
				String fid = elem.getAttribute("FID");
				
				Node node1 = elem.getElementsByTagName("dátum").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("kiszállítási_cím").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("összeg").item(0);
				String node3Text = node3.getTextContent();	
				
				System.out.println("Rendelés id: " + rid);
				System.out.println("Megrendelõ id: " + mid);
				System.out.println("Fizetés id: " + fid);
				System.out.println("Dátum: " + node1Text);
				System.out.println("Kiszállítási cím: " + node2Text);
				System.out.println("Összeg: " + node3Text);
			}
			
		}
	}	
	
	public static void getMegrendelõ(Document doc) {
		NodeList nList = doc.getElementsByTagName("megrendelõ");
		
		System.out.println("\nMegrendelõk:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String mid = elem.getAttribute("MID");
				
				Node node1 = elem.getElementsByTagName("visszatérõ_vendég").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("telefonszám").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("név").item(0);
				String node3Text = node3.getTextContent();	
				
				System.out.println("Megrendelõ id: " + mid);
				System.out.println("Visszatérõ vendég: " + node1Text);
				System.out.println("Telefonszám: " + node2Text);
				System.out.println("Név: " + node3Text);
			}
			
		}
	}	
	
	public static void getFizetés(Document doc) {
		NodeList nList = doc.getElementsByTagName("fizetés");
		
		System.out.println("\nFizetések:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String fid = elem.getAttribute("FID");
				
				Node node1 = elem.getElementsByTagName("átvitel_ideje").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("kupon").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("fizetés_módja").item(0);
				String node3Text = node3.getTextContent();	
				
				System.out.println("Fizetés id: " + fid);
				System.out.println("Átvitel ideje: " + node1Text);
				System.out.println("Kupon: " + node2Text);
				System.out.println("Fizetés módja: " + node3Text);
			}
			
		}
	}
	
	public static void getKapcsolo(Document doc) {
		NodeList nList = doc.getElementsByTagName("rendelt");
		
		System.out.println("\nMegrendelt ételek kapcsolói:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String rid = elem.getAttribute("RID");
				String eid = elem.getAttribute("EID");
				
				Node node1 = elem.getElementsByTagName("mennyiség").item(0);
				String node1Text = node1.getTextContent();
				
				System.out.println("Rendelés id: " + rid);
				System.out.println("Étel id: " + eid);
				System.out.println("Mennyiség: " + node1Text);
			}
			
		}
	}
	
	public static void getÉtelek(Document doc) {
		NodeList nList = doc.getElementsByTagName("étel");
		
		System.out.println("\nÉtelek:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String eid = elem.getAttribute("EID");
				
				Node node1 = elem.getElementsByTagName("név").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("ár").item(0);
				String node2Text = node2.getTextContent();
				
				
				
				
				
				System.out.println("Ételek id: " + eid);
				System.out.println("Név: " + node1Text);
				System.out.println("Ár: " + node2Text);
				
				int counter = 0;
				
				while (elem.getElementsByTagName("allergén").item(counter)!=null) {
					Node node3 = elem.getElementsByTagName("allergén").item(counter);
					String node3Text = node3.getTextContent();	
					System.out.println("Allergén: " + node3Text);
					counter++;
				}
				
				
				Node node4 = elem.getElementsByTagName("típus").item(0);
				String node4Text = node4.getTextContent();	
				
				System.out.println("Típus: " + node4Text);
			}
			
		}
	}
	
	public static void getAkció(Document doc) {
		NodeList nList = doc.getElementsByTagName("akció");
		
		System.out.println("\nAkciók:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String aid = elem.getAttribute("AID");
				String eid = elem.getAttribute("EID");
				
				Node node1 = elem.getElementsByTagName("kezdete").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("vége").item(0);
				String node2Text = node2.getTextContent();
								
				Node node3 = elem.getElementsByTagName("leárazás").item(0);
				String node3Text = node3.getTextContent();	
				
				System.out.println("Akció id: " + aid);
				System.out.println("Étel id: " + eid);
				System.out.println("Kezdete: " + node1Text);
				System.out.println("Vége: " + node2Text);
				System.out.println("Leárazás: " + node3Text);
			}
			
		}
	}
}
