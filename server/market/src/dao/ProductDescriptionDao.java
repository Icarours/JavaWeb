package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.DataSourceManager;

/*
 *  1.5.4 商品描述
	发送方式	发送URL	备注
	GET	/product/description	
	参数名称	描述	样例
	pId	商品ID	2
	
 *  http://localhost:8080/market/product/description?pId=2
 */

public class ProductDescriptionDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());
	
	/**
	 * 获取商品详细详情
	 * @param pId
	 * @return
	 */
	public String getProductDescription(int pId) {
		String sql = "select productdesc from product_description where product_id=?";
		try {
			Object result = runner.query(sql, new ScalarHandler(), pId);
			if (result != null) {
				return String.valueOf(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
