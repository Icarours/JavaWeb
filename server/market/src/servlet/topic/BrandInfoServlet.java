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
import bean.Brand;
import dao.BrandInfoDao;

/**
 *  1.4.2推荐品牌
            发送方式	发送URL	备注
    GET	/brand	
	http://localhost:8080/market/brand	
 */
public class BrandInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BrandInfoDao dao = new BrandInfoDao();
		List<Brand> list = dao.getBrands();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "brand");
		data.put("brand", list);
		CommonUtil.renderJson(response, data);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
