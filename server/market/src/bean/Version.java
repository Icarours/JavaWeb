package bean;

public class Version {

	private float version;
	private String url;

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Version [version=" + version + ", url=" + url + "]";
	}

}
