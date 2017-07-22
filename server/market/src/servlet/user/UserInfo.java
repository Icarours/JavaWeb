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

import dao.FavoritesDao;
import dao.OrderDao;

/*
 * GET	/userinfo	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
 * RequestParams params = new RequestParams();
 params.addHeader("userid", "20428");
 mHttpUtils.send(HttpMethod.POST, HOST + "/userinfo", params,
 new RequestCallBack<String>() {});
 */

public class UserInfo extends HttpServlet {

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
		Object user = context.getAttribute(userid);

		if (!(user instanceof User)) {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_UNLOGIN);
			data.put("error", "没有登录或则需要重新登录");
			CommonUtil.renderJson(response, data);
		} else {
			User info = (User) user;
			FavoritesDao favoritesDao = new FavoritesDao();
			int favoritesCount = favoritesDao.getFavoritesCount(userid);
			info.setFavoritesCount(favoritesCount);
			OrderDao orderDao = new OrderDao();
			int orderCount = orderDao.getOrderCount(userid);
			info.setOrderCount(orderCount);
			data.put("response", "userInfo");
			data.put("userInfo", info);
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
					User.class, "bonus", "level", "userid", "orderCount",
					"favoritesCount");
			CommonUtil.renderJsonWithFilter(response, data, filter);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
