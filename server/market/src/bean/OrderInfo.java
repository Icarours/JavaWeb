package bean;


public class OrderInfo {

	private String orderId;
	private String status;
	private String time;
	private int flag;
	private double price;
	private String userid;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", status=" + status
				+ ", time=" + time + ", flag=" + flag + ", price=" + price
				+ ", userid=" + userid + "]";
	}

}
