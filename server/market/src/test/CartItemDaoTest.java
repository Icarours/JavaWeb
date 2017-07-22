package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import bean.Product;
import bean.ProductProperty;
import dao.CartItemDao;
import dao.ProductDao;

public class CartItemDaoTest {
	@Test
	public void testCartItemDao() {
		float totalPrice = 0;
		int totalCount = 0;
		Product prod = null;
		CartItemDao dao = new CartItemDao();
		ProductDao pdao = new ProductDao();
		/*Map<Integer, Integer> prodIdAndNum = dao.getProdIdAndNum(1);
		//定义出商品数量与单个id的集合
		for (Map.Entry<Integer, Integer> pam : prodIdAndNum.entrySet()) {
			int key = pam.getKey();
			int num = pam.getValue();
			System.out.println("商品id:"+key+",商品数量："+num);
			prod = pdao.getProdById(key);
			float price = prod.getPrice();
			//System.out.println(price);
			float prices = price * num;
			totalPrice = totalPrice + prices;
			totalCount = totalCount + num;
		}
		System.out.println(totalPrice);
		System.out.println(totalCount);
		List<ProductProperty> pp = dao.getAllProdProp(1,2);
		System.out.println(pp);*/
		
		//TODO 更新对应商品的数量
		//dao.updateCartList(pid, prod_num);
	}
	
	
	
}
