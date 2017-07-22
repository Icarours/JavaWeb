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
import dao.FavoritesDao;
/*
 *  1.5.6 商品收藏
	发送方式	发送URL	备注
	ET	/product/favorites	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
	参数名称	描述	样例
	Id	商品ID	1
	RequestParams params = new RequestParams();
    params.addHeader("userid", "20428");
 *  params.addBodyParameter("pId", "1");
	mHttpUtils.send(HttpMethod.POST, HOST + "/product/favorites", params,
				new RequestCallBack<String>() {}
 */

public class AddFavorites extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		String userid = request.getHeader("userid");

		if (userid == null) {
			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);
		}

		ServletContext context = getServletContext();

		Object user = context.getAttribute(userid);

		if (!(user instanceof User)) {
			data.put("response", "error");
			data.put("error", "还未登陆");
			data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_UNLOGIN);
			CommonUtil.renderJson(response, data);
		} else {
			String pid = request.getParameter("pId");
			FavoritesDao favoritesDao = new FavoritesDao();
			int result = favoritesDao.addItem(userid + "", pid);
			if (result != 1) {
				data.put("response", "error");
				String error = "";
				if (result == -1) {
					error = "当前商品已经添加过收藏";
					data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_HAD_ADD_FAVORITES);
				} else if (result == 0) {
					error = "添加收藏夹失败";
					data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_FAILER_ADD_FAVORITES);
				}
				data.put("error", error);
			} else {

				data.put("response", "addfavorites");
			}
		}
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
