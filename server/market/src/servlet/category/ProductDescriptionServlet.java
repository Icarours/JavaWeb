package servlet.category;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import dao.ProductDescriptionDao;

/**
 * Servlet implementation class ProductDescriptionServlet
 */
public class ProductDescriptionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("pId"));
		ProductDescriptionDao dao = new ProductDescriptionDao();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "productDescription");
		data.put("productdesc", dao.getProductDescription(pId));
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
