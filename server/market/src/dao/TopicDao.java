package dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.Topic;

public class TopicDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());


	/**
	 * 获取促销快报列表
	 * @param pageNum 
	 * @param page 
	 * 
	 * @return
	 */
	public List<Topic> getTopicList(int page, int pageNum) {
		String sql = "select * from topic limit ?,?";
		try {
			List<Topic> topicList = runner.query(sql, new BeanListHandler<Topic>(Topic.class),page,pageNum);
			return topicList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
