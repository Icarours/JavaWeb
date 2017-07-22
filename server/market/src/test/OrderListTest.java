package test;

import java.util.List;

import org.junit.Test;

import bean.OrderInfo;
import dao.OrderDao;

public class OrderListTest {
	@Test
	public void testGetOrderList() {
		OrderDao dao = new OrderDao();
		List<OrderInfo> infos = dao.getOrderList("20428", 3, 0, 10);
		System.out.println(infos.toString());
	}
}
