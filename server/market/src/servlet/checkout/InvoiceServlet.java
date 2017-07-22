package servlet.checkout;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.CommonUtil;
/*
 * 1.7.3 发票
         发送方式	发送URL	备注
   POST	/invoice	
   http://192.168.138.1:8080/market/invoice	
   
 */

public class InvoiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//String result = URLEncoder.encode("传智博客", "UTF-8");
		//System.out.println(result);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "invoice");
		List<Map<String, Object>> invoiceList = new ArrayList<Map<String, Object>>();
		Map<String, Object> invoice = new HashMap<String, Object>();
		invoice.put("id", 1);
		invoice.put("content", "图书");
		invoiceList.add(invoice);
		invoice = new HashMap<String, Object>();
		invoice.put("id", 2);
		invoice.put("content", "服装");
		invoiceList.add(invoice);
		invoice = new HashMap<String, Object>();
		invoice.put("id", 3);
		invoice.put("content", "耗材");
		invoiceList.add(invoice);
		invoice = new HashMap<String, Object>();
		invoice.put("id", 4);
		invoice.put("content", "软件");
		invoiceList.add(invoice);
		invoice = new HashMap<String, Object>();
		invoice.put("id", 5);
		invoice.put("content", "资料");
		invoiceList.add(invoice);

		data.put("invoice", invoiceList);

		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
