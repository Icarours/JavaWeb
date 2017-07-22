package servlet.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.Filter;
import bean.Product;
import bean.Value;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.ProductDao;

/*
 *  1.5.2 商品列表
 发送方式	发送URL	备注
 GET	/productlist	
 参数名称	描述	样例
 page	第几页	1
 pageNum	每页个数	10
 cId	分类ID	1010101
 orderby	排序	saleDown(销量降序)，priceUp(价格升序)，priceDown(价格降序)，commentDown(评价降序)，shelvesDown(上架降序)。目前只有价格有双向排序，其他都只有降序，其中默认为saleDown
 filter	筛选	"t1-s2-p1"  (三个条件)

 //不加筛选的请求
 http://localhost:8080/market/productlist?page=0&pageNum=10&orderby=saleDown&cId=22

 //按照颜色进行筛选
 *  http://localhost:8080/market/productlist?page=0&pageNum=10&orderby=saleDown&cId=22&filter=t1
 *  
 *  //按照颜色和品牌进行筛选
 *  //http://localhost:8080/market/productlist?page=0&pageNum=10&orderby=saleDown&cId=22&filter=t1-s1
 *  
 *  //按照颜色、品牌、价格进行筛选
 *  //http://localhost:8080/market/productlist?page=0&pageNum=10&orderby=saleDown&cId=22&filter=t1-s1-p8
 * 
 */

public class ProductListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String orderby = request.getParameter("orderby");
		String cId = request.getParameter("cId");

		String filterStr = request.getParameter("filter");

		ProductDao dao = new ProductDao();
		List<Product> productList = dao.getCategoryProductList(filterStr,
				Integer.parseInt(cId), page, pageNum, orderby);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "categoryProductlist");
		data.put("productList", productList);

		// 筛选
		List<Filter> filterList = new ArrayList<Filter>();

		// 品牌筛选
		Filter filter1 = new Filter();
		filter1.setKey("品牌");
		List<Value> valueList11 = dao.getBrandInfoValues();
		filter1.setValueList(valueList11);

		filterList.add(filter1);

		// 价格筛选
		Filter filter2 = new Filter();
		filter2.setKey("价格");
		List<Value> valueList21 = dao.getPriceValues();
		filter2.setValueList(valueList21);

		filterList.add(filter2);

		// 颜色筛选
		Filter filter3 = new Filter();
		filter3.setKey("颜色");
		List<Value> valueList3 = dao.getColorValues();
		filter3.setValueList(valueList3);

		filterList.add(filter3);

		data.put("listFilter", filterList);

		data.put("listCount", productList == null ? 0 : productList.size());

		SimplePropertyPreFilter preFilter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "pic", "marketPrice", "price",
				"commentCount");
		CommonUtil.renderJsonWithFilter(response, data, preFilter);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
