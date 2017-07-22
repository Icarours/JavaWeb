package servlet.address;

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
import bean.Address;
import bean.User;
import dao.AddressDao;

/**
 * 2.0.1 地址列表
         发送方式	发送URL	备注
   GET	/addresslist	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
   RequestParams params = new RequestParams();
   params.addHeader("userid", "20428");
   mHttpUtils.send(HttpMethod.POST, HOST + "/addresslist", params,
				new RequestCallBack<String>() {	}
 *
 */
public class AddressList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);

		} else {
			AddressDao dao = new AddressDao();
			List<Address> list = dao.getAddressList(userid);
			data.put("response", "addressList");
			data.put("addressList", list);
			CommonUtil.renderJson(response, data);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
