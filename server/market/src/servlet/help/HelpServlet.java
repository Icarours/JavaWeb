package servlet.help;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.Help;
import dao.HelpDao;

public class HelpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "help");

		HelpDao dao = new HelpDao();
		List<Help> helpList = dao.getHelpList();
		data.put("helpList", helpList);

		// 将map转换成Json字符串信息

		// System.out.println(JSONObject.fromObject(data).toString());
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
