package F023QC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

public class F023QCDomModify {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		File xmlFile = new File("F023QC_XML.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		
		NodeList nList = doc.getElementsByTagName("vendeg");
		
		for(int i= 0; i< nList.getLength();i++){
			Node nNode = nList.item(i);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;

				String id = elem.getAttribute("Vkod");
				if(id.equalsIgnoreCase("v1")){
					Node node1 = elem.getElementsByTagName("nev").item(0);
					node1.setTextContent("Kiss Vilmos");
										
					Node node5 = elem.getElementsByTagName("eletkor").item(0);
					node5.setTextContent("55");
				}
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
				
				elem.setAttribute("E_GY", "e3");
			}
		}
		
		
		Node vendeglatas = doc.getFirstChild();
        NodeList childNodes = vendeglatas.getChildNodes();
        
        for(int count = 0; count < childNodes.getLength(); count++) {
           Node node = childNodes.item(count);
           
           if("rendeles".equals(node.getNodeName()))
        	   vendeglatas.removeChild(node);
        }
		
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        FileWriter fileWriter= new FileWriter("xml2.xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        transformer.transform(new DOMSource(doc), new StreamResult(System.out));
        transformer.transform(new DOMSource(doc), new StreamResult(fileWriter));
        
        
        
	}
}
