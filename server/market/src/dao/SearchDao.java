package dao;

import java.util.List;
import java.util.Random;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.DataSourceManager;
import bean.HomeTopic;
import bean.Product;
import bean.SearchBean;

public class SearchDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<Product> getProducts(SearchBean sb) {
		String order = "";
		String what = "price";
		if (sb.getOrderby() == "priceUp") {
			order = "ASC";

		} else if (sb.getOrderby() == "priceDown") {
			order = "DESC";
		}

		String sql = "select id,name,pic,marketPrice,price from product where name like ? order by ? ? limit ?,?";
		try {
			List<Product> products = runner.query(sql,
					new BeanListHandler<Product>(Product.class), "%"
							+ sb.getKeyword() + "%", what, order, sb.getPage(),
					sb.getPageNum());
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object> getRecommends() {

		//String sql1 = "select count(*) from product";

		String sql2 = "select * from product limit ?,?";

		try {

			//Long count = (Long) runner.query(sql1, new ScalarHandler());

			Random random = new Random();
			long nextInt = random.nextInt(4);

			List<Object> searchKeyWords = runner.query(sql2,
					new ColumnListHandler("name"), nextInt, nextInt + 6);
			return searchKeyWords;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
