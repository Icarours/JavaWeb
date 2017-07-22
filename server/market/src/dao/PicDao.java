package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Pic;
import util.DataSourceManager;

public class PicDao {
	private QueryRunner runner=new QueryRunner(DataSourceManager.getSource());
	public List<Pic> getPicList(int id){
		String sql = "select url from Pic where product_id=?";
		try {
			List<Pic> picList = runner.query(sql, new BeanListHandler<Pic>(Pic.class),id);
			return picList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		}
	
}
