package servlet.category;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.Category;
import dao.ProductDao;

/*
 * 1.5.1 分类请求
         发送方式	发送URL	备注
   GET	/category	
   http://localhost:8080/market/category	
 */

public class CategoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ProductDao dao = new ProductDao();
		List<Category> categoryList = dao.getCategoryList();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "category");
		data.put("category", categoryList);
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
