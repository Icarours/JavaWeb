package servlet.search;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchDao;
import util.CommonUtil;
/*
 * 1.3.1 搜索推荐
        发送方式	发送URL	备注
   GET	/search/recommend	

 * http://localhost:8080/market/search/recommend
 * 
 */

public class RecommendServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchDao dao = new SearchDao();
		List<Object> searchKeyWords = dao.getRecommends();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "searchrecommend");
		data.put("searchKeywords", searchKeyWords);
		CommonUtil.renderJson(response, data);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
