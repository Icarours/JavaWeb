package servlet.home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.HomeTopic;
import dao.HomeTopicDao;
/*
 * 1.2 首页
         发送方式	发送URL	备注
   GET	/home	
	
 * http://localhost:8080/market/home
 * 
 */

public class HomeTopicServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HomeTopicDao dao = new HomeTopicDao();
		List<HomeTopic> list = dao.getHomeTopics();
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("response", "home");
		data.put("homeTopic", list);
		CommonUtil.renderJson(response, data);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
