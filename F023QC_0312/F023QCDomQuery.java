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

public class F023QCDomQuery {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		File xmlFile = new File("F023QC_XML.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		
		NodeList nList = doc.getElementsByTagName("szakacs");
		
		System.out.println("Azok a szakacsok, akiknek van középiskolai végzettsége:");
		for(int i= 0; i< nList.getLength();i++){
			int koz = 0;
			Node nNode = nList.item(i);
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
					if(node3.getTextContent().equalsIgnoreCase("Szakközépiskola")){
						koz=1;
					}
				}
				
				if(koz==1){
					System.out.println("Szakacs fkod: " + sz_kod);
					System.out.println("Szakacs e_f: " + e_sz);
					System.out.println("Név: " + name);
					System.out.println("Végzettségek: " + vegzettsegek);
					System.out.println("Részleg: " + reszleg);
					System.out.println("");
				}
			}
		}
		
		System.out.println("Azok az éttermek, amik 5 csillagosak:");
		nList = doc.getElementsByTagName("etterem");
		
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
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
				
				if(stars.equalsIgnoreCase("5")){
					System.out.println("Étterem ID: " + id);
					System.out.println("Név: " + name);
					System.out.println("Cím: " + adr);
					System.out.println("Csillag: " + stars);
					System.out.println("");
				}

		
			}
		}
		
		nList = doc.getElementsByTagName("gyakornok");
		System.out.println("Azok a gyakornokok, akik délután dolgoznak:");
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
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
				
				
				if(muszak.equalsIgnoreCase("Délután")){
					System.out.println("Gyakornok gykod: " + gykod);
					System.out.println("Gyakornok E_GY: " + E_GY);
					System.out.println("Név: " + name);
					System.out.println("Muszak: " + muszak);
					System.out.println("Kezdete: " + kezdete);
					System.out.println("Idõtartama: " + idotartama);
					System.out.println("");
				}

			}
		}
		
		
	}
}
