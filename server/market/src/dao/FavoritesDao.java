package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mysql.jdbc.log.Log;

import bean.FavoritesBean;
import util.DataSourceManager;

public class FavoritesDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 收藏夹获取商品信息
	 * 
	 * @return
	 */
	public List<FavoritesBean> getFavorites(String userid, int page, int pageNum) {
		String sql = "select * from favorites where user_id = ? limit ?,?";
		try {
			List<FavoritesBean> favoritesList = runner.query(sql,
					new BeanListHandler<FavoritesBean>(FavoritesBean.class),
					userid, page, pageNum);
			return favoritesList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加收藏
	 * 
	 * @param userid
	 * @param pid
	 * @return -1:代表当前账户已经添加了当前商品 0:添加收藏夹失败 1:添加成功
	 */
	public int addItem(String userid, String pid) {
		try {
			String sqlQuery = "select id from favorites where user_id=? and pid=?";
			Object query = runner.query(sqlQuery, new ScalarHandler(), userid,
					pid);
			if (query != null) {
				return -1;
			} else {
				String sql = "insert into favorites (user_id,pid)values ( ?,?)";
				int update = runner.update(sql, userid, pid);
				if (update > 0) {
					return 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 删除收藏
	 * 
	 * @param id
	 */
	public void deleItem(String userid, String id) {
		String sql = "delete from favorites where id=? and userid=?";
		try {
			runner.update(sql, id, userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取收藏商品的总数
	 * 
	 * @param userId
	 * @return
	 */
	public int getFavoritesCount(String userId) {
		String sqlQuery = "select count(pid) from favorites where user_id=?";
		try {
			Object result = runner.query(sqlQuery, new ScalarHandler(), userId);
			if (result != null) {
				return Integer.parseInt(String.valueOf(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
