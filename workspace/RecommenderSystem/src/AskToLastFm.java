import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AskToLastFm {
 final static String ApiKey="ac24a17112b7bcc80ffe29c96d5f6588";
 final static String Secret="31261d3ceee612649e73d26b67b5dad1";
 final static String rootURL="http://ws.audioscrobbler.com/2.0/";
 String user="panzerr";
 String pw="panzerftw4";// NOT NEEDED
 final static String USER_AGENT = "Mozilla/5.0";
 // http://www.lastfm.it/api/show/user.getInfo TUTORIAL API LASTFM
 
 
 private static User[] getUsers(int userLimit){
	int i=0;
	while(i<userLimit){
		
	}
	 
	 return null;
	 
 }
 
 public static void main(String[] args)  {
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
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	    JSONParser parser=new JSONParser();	
	    JSONObject obj=  (JSONObject) parser.parse(response.toString());
	    
	    int i=0;
	   
	    while(obj.size()>i){
	    	
	    	System.out.println(obj.toString());
	    	i++;
	    }
		//JSONObject json = new JSONObject();
		//print result
	//	System.out.println("OUTPUT QUERY:");
		//System.out.println(response.toString());
	     
	     
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch ( ParseException e) {
		e.printStackTrace();
	}
    
 
 
 
 }
}
