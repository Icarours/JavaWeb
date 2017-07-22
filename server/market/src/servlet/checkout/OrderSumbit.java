package servlet.checkout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.OrderInfo;
import bean.OrderProDetail;
import bean.Product;
import bean.User;
import dao.OrderDao;
import dao.ProductDao;

/*
 *  1.7.2 提交结算
 发送方式	发送URL	备注
 POST	/ordersumbit	需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录
 参数名称	描述	样例
 sku	商品ID:数量:属性ID|商品ID:数量:属性ID	1:3:1,2,3,4|2:2:2,3
 addressId	地址簿ID	139
 paymentType	支付方式	1
 deliveryType	送货时间	1
 invoiceType	发票类型	1=>个人 2=>单位
 invoiceTitle	发票抬头	传智慧播客教育科技有限公司
 invoiceContent	发票内容	1

 *  需要请求头
 RequestParams params = new RequestParams();
 params.addHeader("userid", "20428");
 params.addBodyParameter("sku", "1:3:1,2,3,4|2:2:2,3");
 params.addBodyParameter("addressId", "139");
 params.addBodyParameter("paymentType", "1");
 params.addBodyParameter("deliveryType", "1");
 params.addBodyParameter("invoiceType", "2");
 params.addBodyParameter("invoiceTitle", "传智慧播客教育科技有限公司");
 params.addBodyParameter("invoiceContent", "传智慧播客教育科技有限公司");
 mHttpUtils.send(HttpMethod.POST, HOST + "/ordersumbit", params,
 new RequestCallBack<String>() {});
 * 
 */

public class OrderSumbit extends HttpServlet {

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
			String sku = request.getParameter("sku");
			String[] results = sku.split("\\|");
			double totalPrice = 0;
			String orderIdStr = System.currentTimeMillis() + "";
			String order_id = orderIdStr.substring(7, orderIdStr.length());
			OrderDao orderDao = new OrderDao();
			ProductDao productDao = new ProductDao();
			for (String result : results) {

				String[] productsStr = result.split(":");
				OrderProDetail orderProDetail = new OrderProDetail();

				String pid = productsStr[0];
				orderProDetail.setPid(Integer.parseInt(pid));

				int pNum = Integer.parseInt(productsStr[1]);
				orderProDetail.setpNum(pNum);

				Product product = productDao.getProduct(Integer.parseInt(pid));
				totalPrice += product.getPrice();

				if (productsStr.length > 2) {
					String pPropertyId = productsStr[2];
					String[] propertyStrs = pPropertyId.split(",");

					orderProDetail.setColor(Integer.parseInt(propertyStrs[0]));
					if (propertyStrs.length > 1) {
						orderProDetail.setSize(Integer
								.parseInt(propertyStrs[1]));
					}

				}
				orderProDetail.setOrder_id(order_id);
				orderProDetail.setPaymentType(Integer.parseInt(request
						.getParameter("paymentType")));
				orderProDetail.setInvoiceType(Integer.parseInt(request
						.getParameter("invoiceType")));

				String invoiceTitle = new String(request.getParameter(
						"invoiceTitle").getBytes("iso-8859-1"), "utf-8");
				// String invoiceTitle = URLDecoder.decode(
				// request.getParameter("invoiceTitle"), "UTF-8");
				orderProDetail.setInvoiceTitle(invoiceTitle);

				String invoiceContent = new String(request.getParameter(
						"invoiceContent").getBytes("iso-8859-1"), "utf-8");
				// String invoiceContent = URLDecoder.decode(
				// request.getParameter("invoiceContent"), "UTF-8");
				System.out.println(invoiceTitle + "," + invoiceContent);
				orderProDetail.setInvoiceContent(invoiceContent);

				orderProDetail.setDeliveryType(Integer.parseInt(request
						.getParameter("deliveryType")));
				orderProDetail.setAddressId(Integer.parseInt(request
						.getParameter("addressId")));
				orderDao.submitOrder(orderProDetail);
			}

			data.put("response", "orderSubmit");
			OrderInfo info = new OrderInfo();
			info.setOrderId(order_id);
			info.setPrice(totalPrice);
			info.setUserid(userid);
			info.setStatus("未处理");
			info.setTime(System.currentTimeMillis() + "");
			Random random = new Random();
			int flag = random.nextInt(3) + 1;
			info.setFlag(flag);
			orderDao.addOrder(info);

			Map<String, Object> orderInfo = new HashMap<String, Object>();
			orderInfo.put("orderId", info.getOrderId());
			orderInfo.put("price", info.getPrice());
			orderInfo.put("paymentType",
					Integer.parseInt(request.getParameter("paymentType")));
			data.put("orderInfo", orderInfo);

			CommonUtil.renderJson(response, data);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
