package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.DataSourceManager;
import bean.OrderInfo;
import bean.OrderProDetail;

public class OrderDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 查询订单
	 * 
	 * @param bean
	 * @return
	 */
	public List<OrderInfo> getOrderList(String userId, int type, int page,
			int pageNum) {
		try {
			switch (type) {
			case 1:
				String queryOneMinuteSql = "select orderId,status,time,flag,price from order_info where time>? and userid=? and type=1 limit ?,?";
				List<OrderInfo> infos = runner.query(queryOneMinuteSql,
						new BeanListHandler<OrderInfo>(OrderInfo.class),
						System.currentTimeMillis() - 10 * 60 * 1000, userId,
						page, pageNum);
				return infos;
			case 2:
				queryOneMinuteSql = "select orderId,status,time,flag,price from order_info where time<? and userid=? and type=1 limit ?,?";
				infos = runner.query(queryOneMinuteSql,
						new BeanListHandler<OrderInfo>(OrderInfo.class),
						System.currentTimeMillis() - 10 * 60 * 1000, userId,
						page, pageNum);
				return infos;
			case 3:
				String queryCancelSql = "select orderId,status,time,flag,price from order_info where userid=? and type=0 limit ?,?";
				infos = runner.query(queryCancelSql,
						new BeanListHandler<OrderInfo>(OrderInfo.class),
						userId, page, pageNum);
				return infos;

			default:
				break;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return null;

	}

	/**
	 * 查询单个订单
	 * 
	 * @param 根据订单
	 *            id
	 * @return
	 */
	public OrderInfo getOrder(String id) {

		String sql = "select * from order_info where orderId= ?";
		try {
			OrderInfo order = runner.query(sql, new BeanHandler<OrderInfo>(
					OrderInfo.class), id);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * 
	 * 
	 */
	public int getOrderCount(String userid) {

		String sql = "select count(*) from order_info where userid= ?";
		try {
			Object result = runner.query(sql, new ScalarHandler(), userid);
			if (result != null) {
				return Integer.parseInt(String.valueOf(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * 取消状态
	 * 
	 * @param orderid
	 * @return
	 */
	public int cancelOrder(String orderid) {
		String sql = "update order_info set type=0 WHERE orderid = ?";
		try {
			int update = runner.update(sql, orderid);
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 添加一条订单数据
	 * 
	 * @param info
	 */
	public void addOrder(OrderInfo info) {
		String sql = "insert into order_info (orderId,status,flag,price,userid,time,type) values (?,?,?,?,?,?,?)";
		try {
			runner.update(sql, info.getOrderId(), info.getStatus(),
					info.getFlag(), info.getPrice(), info.getUserid(),
					info.getTime(), true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交结算
	 * 
	 * @param orderProDetail
	 */
	public void submitOrder(OrderProDetail orderProDetail) {
		String sql = "insert into order_detail (pid,pNum,color,size,order_id,paymentType,deliveryType,invoiceType,invoiceTitle,invoiceContent,addressId) values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			runner.update(sql, orderProDetail.getPid(),
					orderProDetail.getpNum(), orderProDetail.getColor(),
					orderProDetail.getSize(), orderProDetail.getOrder_id(),
					orderProDetail.getPaymentType(),
					orderProDetail.getDeliveryType(),
					orderProDetail.getInvoiceType(),
					orderProDetail.getInvoiceTitle(),
					orderProDetail.getInvoiceContent(),
					orderProDetail.getAddressId());
		} catch (Exception e) {
			System.out.println("插入订单详情异常");
			e.printStackTrace();
		}
	}

	/**
	 * 获取订单对应的所有的订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderProDetail> getOrderDetailByOrderId(String orderId) {
		String sql = "select * from order_detail where order_id= ?";
		try {
			List<OrderProDetail> orderDetailList = runner.query(sql,
					new BeanListHandler<OrderProDetail>(OrderProDetail.class),
					orderId);
			return orderDetailList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
