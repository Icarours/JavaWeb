package servlet.order;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.CommonUtil;
import bean.User;
import dao.OrderDao;

/*  1.9.3 取消订单
	发送方式	发送URL	备注
	POST	/ordercancel	
	参数名称	描述	样例
	orderId	订单ID	873768
 *  RequestParams params = new RequestParams();
	params.addHeader("userid", "20428");
	params.addBodyParameter("orderId", "032096");
	mHttpUtils.send(HttpMethod.POST, HOST + "/ordercancel", params,
				new RequestCallBack<String>() {}
 */

public class OrderCancel extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HashMap<String, Object> data = new HashMap<String, Object>();

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
		}

		String orderId = request.getParameter("orderId");

		OrderDao dao = new OrderDao();
		int update = dao.cancelOrder(orderId);

		if (update > 0) {
			data.put("response", "orderCancel");
		} else {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_FAILER_CANCLE_ORDER);
			data.put("error", "取消订单失败");
		}
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
