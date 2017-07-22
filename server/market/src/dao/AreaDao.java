package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.AreaBean;

public class AreaDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<AreaBean> getAreaBeanList(String parent) {
		String sql = "select * from area where parent = ?";
		try {
			List<AreaBean> areaBeanList = runner.query(sql, new BeanListHandler<AreaBean>(AreaBean.class), parent);
			return areaBeanList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
