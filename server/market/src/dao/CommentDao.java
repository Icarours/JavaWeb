package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Comment;

import util.DataSourceManager;

public class CommentDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<Comment> getComment(int pId, int page, int pageNum) {
		String sql = "select title,content,username,time from comment where product_id=? limit ?,?";
		try {
			List<Comment> commentList = runner.query(sql,
					new BeanListHandler<Comment>(Comment.class), pId, page,
					pageNum);
			return commentList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
