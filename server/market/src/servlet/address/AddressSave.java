package servlet.address;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

/*
 *  2.0.3 地址保存
	 发送方式	发送URL	备注
	 POST	/addresssave	
	 参数名称	描述	样例
	 id	修改地址簿ID	1001，如为新增地址，无此参数
	 name	收货人姓名	肖文
	 phoneNumber	手机号码	15801477821
	 fixedtel	固定电话	010-88496666
	 areaId	北京市海淀区	010305
	 areaDetail	详情地址	闵庄路3号红孩子
	 zipCode	邮编	100195
 
        RequestParams params = new RequestParams();
		params.addHeader("userid", "20428");
		params.addBodyParameter("name", "肖文");
		params.addBodyParameter("phoneNumber", "15801477821");
		params.addBodyParameter("province", "湖北");
		params.addBodyParameter("city", "武汉");
		params.addBodyParameter("addressArea", "洪山区");
		params.addBodyParameter("addressDetail", "街道口");
		params.addBodyParameter("zipCode", "430070");
		params.addBodyParameter("isDefault", "1");
		mHttpUtils.send(HttpMethod.POST, HOST + "/addresssave", params,
				new RequestCallBack<String>() {}

 */
public class AddressSave extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> data = new HashMap<String, Object>();

		String userId = request.getHeader("userid");

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

		} else {
			AddressDao dao = new AddressDao();

			String parameterId = request.getParameter("id");
			int id = (parameterId == null || "".equals(parameterId)) ? 0
					: Integer.parseInt(parameterId);

			Address address = new Address();
			address.setId(id);
			
			String parameterName = request.getParameter("name");
			parameterName = new String(parameterName.getBytes("iso-8859-1"), "utf-8");
			//parameterName = URLDecoder.decode(parameterName, "utf-8");
			address.setName(parameterName);
			
			address.setPhoneNumber(request.getParameter("phoneNumber"));
			
			String parameterProvince = request.getParameter("province");
			parameterProvince = new String(parameterProvince.getBytes("iso-8859-1"), "utf-8");
			//parameterProvince = URLDecoder.decode(parameterProvince, "utf-8");
			address.setProvince(parameterProvince);
			
			String parameterCity = request.getParameter("city");
			parameterCity = new String(parameterCity.getBytes("iso-8859-1"), "utf-8");
			//parameterCity = URLDecoder.decode(parameterCity, "utf-8");
			address.setCity(parameterCity);
			
			String parameterAddressArea = request.getParameter("addressArea");
			parameterAddressArea = new String(parameterAddressArea.getBytes("iso-8859-1"), "utf-8");
			//parameterAddressArea = URLDecoder.decode(parameterAddressArea, "utf-8");
			address.setAddressArea(parameterAddressArea);
			
			String parameterAddressDetail = request.getParameter("addressDetail");
			parameterAddressDetail = new String(parameterAddressDetail.getBytes("iso-8859-1"), "utf-8");
			//parameterAddressDetail = URLDecoder.decode(parameterAddressDetail, "utf-8");
			address.setAddressDetail(parameterAddressDetail);
			
			address.setZipCode(request.getParameter("zipCode"));
			address.setIsDefault(Integer.parseInt(request
					.getParameter("isDefault")));
			dao.saveAddress(address, userId);

			List<Address> addressList = dao.getAddressList(userId);
			data.put("response", "addresssave");
			data.put("addressList", addressList);
			CommonUtil.renderJson(response, data);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
