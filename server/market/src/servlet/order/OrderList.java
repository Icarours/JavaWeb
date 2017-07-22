package servlet.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.CommonUtil;
import bean.OrderInfo;
import bean.User;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import dao.OrderDao;
/*
 *  1.9.1 订单列表
	发送方式	发送URL	备注
	GET	/orderlist	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
	参数名称	描述	样例
	type	1/2/3	1=>近一个月订单(改为10分钟)  2=>一个月前订单(改为10分钟)  3=>已取消订单
    RequestParams params = new RequestParams();
    params.addHeader("userid", "20428");
	params.addBodyParameter("type", "2");
	params.addBodyParameter("page", "0");
	params.addBodyParameter("pageNum", "10");
	mHttpUtils.send(HttpMethod.POST, HOST + "/orderlist", params,
				new RequestCallBack<String>() {}
 */


public class OrderList extends HttpServlet {

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
			
			int type = Integer.parseInt(request.getParameter("type"));
			int page = Integer.parseInt(request.getParameter("page"));
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			List<OrderInfo> list = new OrderDao().getOrderList(userid, type,
					page, pageNum);
			data.put("response", "orderList");
			data.put("orderList", list);
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
					OrderInfo.class, "orderId", "status", "time", "price",
					"flag");
			CommonUtil.renderJsonWithFilter(response, data, filter);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
