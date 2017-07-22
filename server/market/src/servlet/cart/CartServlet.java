package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.CartItem;
import bean.Product;
import bean.ProductProperty;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.ProductDao;
import dao.ProductPropertyDao;
/*
 *  1.6.1 进入购物车
	发送方式	发送URL	备注
	Post	/cart	
	参数名称	描述	样例
	sku	商品ID:数量:属性ID|商品ID:数量:属性ID	1:3:1,2,3,4|2:2:2,3

 *  http://localhost:8080/market/cart?sku=1:3:1,2,3,4|2:2:2,3 
 */
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		ProductDao productDao = new ProductDao();
		ProductPropertyDao propertyDao = new ProductPropertyDao();
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		String sku = request.getParameter("sku");
		String[] results = sku.split("\\|");

		// 商品数量总计
		int totalCount = 0;
		// 商品金额总计
		float totalPrice = 0f;

		// /商品积分总计
		int totalPoint = 100;

		for (String result : results) {
			System.out.println("result:" + result);

			CartItem item = new CartItem();
			String[] productsStr = result.split(":");
			String pid = productsStr[0];

			int pNum = Integer.parseInt(productsStr[1]);
			Product product = productDao.getProduct(Integer.parseInt(pid));
			if (productsStr.length > 2) {
				String pPropertyId = productsStr[2];
				List<ProductProperty> productProperty = propertyDao
						.getProductProperty(pPropertyId);
				product.setProductProperty(productProperty);
			}

			item.setProduct(product);
			item.setProdNum(pNum);

			// 结算商品金额总计
			totalPrice += product.getPrice() * pNum;
			// 计算商品数量总计
			totalCount += pNum;

			cartItemList.add(item);
		}

		data.put("response", "cart");
		data.put("cart", cartItemList);

		List<String> prom = Arrays.asList("促销信息一", "促销信息二");
		data.put("prom", prom);

		data.put("totalCount", totalCount);
		data.put("totalPrice", totalPrice);
		data.put("totalPoint", totalPoint);

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "pic", "marketprice", "price","number","buyLimit",
				"productProperty");
		CommonUtil.renderJsonWithFilter(response, data, filter);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
