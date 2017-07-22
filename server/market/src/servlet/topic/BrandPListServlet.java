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

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.ProductDao;
import bean.Product;

public class BrandPListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int id = Integer.parseInt(request.getParameter("id"));
		String orderby = request.getParameter("orderby");
		ProductDao dao = new ProductDao();
		List<Product> brandPList = dao
				.getBrandPList(page, pageNum, id, orderby);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "brandProductList");
		data.put("productList", brandPList);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "pic", "marketPrice", "price");
		CommonUtil.renderJsonWithFilter(response, data, filter);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
