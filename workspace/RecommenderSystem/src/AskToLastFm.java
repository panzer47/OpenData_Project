import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class AskToLastFm {
 final static String ApiKey="ac24a17112b7bcc80ffe29c96d5f6588";
 final static String Secret="31261d3ceee612649e73d26b67b5dad1";
 final static String rootURL="http://ws.audioscrobbler.com/2.0/";
 String user="panzerr";
 String pw="panzerftw4";// NOT NEEDED
 final static String USER_AGENT = "Mozilla/5.0";
 // http://www.lastfm.it/api/show/user.getInfo TUTORIAL API LASTFM
 
 
 private static LastFM[] getUsers(int userLimit){
	int i=0;
	while(i<userLimit){
		
	}
	 
	 return null;
	 
 }
 
 public static void main(String[] args) throws ParserConfigurationException, SAXException  {
	//String tempurl="http://ws.audioscrobbler.com/2.0/?method=user.getplaylists&user=panzerr&api_key=ac24a17112b7bcc80ffe29c96d5f6588&format=json";
 String tempurl="http://ws.audioscrobbler.com/2.0/?method=user.getfriends&user=rj&limit=1000&api_key=ac24a17112b7bcc80ffe29c96d5f6588";
// String tempurl="http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+distinct+%3Fbands+where+%7B%0D%0A%3Fbands+%3Chttp%3A%2F%2Fdbpedia.org%2Fontology%2Fgenre%3E+%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2FHeavy_metal_music%3E%0D%0A%7D+LIMIT+100&format=json&timeout=30000&debug=on";	
 URL url;
	try {
		url = new URL(tempurl);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		//InputStream is = url.openStream();
		
		
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		InputSource input = new InputSource(in);
		/*String inputLine;
		StringBuffer response = new StringBuffer();
		File f=new File("a");
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		f.
		f.write( response.toString());*/
		//System.out.println(response);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(input);
		doc.getDocumentElement().normalize();
		LastFM obj=new LastFM();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	
		
		in.close();
	    long limit=1000;
	    long i=0;
	    
		
	     
	     
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    
 
 
 
 }
}
