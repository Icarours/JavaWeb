package servlet.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Version;
import dao.VersionDao;
import util.CommonUtil;

/*
 * 1.8.6 版本检测
         发送方式	发送URL	备注
   GET	/version	
   http://localhost:8080/market/version
 */

public class CheckVersionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		VersionDao dao = new VersionDao();
		Version info = dao.getVersionInfo();

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("response", "version");
		if (info != null) {
			data.put("version", info);
		}
		
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
