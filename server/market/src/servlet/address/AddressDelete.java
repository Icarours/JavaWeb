package servlet.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import dao.AddressDao;

/*
 * addressdelete
 */

public class AddressDelete extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		/*String userid = request.getHeader("userid");
		
		if (userid == null) {

			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);
		}

		ServletContext context = getServletContext();

		Object user = context.getAttribute(userid);

		if (!(user instanceof User)) {

			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_UNLOGIN);
			data.put("error", "没有登录或则需要重新登录");
			CommonUtil.renderJson(response, data);

		}*/
		
		String id = request.getParameter("id");
		AddressDao dao = new AddressDao();
		dao.deleAddress(id);
		data.put("response", "addressDelete");
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
