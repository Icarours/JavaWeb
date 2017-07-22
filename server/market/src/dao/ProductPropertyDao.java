package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Product;
import bean.ProductProperty;
import util.DataSourceManager;

public class ProductPropertyDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 通过商品ID查询其属性
	 * 
	 * @param id
	 * @return
	 */
	public List<ProductProperty> getProductProperty(String id) {
		String[] propertyIds = id.split(",");
		List<ProductProperty> productPropertyList = new ArrayList<ProductProperty>();
		for (String propertyId : propertyIds) {
			int pId = Integer.parseInt(propertyId);
			String sql = "select * from product_property where id=?";
			try {
				ProductProperty productProperty = runner.query(sql, new BeanHandler<ProductProperty>(ProductProperty.class), pId);
				productPropertyList.add(productProperty);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productPropertyList;
	}

	/**
	 * 
	 * 获取单一商品属性
	 * 
	 * @param id
	 * @return
	 */
	public ProductProperty getProperty(int id) {
		String sql = "select k,v from product_property where id = ?";
		try {
			ProductProperty property = runner.query(sql, new BeanHandler<ProductProperty>(ProductProperty.class), id);
			return property;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
