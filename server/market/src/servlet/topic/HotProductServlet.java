package servlet.topic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import util.CommonUtil;
import bean.Product;
import dao.ProductDao;

/**
		1.4.5 热门单品
		发送方式	发送URL	备注
		GET	/hotproduct	
		参数名称	描述	样例
		page	第几页	
		pageNum	每页个数	
		orderby	排序	saleDown(销量降序)，priceUp(价格升序)，priceDown(价格降序)，commentDown(评价降序)，shelvesDown(上架降序)。目前只有价格有双向排序，其他都只有降序，其中默认为saleDown
		http://localhost:8080/market/hotproduct?page=1&pageNum=10&orderby=saleDown
 */
public class HotProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String orderby = request.getParameter("orderby");
		ProductDao dao = new ProductDao();
		List<Product> hotList = dao.getHotProductList(page, pageNum, orderby);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "hotProduct");
		data.put("productList", hotList);
		data.put("listCount", 15);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Product.class, "id", "name", "pic", "marketPrice", "price");

		CommonUtil.renderJsonWithFilter(response, data, filter);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
