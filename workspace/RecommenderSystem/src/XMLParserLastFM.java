import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import others.LastFM;


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
		
		Model m=ModelFactory.createDefaultModel();
		//Resource r=m.createResource("http://www.example.com/laputtanadituamadre" );
	//	Property sex=m.createProperty("http://example.org/ontology/sex");
	//	r.addProperty(sex, "maseisicuro?");
		String nameFile="testSQL.rdf";
	//	PrintWriter writer = new PrintWriter(nameFile, "UTF-8");
//		m.write( writer, "RDF/XML");
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
			NodeList childs=nList.item(i).getChildNodes();
			for(int l=0;l<childs.getLength();l++){
				
				if(childs.item(l).getNodeName().equals("url")) {
					// NOME URL CLASSE
					System.out.println(childs.item(l).getTextContent());
				}else if(childs.item(l).getNodeName().equals("output")) {
					for(int w=0;w< childs.item(l).getChildNodes().getLength();w++) {
						// NOMEATTRIBUTO
						//System.out.println(childs.item(l).getChildNodes().item(w).getNodeName());
						if(childs.item(l).getChildNodes().item(w).getNodeName().equals("name")) {
							//System.out.println(
								//	childs.item(l).getChildNodes().item(w).getTextContent()+"name");
						}else if(childs.item(l).getChildNodes().item(w).getNodeName().equals("position")) {
							//System.out.println(
									//childs.item(l).getChildNodes().item(w).getTextContent()+"position");
						}else if(childs.item(l).getChildNodes().item(w).getNodeName().equals("url")) {
							System.out.println(
									childs.item(l).getChildNodes().item(w).getTextContent()+"sonoENTRATOOOOOOOO");
						}			
					}
				}
			}			
		}
		NodeList relations = doc.getElementsByTagName("Relation");
		for(int i=0; i<relations.getLength();i++) {
			System.out.println( ((Element) relations.item(i)).getAttribute("id"));
			classes[i]=((Element) relations.item(i)).getAttribute("id");
			NodeList childs=relations.item(i).getChildNodes();
			for(int l=0;l<childs.getLength();l++) {
				if(childs.item(l).getNodeName().equals("url")) {
					System.out.println(childs.item(l).getTextContent() +"url" );
				}else if(childs.item(l).getNodeName().equals("origin")) {
					System.out.println(childs.item(l).getTextContent() +"origin" );
				}else if(childs.item(l).getNodeName().equals("destination")) {
					System.out.println(childs.item(l).getTextContent() +"destinescion");
				}
			}
		}
		
		
		
	}

}
