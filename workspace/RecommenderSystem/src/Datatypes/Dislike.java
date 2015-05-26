package Datatypes;

public class Dislike {
	
		private String idUser;
		//private String typeRelation;
		private String song;
		public String getIdUser() {
			return idUser;
		}
		public void setIdUser(String idUser) {
			this.idUser = idUser;
		}
	
		public String getSong() {
			return song;
		}
		public void setSong(String song) {
			this.song = song;
		}
		

		public String toString(){		
			return idUser+"//"+song;
		}
}


