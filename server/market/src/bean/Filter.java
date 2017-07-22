package bean;

import java.util.List;

public class Filter {
	private String key;
	private List<Value> valueList;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Value> getValueList() {
		return valueList;
	}
	public void setValueList(List<Value> valueList) {
		this.valueList = valueList;
	}
	
}
