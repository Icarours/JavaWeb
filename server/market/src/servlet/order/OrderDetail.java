package servlet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import bean.CartItem;
import bean.CheckoutAddup;
import bean.OrderInfo;
import bean.OrderProDetail;
import bean.Product;
import bean.ProductProperty;
import bean.User;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.AddressDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.ProductPropertyDao;

public class OrderDetail extends HttpServlet {
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
			String orderId = request.getParameter("orderId");

			OrderDao orderDao = new OrderDao();
			AddressDao addressDao = new AddressDao();

			List<OrderProDetail> orderDetailList = orderDao
					.getOrderDetailByOrderId(orderId);
			
			if (orderDetailList == null || orderDetailList.size() < 1) {
				data.put("response", "error");
				data.put(CommonUtil.ERROR_CODE, CommonUtil.ERROR_CODE_NO_ORDER_DETAIL);
				data.put("error", "没有该订单详情");
				CommonUtil.renderJson(response, data);
			}
			
			data.put("response", "orderDetail");
			//1.订单信息
			OrderInfo info = orderDao.getOrder(orderId);

			Map<String, Object> orderInfo = new HashMap<String, Object>();
			orderInfo.put("orderId", info.getOrderId());
			orderInfo.put("status", info.getStatus());
			orderInfo.put("time", info.getTime());
			orderInfo.put("flag", info.getFlag());
			data.put("orderInfo", orderInfo);

			
			int addressId = orderDetailList.get(0).getAddressId();
			System.out.println("addressId:" + addressId);
			
			//2.地址信息
			Address address = addressDao.getAddressInfoById(addressId);

			Map<String, Object> addressInfo = new HashMap<String, Object>();
			addressInfo.put("id", addressId);
			addressInfo.put("name", address.getName());
			addressInfo.put("addressArea", address.getAddressArea());
			addressInfo.put("addressDetail", address.getAddressDetail());
			data.put("addressInfo", addressInfo);

			//3.支付方式
			Map<String, Integer> paymentInfo = new HashMap<String, Integer>();
			paymentInfo.put("type", orderDetailList.get(0).getPaymentType());
			data.put("paymentInfo", paymentInfo);
			//4.发票信息
			Map<String, String> invoiceInfo = new HashMap<String, String>();
			invoiceInfo.put("invoiceTitle", orderDetailList.get(0)
					.getInvoiceTitle());
			invoiceInfo.put("invoiceContent", orderDetailList.get(0)
					.getInvoiceContent());
			data.put("invoiceInfo", invoiceInfo);

			//5.送货时间
			Map<String, String> deliveryInfo = new HashMap<String, String>();
			deliveryInfo.put("type", orderDetailList.get(0).getDeliveryType()+"");
			data.put("deliveryInfo", deliveryInfo);
			
			
			List<CartItem> cartItemList = new ArrayList<CartItem>();
			ProductDao productDao = new ProductDao();
			ProductPropertyDao propertyDao = new ProductPropertyDao();
			int totalCount = 0;
			int totalPrice = 0;
			for (OrderProDetail orderProDetail : orderDetailList) {
				CartItem item = new CartItem();
				int pid = orderProDetail.getPid();
				int pNum = orderProDetail.getpNum();
				Product product = productDao.getProduct(pid);
				List<ProductProperty> productProperties = new ArrayList<ProductProperty>();
				if (orderProDetail.getColor() != 0) {
					ProductProperty property = propertyDao
							.getProperty(orderProDetail.getColor());
					productProperties.add(property);
				}
				if (orderProDetail.getSize() != 0) {
					ProductProperty property = propertyDao
							.getProperty(orderProDetail.getSize());
					property.setId(1);
					productProperties.add(property);
				}
				product.setProductProperty(productProperties);

				item.setProduct(product);
				item.setProdNum(pNum);
				totalCount += pNum;
				totalPrice += pNum * product.getPrice();

				cartItemList.add(item);
			}
			System.out.println(totalCount + "," + totalPrice);
			data.put("productList", cartItemList);
			
			List<String> prom = Arrays.asList("促销信息一", "促销信息二");
			data.put("prom", prom);

			// 订单总价格
			CheckoutAddup checkoutAddup = new CheckoutAddup();
			checkoutAddup.setFreight(10);
			checkoutAddup.setTotalCount(totalCount);
			checkoutAddup.setTotalPrice(totalPrice);
			checkoutAddup.setTotalPoint(30);
			data.put("checkoutAddup", checkoutAddup);

			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
					Product.class, "id", "name", "pic", "marketprice", "price",
					"productProperty");
			CommonUtil.renderJsonWithFilter(response, data, filter);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
