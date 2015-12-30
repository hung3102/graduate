package xml;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {
	public HashMap<String,String> toMap(File f) {
		HashMap<String,String> elementMap = new HashMap<String,String>();
		try {	  
//	        File inputFile = new File(fileName);
//	        System.out.println(inputFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("student");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            	Element eElement = (Element) nNode;
	            	elementMap.put("name", eElement.getElementsByTagName("name").item(0).getTextContent());
	            	elementMap.put("gender", eElement.getElementsByTagName("gender").item(0).getTextContent());
	            	elementMap.put("birthday", eElement.getElementsByTagName("birthday").item(0).getTextContent());
	            	elementMap.put("address", eElement.getElementsByTagName("address").item(0).getTextContent());
	            	elementMap.put("native", eElement.getElementsByTagName("native").item(0).getTextContent());
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return elementMap;
	}
		
	public static void main(String[] args){
		
	}
}
