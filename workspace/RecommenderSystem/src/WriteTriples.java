import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import others.LastFM;
import Datatypes.Artist;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;

public class WriteTriples {

	public static void main(int args[]) {
		ArrayList<Object> test = new ArrayList<Object>();
		/*
		 * for(int i=0;i<100;i++) { Artist a=new Artist(); a.setName("a"+i);
		 * User }
		 */
	}

	public static void buildTriples(List<Object> fullList)
			throws ParserConfigurationException, SAXException, IOException {
		Model m = ModelFactory.createDefaultModel();
		
		long identifier = 0;
		for (int ii = 0; ii < fullList.size(); ii++) {
			File xml = new File("cfg/lastFM.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Class");
			String classes[] = new String[nList.getLength()];
			boolean classetrovata = false;
			for (int i = 0; (i < nList.getLength() && classetrovata == false); i++) {
				System.out
						.println(((Element) nList.item(i)).getAttribute("id"));
				String nomeClasseJava = ((Element) nList.item(i))
						.getAttribute("id");
				String individual = fullList.get(ii).getClass().toString();

				individual = individual.substring(16);// 5+1+10
				if (individual.equals(nomeClasseJava)) {
					// QUI HO TROVATO IL NOME CLASSE JAVA SULLXML
				
					classetrovata = true;
					ArrayList<String> name = new ArrayList<String>();
					ArrayList<String> position = new ArrayList<String>();
					ArrayList<String> urlProperty = new ArrayList<String>();
					String urlResource = "";
					String key = ((Element) nList.item(i)).getAttribute("key");
					classes[i] = ((Element) nList.item(i)).getAttribute("id");
					String type=((Element) nList.item(i)).getAttribute("type");
					NodeList childs = nList.item(i).getChildNodes();
					
					for (int l = 0; l < childs.getLength(); l++) {
						
						if (childs.item(l).getNodeName().equals("url")) {
							// NOME URL CLASSE
							identifier++;
							
							urlResource = childs.item(l).getTextContent();
							
						} else if (childs.item(l).getNodeName()
								.equals("output")) {
							for (int w = 0; w < childs.item(l).getChildNodes()
									.getLength(); w++) {
								// NOMEATTRIBUTO
								
								if (childs.item(l).getChildNodes().item(w)
										.getNodeName().equals("name")) {

									name.add(childs.item(l).getChildNodes()
											.item(w).getTextContent());
								} else if (childs.item(l).getChildNodes()
										.item(w).getNodeName()
										.equals("position")) {
									
									position.add(childs.item(l).getChildNodes()
											.item(w).getTextContent());
								} else if (childs.item(l).getChildNodes()
										.item(w).getNodeName().equals("url")) {
									System.out.println(childs.item(l)
											.getChildNodes().item(w)
											.getTextContent()
											+ "SONOENTRATO");
									urlProperty.add(childs.item(l)
											.getChildNodes().item(w)
											.getTextContent());
								}
							}
						}

					}

					String[] values = fullList.get(ii).toString().split("//");
					System.out.println(Integer.parseInt(key));
					System.out.println(urlResource + "----0 "
							+ fullList.get(ii).toString());
					Resource r = m.createResource(urlResource
							+ values[Integer.parseInt(key) - 1]);// );
					Property taip=m.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
					r.addProperty(taip, type);
					for (int j = 0; j < values.length; j++) {
						Property newProperty = m.createProperty(urlProperty
								.get(j));
						r.addProperty(newProperty, values[j]);
					}

				}

			}
			NodeList relations = doc.getElementsByTagName("Relation");
			boolean trovato=false;
			
			for (int i = 0; (i < relations.getLength()&&trovato==false); i++) {
				System.out.println(((Element) relations.item(i))
						.getAttribute("id"));
				
				String individual = fullList.get(ii).getClass().toString();
				individual = individual.substring(16);
				String url="", origin="", destination="";
				if (individual.equals(((Element) relations.item(i))
						.getAttribute("id"))) {
					trovato=true;
					

					NodeList childs = relations.item(i).getChildNodes();
					for (int l = 0; l < childs.getLength(); l++) {
						if (childs.item(l).getNodeName().equals("url")) {
							
							url=childs.item(l).getTextContent();
						} else if (childs.item(l).getNodeName()
								.equals("origin")) {
							
							origin=childs.item(l).getTextContent();
						} else if (childs.item(l).getNodeName()
								.equals("destination")) {
							
							destination=childs.item(l).getTextContent();
						}
					}
					String[] relazione=fullList.get(ii).toString().split("//");
					System.out.println(fullList.get(ii).toString());
					System.out.println(origin+"/"+relazione[0]);
					System.out.println((destination+"/"+relazione[1]));
					System.out.println(url);
					
					Resource or=m.getResource(origin+"/"+relazione[0]);
					Resource des=m.getResource(destination+"/"+relazione[1]);
					
					Property p=m.createProperty(url);
					or.addProperty(p, des);
					
				}
			}
		}
		String nameFile = "dumpLASTFM.rdf";
		PrintWriter writer = new PrintWriter(nameFile, "UTF-8");
		m.write(writer, "RDF/XML");
		
		m.close();
		//dataset.close();
	}


}
