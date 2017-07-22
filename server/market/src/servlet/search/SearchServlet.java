package servlet.search;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
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
 *  1.3.2 搜索商品列表
	发送方式	发送URL	备注
	GET	/search	
	参数名称	描述	样例
	keyword	搜索关键字	keyword=奶粉
	page	第几页	1
	pageNum	每页多少个	10
	orderby	排序	saleDown(销量降序)，priceUp(价格升序)，priceDown(价格降序)，commentDown(评价降序)，shelvesDown(上架降序)。
	目前只有价格有双向排序，其他都只有降序，其中默认为saleDown
 * 
 * http://localhost:8080/market/search?page=0&pageNum=10&orderby=saleDown&keyword=奶粉
 */
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		//String keyword = URLDecoder.decode(request.getParameter("keyword"),"UTF-8");
		
		String keyword = new String(request.getParameter("keyword").getBytes("iso-8859-1"), "UTF-8");
		
		String orderby = request.getParameter("orderby");
		
		if (orderby==null || "".equals(orderby)) {
			orderby = "saleDown";
		}

		ProductDao dao = new ProductDao();
		List<Product> products = dao.getSearchProducts(keyword, page, pageNum, orderby);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "search");
		data.put("productList", products);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Product.class, "id", "name", "pic", "marketPrice", "price");
		CommonUtil.renderJsonWithFilter(response, data, filter);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
