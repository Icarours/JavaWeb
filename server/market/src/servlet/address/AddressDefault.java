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
 *  2.0.4 设置默认地址
	发送方式	发送URL	备注
	GET	/addressdefault	
	参数名称	描述	样例
	id	修改地址簿ID	146
 */
public class AddressDefault extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> data = new HashMap<String, Object>();

		/*String userId = request.getHeader("userid");

		if (userId == null) {

			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);
		}

		ServletContext context = getServletContext();
		Object user = context.getAttribute(userId);
		
		if (!(user instanceof User)) {

			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_UNLOGIN);
			data.put("error", "没有登录或则需要重新登录");
			CommonUtil.renderJson(response, data);
		}*/
		
		String id = request.getParameter("id");
		AddressDao dao = new AddressDao();
		dao.setDefault(id);
		//id为需要设置默认地址的id
		data.put("response", "addressDefault");
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
