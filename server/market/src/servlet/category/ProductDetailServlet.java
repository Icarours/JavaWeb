package servlet.category;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.Product;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.ProductDao;
/*
 *  1.5.3 商品详情
	发送方式	发送URL	备注
	GET	/product	
	参数名称	描述	样例
	pId	商品ID	1
	http://localhost:8080/market/product?pId=1
 */

public class ProductDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("pId"));
		ProductDao dao = new ProductDao();
		Product product = dao.getProductDetail(pId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "product");
		data.put("product", product);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "marketPrice", "price",
				"limitPrice", "leftTime", "score", "available", "buyLimit",
				"commentCount", "inventoryArea", "productProperty", "pics",
				"bigPic");
		CommonUtil.renderJsonWithFilter(response, data, filter);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
