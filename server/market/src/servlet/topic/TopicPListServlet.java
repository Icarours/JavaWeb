package servlet.topic;

import java.io.IOException;
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

/**
 *  1.4.1.1专题商品列表
	发送方式	发送URL	备注
	GET	/topic/plist	
	参数名称	描述	样例
	page	第几页	1
	pageNum	每页个数	10
	id	专题ID	1234
	orderby	排序	saleDown(销量降序)，priceUp(价格升序)，priceDown(价格降序)，commentDown(评价降序)，shelvesDown(上架降序)。目前只有价格有双向排序，其他都只有降序，其中默认为saleDown

    http://localhost:8080/market/topic/plist?page=1&pageNum=10&id=1&orderby=priceUp
 */
public class TopicPListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int id = Integer.parseInt(request.getParameter("id"));
		String orderby = request.getParameter("orderby");
		ProductDao dao = new ProductDao();
		List<Product> topicPList = dao.getTopicPList(page, pageNum, id, orderby);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "topicProductList");
		data.put("productList", topicPList);
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Product.class,"id","name","pic","marketPrice","price");
		CommonUtil.renderJsonWithFilter(response, data,filter);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
