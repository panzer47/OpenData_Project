import java.util.ArrayList;


public class MadonnaPiangeSborraDalCUlo {
 public static void main(String[] args) {
	 Song a=new Song();
	 a.setName("piscio");
	 ArrayList<Song> song= new ArrayList<Song>();
	 song.add(a);
	 Song toAdd=new Song();
	 toAdd.setName("piscio");
	 //if (!b.contains(c) ) System.out.println("a");
	 
	 boolean found=false;
		for(int j=0; j<song.size();j++){
			if(song.get(j).getName().equals(toAdd.getName())) {
				found=true;
			}
		}
		if (found) System.out.println("gesusborradura");//song.add(toAdd);
	 
 }
}
