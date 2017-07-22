package bean;

import java.util.List;

public class Brand {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String key;

	private List<BrandInfo> value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<BrandInfo> getValue() {
		return value;
	}

	public void setValue(List<BrandInfo> value) {
		this.value = value;
	}

}
