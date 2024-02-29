package hu.domparse.f58kq8;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryF58KQ8 {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		try {
			//xml fájl megnyitása ahonnan beolvassuk az adatokat
            File file = new File("XMLF58KQ8.xml");

            //dokumentum létrehozása létrehozása a beolvasott fájlból
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            
            //dokumentum normalizálásáa
            document.getDocumentElement().normalize();           
            

            //1. lekérdezés: a 'Húsétel' típusú ételek nevének és árának lekérdezése  
            System.out.println("\n1. a 'Húsétel' típusú ételek nevének és árának lekérdezése:");
            NodeList nList1 = document.getElementsByTagName("étel");
            
            for (int i = 0; i < nList1.getLength(); i++) {
    			
            	Node nNode = nList1.item(i);
            	
    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

    				Element elem = (Element) nNode;
    				NodeList childNodes = nNode.getChildNodes();
    				
    				for (int j = 0; j < childNodes.getLength(); j++) {
    					
    					Node childNode = childNodes.item(j);	
    					
    					if ("típus".equals(childNode.getNodeName())) {

    						if ("Húsétel".equals(childNode.getTextContent())) {
    							System.out.println("");
    							
    							String eid = elem.getAttribute("EID");    		    				
    		    				Node node1 = elem.getElementsByTagName("név").item(0);
    		    				String node1Text = node1.getTextContent();    		    				
    		    				Node node2 = elem.getElementsByTagName("ár").item(0);
    		    				String node2Text = node2.getTextContent();    				
    		    				
    		    				System.out.println("Ételek id: " + eid);
    		    				System.out.println("Név: " + node1Text);
    		    				System.out.println("Ár: " + node2Text);	
    		    				
    						}
    					}
    				}	
    			}
    			
	            }
	            System.out.println("---------------------------------------------------");
	            
	            
	            //2. lekérdezés: azoknak a rendeléseknek lekérdezése amelyek az 1. ID-jû rendelõtõl jöttek, 10000ft alatti összeg értékben  
	            System.out.println("\n2. lekérdezés: azoknak a rendeléseknek lekérdezése, amelyek az 1. ID-jû rendelõtõl jöttek, 10000ft alatti összeg értékben:");
	            NodeList nList2 = document.getElementsByTagName("rendelés");
	            
	            for (int i = 0; i < nList2.getLength(); i++) {
	    			
	            	Node nNode = nList2.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				
	
	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				NamedNodeMap attr = nNode.getAttributes();
	    				Node nodeAttrM = attr.getNamedItem("MID");
	    				
	    				if (nodeAttrM.getTextContent().equals("1")) {
		    				for (int j = 0; j < childNodes.getLength(); j++) {
		    					
		    					Node childNode = childNodes.item(j);	
		    					
		    					if ("összeg".equals(childNode.getNodeName())) {
		
		    						if (Integer.valueOf(childNode.getTextContent()) < 10000 ) {
		    							System.out.println("");
		    							
		    							String rid = elem.getAttribute("RID");    		    				
		    		    				Node node1 = elem.getElementsByTagName("dátum").item(0);
		    		    				String node1Text = node1.getTextContent();    		    				
		    		    				Node node2 = elem.getElementsByTagName("kiszállítási_cím").item(0);
		    		    				String node2Text = node2.getTextContent();
		    		    				Node node3 = elem.getElementsByTagName("összeg").item(0);
		    		    				String node3Text = node3.getTextContent();    				
		    		    				
		    		    				
		    		    				System.out.println("Rendelés id: " + rid);
		    		    				System.out.println("Dátum: " + node1Text);
		    		    				System.out.println("Kiszállítási cím: " + node2Text);	
		    		    				System.out.println("Összeg: " + node3Text);
		    		    				
		    						}
		    					}
		    				}	
	    				}
	    			}
	    			
	    		}
	            System.out.println("---------------------------------------------------");
	            
	            
		        //3. lekérdezés: hány fizetés történt a különbözõ fizetési módok használatával
		        System.out.println("\n3. hány fizetés történt a különbözõ fizetési módok használatával:");
		        NodeList nList3 = document.getElementsByTagName("fizetés");
		          
		        int bankSzám = 0;
		    	int szépSzám = 0;
		    	int pénzSzám = 0;
		            
		        for (int i = 0; i < nList3.getLength(); i++) {        	  
		        	  
		        	Node nNode = nList3.item(i);
		            	
		        	if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				
		        		
		        		NodeList childNodes = nNode.getChildNodes();
		    				
		        		for (int j = 0; j < childNodes.getLength(); j++) {
		    					
		        			Node childNode = childNodes.item(j);	
		    					
		        			if ("fizetés_módja".equals(childNode.getNodeName())) {
		
		        				if ("bankkártya".equals(childNode.getTextContent())) {
		        					bankSzám++;	    				
		        				}
		        				else if ("szép-kártya".equals(childNode.getTextContent())) {
		        					szépSzám++;
		        				}
		        				else if ("készpénz".equals(childNode.getTextContent())) {
		        					pénzSzám++;
		        				}
		        				  
		        			}
		    			}	
		    		}    			
		    	}
		        System.out.println("\nBankkártyás fizetések száma: " + bankSzám);	
		        System.out.println("Szép-kártyás fizetések száma: " + szépSzám);	
		        System.out.println("Készpénzes fizetések száma: " + pénzSzám);	
		        System.out.println("---------------------------------------------------");
		        
		        
		        //4. lekérdezés: a NEM visszatérõ vendégek lekérdezése 
	            System.out.println("\n4. a NEM visszatérõ vendégek lekérdezése:");
	            NodeList nList4 = document.getElementsByTagName("megrendelõ");
	            
	            for (int i = 0; i < nList4.getLength(); i++) {
	    			
	            	Node nNode = nList4.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				
	
	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				for (int j = 0; j < childNodes.getLength(); j++) {
	    					
	    					Node childNode = childNodes.item(j);	
	    					
	    					if ("visszatérõ_vendég".equals(childNode.getNodeName())) {
	
	    						if ("nem".equals(childNode.getTextContent())) {
	    							System.out.println("");
	    							
	    							String mid = elem.getAttribute("MID");    		    				
	    		    				Node node1 = elem.getElementsByTagName("név").item(0);
	    		    				String node1Text = node1.getTextContent();    		    				
	
	    		    				System.out.println("Megrendelõ id: " + mid);
	    		    				System.out.println("Név: " + node1Text);
	    						}
	    					}
	    				}	
	    			}
	    			
	            }
	            System.out.println("---------------------------------------------------");
	            
	          //5. lekérdezés: a 6 ID-jû ételhez tartozó akciók lekérdezése 
	            System.out.println("\n5. a 6 ID-jû ételhez tartozó akciók lekérdezése:");
	            NodeList nList5 = document.getElementsByTagName("akció");
	            
	            for (int i = 0; i < nList5.getLength(); i++) {
	    			
	            	Node nNode = nList5.item(i);
	            	
	            	if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				
	
	    				Element elem = (Element) nNode;    				
	    				NamedNodeMap attr = nNode.getAttributes();
	    				Node nodeAttrM = attr.getNamedItem("EID");
	
	    				if (nodeAttrM.getTextContent().equals("6")) {	    				
	
		    					System.out.println("");
		    							
		    					String aid = elem.getAttribute("AID");    		    				
		    		    		Node node1 = elem.getElementsByTagName("kezdete").item(0);
		    		    		String node1Text = node1.getTextContent();    		    				
		    		    		Node node2 = elem.getElementsByTagName("vége").item(0);
		    		    		String node2Text = node2.getTextContent();
		    		    		Node node3 = elem.getElementsByTagName("leárazás").item(0);
		    		    		String node3Text = node3.getTextContent();    				
		    		    				
		    		    				
		    		    		System.out.println("Akció id: " + aid);
		    		    		System.out.println("Akció kezdete: " + node1Text);
		    		    		System.out.println("Akció vége: " + node2Text);	
		    		    		System.out.println("Leárazás mértéke: " + node3Text);
		    				}	
	    				
	            	}
	    			
	            }
	            System.out.println("---------------------------------------------------");
		
	            
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}   
         
	}
}
