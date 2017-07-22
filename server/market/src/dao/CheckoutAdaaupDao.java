package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.CheckoutAddup;

public class CheckoutAdaaupDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<CheckoutAddup> getCheckoutAddup(String ordernum) {
		String sql = "select * from checkout_addup where ordernum = ?";
		try {
			List<CheckoutAddup> checkoutaddup = runner.query(sql,ordernum,
					new BeanListHandler<CheckoutAddup>(CheckoutAddup.class));
			return checkoutaddup;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
