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
import bean.HelpDetail;
import dao.HelpDao;

public class HelpDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "helpDetail");

		HelpDao dao = new HelpDao();
		List<HelpDetail> helpList = dao.getHelpDetails(id);
		data.put("helpDetailList", helpList);

		// 将map转换成Json字符串信息

		// System.out.println(JSONObject.fromObject(data).toString());
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
