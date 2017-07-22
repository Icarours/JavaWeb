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
 *  1.4.3 限时抢购
	发送方式	发送URL	备注
	GET	/limitbuy	
	参数名称	描述	样例
	page	第几页	
	pageNum	每页个数	
	
	http://localhost:8080/market/limitbuy?page=1&pageNum=10

 */
public class LimitbuyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		ProductDao dao = new ProductDao();
		List<Product> leftTimeList = dao.getLeftTimeList(page, pageNum);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "limitbuy");
		data.put("productList", leftTimeList);
		data.put("listCount", leftTimeList.size());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "pic", "limitPrice", "price",
				"leftTime");

		CommonUtil.renderJsonWithFilter(response, data, filter);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
