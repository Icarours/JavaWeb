package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.Help;
import bean.HelpDetail;

/**
 * 操作help表的dao类
 * 
 * @author ccy
 *
 */
public class HelpDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 通过客户端传递的verison值,返回服务器端数据库中的帮助列表
	 * 
	 * @param version
	 * @return
	 */
	public List<Help> getHelpList() {
		String sql = "select id,title from help";
		try {
			List<Help> helpList = runner.query(sql, new BeanListHandler<Help>(
					Help.class));
			return helpList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HelpDetail> getHelpDetails(String id) {

		String sql = "select * from help_detail";
		try {
			List<HelpDetail> HelpDetails = runner.query(sql,
					new BeanListHandler<HelpDetail>(HelpDetail.class));
			return HelpDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
