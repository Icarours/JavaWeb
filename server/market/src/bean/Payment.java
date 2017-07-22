package bean;

public class Payment {
	private int type;
	private String des;
	public Payment() {
	}
	public Payment(int type, String des) {
		super();
		this.type = type;
		this.des = des;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
