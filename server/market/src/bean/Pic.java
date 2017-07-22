package bean;

public class Pic {
	private int id;
	private String url;
	private int type;
	public Pic() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Pic [id=" + id + ", url=" + url + ", type=" + type + "]";
	}
}	
