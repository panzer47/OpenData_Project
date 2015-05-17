import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
 
 
 private static LastFM[] getUsers( String url, int userLimit){
	int i=0;
	while(i<userLimit){
		
	}
	 
	 return null;
	 
 }
 
 public static void main(String[] args) throws ParserConfigurationException, SAXException, InterruptedException, UnsupportedEncodingException  {
	//String tempurl="http://ws.audioscrobbler.com/2.0/?method=user.getplaylists&user=panzerr&api_key=ac24a17112b7bcc80ffe29c96d5f6588&format=json";
	 String id="rj"; int lim=25;
	 ArrayList<String> listIDusers=new ArrayList<String>();
	 List<User> users=new ArrayList<User>();
	 List<Artist> artists=new ArrayList<Artist>();
	 List<IsDescribed> relations=new ArrayList<IsDescribed>();
	 List<Tag> tags=new ArrayList<Tag>();
	 List<Song> songs=new ArrayList<Song>();
	 List<Like> like=new ArrayList<Like>();
	 //List<>
     searchUsers(id, 1000, listIDusers, users);
 // System.out.println("trololol + "+listIDusers.size()+"- "+users.get(19).getSex());
  for(int i=0; i<listIDusers.size();i++) {
	 // searchLikes(listIDusers.get(i), lim, songs, artists, like, "LIKE" );
	  //searchLikes(listIDusers.get(i), lim, songs, artists, like, "DISLIKE" );
	  //searchTags();
  }
  System.out.println("1- "+users.size()+"- "+artists.size()+" - "+like.size()+" - "+ songs.size());
  int lilly=0;
  for(int i=0;i<artists.size();i++) {
	 // System.out.println("artistname: "+artists.get(i).getName());
	  //tagsPerArtist(artists.get(i), relations,tags, 5 );
	  if(artists.get(i).getMbid().isEmpty()) lilly++;
  }
  System.out.println("artists null: "+lilly+" full: "+(songs.size()-lilly) );
  lilly=0;
  for(int i=0;i<songs.size();i++) {
	  //System.out.println("songname: "+ songs.get(i).getName()+"artist: "+songs.get(i).getArtist() );
	 // tagsPerSongs(songs.get(i), relations, tags, 5);
	  if(songs.get(i).getMbid().isEmpty()) lilly++;
  }
  for(int i=0; i<users.size()-1;i++) {
	  for(int k=i+1; k<users.size();k++) {
		  if(users.get(i).getId().equals( users.get(k).getId())) System.out.println("MADONNA PUTTANA E MAIALA E SCHIFOSA");;
	  }
  }
  System.out.println("songs null: "+lilly+" full: "+( songs.size()-lilly ));
 }
 
 
 private static void tagsPerSongs(Song song, List<IsDescribed> relations, List<Tag> tags, int lim) throws InterruptedException, ParserConfigurationException, SAXException, UnsupportedEncodingException {
	 String codeSong=(URLEncoder.encode(song.getName(), "UTF-8"));
	 String codeArtist=(URLEncoder.encode(song.getArtist(), "UTF-8"));
	 String tempurl;
	 if(song.getMbid().isEmpty()) {
		 tempurl=rootURL+"?method=track.gettoptags&artist="+codeArtist+"&track="+codeSong+"&limit="+lim+"&api_key="+ApiKey;
	 }else{
		 tempurl=rootURL+"?method=track.gettoptags&mbid="+song.getMbid()+"&api_key="+ApiKey;
	 }
	 int tagFounded=0;
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
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("tag");
			//System.out.println(nList.getLength());
			//String classes[]=new String[nList.getLength()];
			for(int i=0;i<nList.getLength();i++){			
				//System.out.println( ((Element) nList.item(i)).getAttribute("id"));
				//System.out.println(  nList.item(i).g );
				//System.out.println(  );
				NodeList intra=nList.item(i).getChildNodes();
				Tag toAdd=new Tag();
				IsDescribed<Song> relation=new IsDescribed<Song>();
				for(int l=0; l<intra.getLength()&&lim>tagFounded;l++) {
					
					
					if(intra.item(l).getNodeName().equals("name")) {
						// THIS IS THE NAME OF THE SONGS OUR USERS LIKE
						//System.out.println("porco"+intra.item(l).getTextContent() ); 
						//toAdd.setName(intra.item(l).getTextContent());
						System.out.println("taaaaag: "+intra.item(l).getTextContent());
						toAdd.setName(intra.item(l).getTextContent());
						if(!tags.contains(toAdd)) {
							tags.add(toAdd);
						}
						relation.setTag(intra.item(l).getTextContent() );
						relation.setLabel(song);
						tagFounded++;
					}
				}				
			}
			
			TimeUnit.MILLISECONDS.sleep(111);
			in.close();
		    	     
		     
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 
 
 private static void tagsPerArtist(Artist artist, List<IsDescribed> relations, List<Tag> tags, int lim) throws InterruptedException, ParserConfigurationException, SAXException, UnsupportedEncodingException {
	 String codeArtist=(URLEncoder.encode(artist.getMbid(), "UTF-8"));
	 String tempurl=rootURL+"?method=artist.gettoptags&mbid="+codeArtist+"&api_key="+ApiKey;
	 int tagFounded=0;
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
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("tag");
			//System.out.println(nList.getLength());
			//String classes[]=new String[nList.getLength()];
			for(int i=0;i<nList.getLength();i++){			
				//System.out.println( ((Element) nList.item(i)).getAttribute("id"));
				//System.out.println(  nList.item(i).g );
				//System.out.println(  );
				NodeList intra=nList.item(i).getChildNodes();
				Tag toAdd=new Tag();
				IsDescribed<Artist> relation=new IsDescribed<Artist>();
				for(int l=0; l<intra.getLength()&&lim>tagFounded;l++) {
					
					
					if(intra.item(l).getNodeName().equals("name")) {
						// THIS IS THE NAME OF THE SONGS OUR USERS LIKE
						//System.out.println("porco"+intra.item(l).getTextContent() ); 
						//toAdd.setName(intra.item(l).getTextContent());
						System.out.println("taaaaag: "+intra.item(l).getTextContent());
						toAdd.setName(intra.item(l).getTextContent());
						if(!tags.contains(toAdd)) {
							tags.add(toAdd);
						}
						relation.setTag(intra.item(l).getTextContent() );
						relation.setLabel(artist);
						tagFounded++;
					}
				}				
			}
			
			TimeUnit.MILLISECONDS.sleep(111);
			in.close();
		    	     
		     
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 
 private static void searchUsers(String id, int lim, ArrayList<String> idUsers, List<User> users) throws ParserConfigurationException, SAXException, InterruptedException {
	 String tempurl=rootURL+"?method=user.getfriends&user="+id+"&limit="+lim+"&api_key="+ApiKey;
	 if(lim>0) {
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
			
			//ArrayList<String> idUsers=new ArrayList<String>();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			LastFM obj=new LastFM();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());	
			NodeList nList = doc.getElementsByTagName("id");
			NodeList name = doc.getElementsByTagName("realname");
			NodeList gender = doc.getElementsByTagName("gender");
			NodeList age = doc.getElementsByTagName("age");
			NodeList country = doc.getElementsByTagName("country");
			System.out.println(nList.getLength());
			String classes[]=new String[nList.getLength()];
			for(int i=0;i<nList.getLength();i++){			
				//System.out.println( ((Element) nList.item(i)).getAttribute("id"));
				//System.out.println(  nList.item(i).g );
				
				if(!idUsers.contains(nList.item(i).getTextContent() )) {
					lim--;
					idUsers.add(nList.item(i).getTextContent());
					System.out.println( nList.item(i).getTextContent()+"- "+
						name.item(i).getTextContent()+"- "+
						gender.item(i).getTextContent()+"- "+
						age.item(i).getTextContent()+"- "+
						country.item(i).getTextContent()+"- "+lim);
						User userI=new User();
						userI.setName( name.item(i).getTextContent());
						userI.setId(nList.item(i).getTextContent());
						if(gender.item(i).getTextContent().equals("m") ){
							userI.setSex("male");
						}else if(gender.item(i).getTextContent().equals("f") ){
							userI.setSex("female");
						}
						userI.setAge(age.item(i).getTextContent());
						userI.setCountry(country.item(i).getTextContent() );
						users.add(userI);
				}
				//classes[i]=((Element) nList.item(i)).getAttribute("id");
				
				//ArrayList<String> table=new ArrayList();
				//table.add(((Element) nList.item(i)).getAttribute("id"));
				//System.out.println(table.get(i));
			}
			TimeUnit.MILLISECONDS.sleep(111);
			searchUsers( idUsers.get( idUsers.size()-nList.getLength()+1 ), lim, idUsers, users );
			in.close();
		    	     
		     
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
 }
 
 private static void searchLikes(String user, int lim, List<Song> song, List<Artist> artist, List<Like> likes, String type) throws ParserConfigurationException, SAXException, InterruptedException {
	 String tempurl;
	 if(type.equals("LIKE")) {
		 tempurl=rootURL+"?method=user.getlovedTracks&user="+user+"&limit="+lim+"&api_key="+ApiKey;
	 }else{
		 tempurl=rootURL+"?method=user.getBannedTracks&user="+user+"&limit="+lim+"&api_key="+ApiKey;
		 
	 }
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
			
			//ArrayList<String> idUsers=new ArrayList<String>();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			//LastFM obj=new LastFM();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("track");
			//nList.
			//nList.item(2).
			System.out.println(nList.getLength());
			//String classes[]=new String[nList.getLength()];
			for(int i=0;i<nList.getLength();i++){			
				//System.out.println( ((Element) nList.item(i)).getAttribute("id"));
				//System.out.println(  nList.item(i).g );
				//System.out.println(  );
				NodeList intra=nList.item(i).getChildNodes();
				Song toAdd=new Song();
				Artist a=new Artist();
				for(int l=0; l<intra.getLength();l++) {
						
					if(intra.item(l).getNodeName().equals("name")) {
						// THIS IS THE NAME OF THE SONGS OUR USERS LIKE
						//System.out.println("porco"+intra.item(l).getTextContent() ); 
						toAdd.setName(intra.item(l).getTextContent());
						
					}else if(intra.item(l).getNodeName().equals("artist")) {
						NodeList temp=intra.item(l).getChildNodes();
						for(int k=0;k<temp.getLength();k++) {
							if(temp.item(k).getNodeName().equals("name")){ 
								//System.out.println( "madonna "+temp.item(k).getTextContent() );// THIS IS THE ARTIST OF THE SONG
							//THIS ARE THE ARTISTS OF THE SONGS THAT OUR USER LIKE
								toAdd.setArtist(temp.item(k).getTextContent());
							
								a.setName(temp.item(k).getTextContent());
								//System.out.println("santissima "+a.getName());
							}else if(temp.item(k).getNodeName().equals("mbid")) {
								a.setMbid(temp.item(k).getTextContent());
							}
						}
					}else if(intra.item(l).getNodeName().equals("mbid")) {
						toAdd.setMbid(intra.item(l).getTextContent());
					}
					
					
				}
				boolean found=false;
				for(int j=0; j<song.size();j++){
					if(song.get(j).getName().equals(toAdd.getName())) {
						found=true;
					}
				}
				if (!found) song.add(toAdd);
				/*if(!song.contains(toAdd)){
					song.add(toAdd);					
				}*/
				found=false;
				for(int j=0; j<artist.size();j++){
					if(artist.get(j).getName().equals(a.getName())) {
						found=true;
					}
				}
				if(!found) artist.add(a);
				/*
				if(!artist.contains(a) ){					
					artist.add(a);					
				}*/
				Like newLike= new Like();
				newLike.setIdUser(user);
				newLike.setSong(toAdd);
				newLike.setTypeRelation(type);
				likes.add(newLike);
				
			}
			TimeUnit.MILLISECONDS.sleep(111);
			in.close();
		    	     
		     
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
 }
}
