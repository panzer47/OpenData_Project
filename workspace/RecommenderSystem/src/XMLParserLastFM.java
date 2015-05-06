import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParserLastFM {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File xml = new File("cfg/lastFM.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		LastFM obj=new LastFM();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());		
		
		NodeList root=doc.getElementsByTagName("root");		
		System.out.println(root.item(0).getTextContent());
		obj.setUrl(root.item(0).getTextContent() );
		
		
		NodeList api_key=doc.getElementsByTagName("api_key");		
		System.out.println(api_key.item(0).getTextContent());
		obj.setApi_key( api_key.item(0).getTextContent() );
		
		NodeList userlimit=doc.getElementsByTagName("userlimit");		
		System.out.println(userlimit.item(0).getTextContent());
		obj.setUserLimit( api_key.item(0).getTextContent() );
		
		NodeList nList = doc.getElementsByTagName("Class");
		
		String classes[]=new String[nList.getLength()];
		for(int i=0;i<nList.getLength();i++){			
			System.out.println( ((Element) nList.item(i)).getAttribute("id"));
			classes[i]=((Element) nList.item(i)).getAttribute("id");
			ArrayList<String> table=new ArrayList();
			table.add(((Element) nList.item(i)).getAttribute("id"));
		}
		
		
	}

}
