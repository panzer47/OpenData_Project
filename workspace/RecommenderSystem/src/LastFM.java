import java.util.ArrayList;


public class LastFM {
	private String url;
	private String api_key;
	private String userLimit;
	ArrayList<ArrayList<String>> facts;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserLimit() {
		return userLimit;
	}
	public void setUserLimit(String string) {
		this.userLimit = string;
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	
	
}
