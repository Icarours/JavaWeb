package servlet.user;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
 * POST	/register	//用户注册直接进入登录状态，返回的
   参数名称	描述	样例
 username	用户名	xiaowen@itcast.cn
 password	密码	123456
 http://192.168.138.1:8080/market/register?username=xiaowen@itcast.cn&password=123456 
 */

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();
		request.setCharacterEncoding("utf-8");
		// Map<String, String> map = request.getParameterMap();

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

		User bean = new User();
		UserDao dao = new UserDao();
		try {
			// BeanUtils.populate(bean, map);
			bean.setUsername(username);
			bean.setPassword(password);

			Date date = new Date();
			int hours = date.getHours();
			int min = date.getMinutes();
			int sec = date.getSeconds();
			String userId = String.valueOf((hours + "") + (min + "")
					+ (sec + ""));

			bean.setUserid(userId);

			Random random = new Random();
			int nextInt = random.nextInt(3);
			String[] persons = new String[] { "普通会员", "白金会员", "铂金会员" };
			bean.setLevel(persons[nextInt]);
			int bonus = 10 + random.nextInt(300);
			bean.setBonus(bonus);
			boolean have = dao.register(bean);

			if (have) {
				data.put("response", "error");
				data.put(CommonUtil.ERROR_CODE,
						CommonUtil.ERROR_CODE_USERNAME_BEEN_REGISTERED);
				data.put("error", "该用户名已经被注册过了");
			} else {
				data.put("response", "register");
				data.put("userInfo", bean);
				// 注册成功进入登录状态
				ServletContext context = getServletContext();
				context.setAttribute(bean.getUserid(), bean);
			}

			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
					User.class, "userid");
			CommonUtil.renderJsonWithFilter(response, data, filter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
