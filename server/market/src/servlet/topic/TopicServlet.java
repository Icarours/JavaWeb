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
import bean.Topic;
import dao.TopicDao;
/*
 *  1.4.1 促销快报
	发送方式	发送URL	备注
	GET	/topic	
	参数名称	描述	样例
	page	第几页	1 
	pageNum	每页多少个	8 
	http://localhost:8080/market/topic?page=1&pageNum=10
 * 
 */

public class TopicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		TopicDao dao = new TopicDao();
		List<Topic> topicList = dao.getTopicList(page, pageNum);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "topic");
		data.put("topic", topicList);
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
