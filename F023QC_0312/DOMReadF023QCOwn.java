package F023QC;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class F023QCDomRead {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File("F023QC_XML.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("etterem");
		
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String id = elem.getAttribute("ekod");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("varos").item(0);
				String city = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("utca").item(0);
				String street = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("hazszam").item(0);
				String number = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("csillag").item(0);
				String stars = node5.getTextContent();

				String adr = city + ", " + street + " utca " + number + ".";
				
				
				System.out.println("Étterem ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Cím: " + adr);
				System.out.println("Csillag: " + stars);
		
			}
		}
		
		
		nList = doc.getElementsByTagName("foszakacs");
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String f_kod = elem.getAttribute("fkod");
				String e_f = elem.getAttribute("e_f");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("eletkor").item(0);
				String eletkor = node2.getTextContent();
				
				Node node3;
				String vegzettsegek = "";
				
				for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength()-1) {
						vegzettsegek += node3.getTextContent();
					}else {
						vegzettsegek += node3.getTextContent() + ", ";
					}
				}
				
				
				System.out.println("Foszakacs fkod: " + f_kod);
				System.out.println("Foszakacs e_f: " + e_f);
				System.out.println("Név: " + name);
				System.out.println("Végzettségek: " + vegzettsegek);
				System.out.println("Életkor: " + eletkor);
			}
		}
		
		nList = doc.getElementsByTagName("szakacs");
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String sz_kod = elem.getAttribute("szkod");
				String e_sz = elem.getAttribute("e_sz");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("reszleg").item(0);
				String reszleg = node2.getTextContent();
				
				Node node3;
				String vegzettsegek = "";
				
				for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength()-1) {
						vegzettsegek += node3.getTextContent();
					}else {
						vegzettsegek += node3.getTextContent() + ", ";
					}
				}
				
				
				System.out.println("Foszakacs fkod: " + sz_kod);
				System.out.println("Foszakacs e_f: " + e_sz);
				System.out.println("Név: " + name);
				System.out.println("Végzettségek: " + vegzettsegek);
				System.out.println("Életkor: " + reszleg);
			}
		}
		
		nList = doc.getElementsByTagName("gyakornok");
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String gykod = elem.getAttribute("gykod");
				String E_GY = elem.getAttribute("E_GY");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("muszak").item(0);
				String muszak = node2.getTextContent();
				
				Node node4 = elem.getElementsByTagName("kezdete").item(0);
				String kezdete = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("idotartama").item(0);
				String idotartama = node5.getTextContent();
				
				
				
				System.out.println("Gyakornok gykod: " + gykod);
				System.out.println("Gyakornok E_GY: " + E_GY);
				System.out.println("Név: " + name);
				System.out.println("Muszak: " + muszak);
				System.out.println("Kezdete: " + kezdete);
				System.out.println("Idõtartama: " + idotartama);
			}
		}
		
		nList = doc.getElementsByTagName("vendeg");
		
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String id = elem.getAttribute("Vkod");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("varos").item(0);
				String city = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("utca").item(0);
				String street = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("hazszam").item(0);
				String number = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("eletkor").item(0);
				String eletkor = node5.getTextContent();

				String adr = city + ", " + street + " utca " + number + ".";
				
				
				System.out.println("Vendég ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Cím: " + adr);
				System.out.println("Életkor: " + eletkor);
		
			}
		}
		
		nList = doc.getElementsByTagName("rendeles");
		
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String evv = elem.getAttribute("E_V_V");
				String eve = elem.getAttribute("E_V_E");

				Node node1 = elem.getElementsByTagName("orszag").item(0);
				String orszag = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("etel").item(0);
				String etel = node2.getTextContent();
				
				
				
				System.out.println("EVE: " + eve);
				System.out.println("EVV: " + evv);
				System.out.println("Ország: " + orszag);
				System.out.println("Étel: " + etel);
		
			}
		}
	}
}
