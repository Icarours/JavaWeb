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

/**
 * 1.8.4 注销
         发送方式	发送URL	备注
   POST	/logout	需要先登录获取userid，在将userid添加到请求头
   RequestParams params = new RequestParams();
   params.addHeader("userid", "20428");
   mHttpUtils.send(HttpMethod.POST, HOST + "/logout", params,
				new RequestCallBack<String>() {}
 */
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();
		String userid = request.getHeader("userid");
		if (userid == null) {
			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);
		}
		ServletContext context = getServletContext();
		context.removeAttribute(userid);
		data.put("response", "logout");
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
