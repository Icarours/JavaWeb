package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import util.DataSourceManager;
import bean.Version;

public class VersionDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 获取版本信息
	 * 
	 * @param version
	 * @return
	 */
	public Version getVersionInfo() {
		String sql = "select * from version";
		try {
			Version ver = runner.query(sql, new BeanHandler<Version>(Version.class));
			return ver;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
