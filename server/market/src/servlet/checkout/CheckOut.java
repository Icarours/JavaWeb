package servlet.checkout;

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
import bean.Payment;
import bean.Product;
import bean.ProductProperty;
import bean.User;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.AddressDao;
import dao.ProductDao;
import dao.ProductPropertyDao;

public class CheckOut extends HttpServlet {
	/**
	 * 1.7.1 结算中心信息 发送方式 发送URL 备注 POST /checkout
	 * 需要先登录获取userid，在将userid添加到请求头，如果返回错误码1533需要重新登录 参数名称 描述 样例 sku
	 * 商品ID:数量:属性ID|商品ID:数量:属性ID 1:3:1,2,3,4|2:2:2,3
	 *  需要请求头
	 *  RequestParams params = new RequestParams();
        params.addHeader("userid", "20428");
		params.addBodyParameter("sku", "1:3:1,2,3,4|2:2:2,3");
		mHttpUtils.send(HttpMethod.POST, HOST + "/checkout", params,
				new RequestCallBack<String>() {});
	 * 
	 */
	private AddressDao addressDao = new AddressDao();

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

			ProductDao productDao = new ProductDao();
			ProductPropertyDao propertyDao = new ProductPropertyDao();
			List<CartItem> cartItemList = new ArrayList<CartItem>();
			String sku = request.getParameter("sku");
			String[] results = sku.split("\\|");

			int totalCount = 0;
			double totalPrice = 0;

			for (String result : results) {

				CartItem item = new CartItem();
				String[] productsStr = result.split(":");
				String pid = productsStr[0];

				int pNum = Integer.parseInt(productsStr[1]);
				Product product = productDao.getProduct(Integer.parseInt(pid));
				if (productsStr.length > 2) {
					String pPropertyId = productsStr[2];
					List<ProductProperty> productProperty = propertyDao
							.getProductProperty(pPropertyId);
					product.setProductProperty(productProperty);
				}

				item.setProduct(product);
				item.setProdNum(pNum);
				totalCount += pNum;
				totalPrice += product.getPrice();
				cartItemList.add(item);
			}

			// 1.
			data.put("productList", cartItemList);

			List<Payment> paymentList = new ArrayList<Payment>();
			Payment payment = new Payment();
			payment.setType(1);
			payment.setDes("到付-现金");
			paymentList.add(payment);
			payment = new Payment();
			payment.setType(2);
			payment.setDes("到付-POS机");
			paymentList.add(payment);
			payment = new Payment();
			payment.setType(1);
			payment.setDes("支付宝");
			paymentList.add(payment);

			// 2.
			data.put("paymentList", paymentList);

			List<Payment> deliveryList = new ArrayList<Payment>();
			Payment deliver = new Payment();
			deliver.setType(1);
			deliver.setDes("周一至周五送货");
			deliveryList.add(deliver);
			deliver = new Payment();
			deliver.setType(2);
			deliver.setDes("双休日及公众假期送货");
			deliveryList.add(deliver);
			deliver = new Payment();
			deliver.setType(3);
			deliver.setDes("时间不限，工作日双休日及公众假期均可送货");
			deliveryList.add(deliver);

			// 3.
			data.put("deliveryList", deliveryList);

			CheckoutAddup checkoutAddup = new CheckoutAddup();
			checkoutAddup.setFreight(10);
			checkoutAddup.setTotalCount(totalCount);
			checkoutAddup.setTotalPrice(totalPrice);
			checkoutAddup.setTotalPoint(30);
			data.put("checkoutAddup", checkoutAddup);

			// 4.
			List<String> checkoutProm = Arrays.asList("促销信息一", "促销信息二");
			data.put("checkoutProm", checkoutProm);

			data.put("response", "checkOut");
			
			// 5.获取地址信息
			Address defaultAddressInfo = addressDao
					.getDefaultAddressInfo(userid);
			data.put("addressInfo", defaultAddressInfo);

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
