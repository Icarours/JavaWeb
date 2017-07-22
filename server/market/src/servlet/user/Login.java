package servlet.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.User;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import dao.UserDao;

/*
 *  1.8.1 登录
	发送方式	发送URL	备注
	POST	/login	
	参数名称	描述	样例
	username	用户名	xiaowen@itcast.cn
	password	密码	123456
    http://192.168.138.1:8080/market/login?username=xiaowen@itcast.cn&password=123456
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> data = new HashMap<String, Object>();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || username == "" || password == null
				|| password == "") {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_REQUEST_PARAMETER_ERROR);
			data.put("error", "请求参数错误或缺失");
			CommonUtil.renderJson(response, data);
		}

		UserDao dao = new UserDao();

		User user = dao.login(username, password);

		if (user == null) {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_NOT_EXIST_USER);
			data.put("error", "用户名不存在或密码错误");
		} else {
			data.put("response", "login");
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", user.getUserid());
			data.put("userInfo", map);
			ServletContext context = getServletContext();
			context.setAttribute(user.getUserid(), user);
		}

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				User.class, "userid");

		CommonUtil.renderJsonWithFilter(response, data, filter);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
