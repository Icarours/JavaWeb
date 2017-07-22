package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.HomeTopic;

public class HomeTopicDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<HomeTopic> getHomeTopics() {
		String sql = "select * from home_topic where istopic=1";
		try {
			List<HomeTopic> helpList = runner.query(sql, new BeanListHandler<HomeTopic>(HomeTopic.class));
			return helpList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
