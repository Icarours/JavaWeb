package bean;


public class CartItem {
	private int prodNum;
	private Product product;
	
	
	
	public CartItem() {}
	
	public int getProdNum() {
		return prodNum;
	}

	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [prodNum=" + prodNum + ", product=" + product + "]";
	}

	
	
	
	
}
