package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mysql.jdbc.log.Log;

import bean.User;
import util.DataSourceManager;

/**
 * 操作User表中的Dao类，
 * 
 * @author ccy
 */
public class UserDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 登陆时验证用户名密码
	 * 
	 * @return
	 */
	public User login(String username, String password) {

		if (username == null || username.trim().length() == 0
				|| password == null || password.trim().length() == 0) {

			return null;
		}

		String sql = "SELECT * FROM user where username=? and password=?";
		try {
			User user = runner.query(sql, new BeanHandler<User>(User.class),
					username, password);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 注册
	 */
	public boolean register(User user) {
		String sql1 = "select * from user where username = ?";
		String sql2 = "INSERT INTO user(username,password,bonus,level,userid) VALUES(?,?,?,?,?)";
		try {
			User registerUser = runner.query(sql1, new BeanHandler<User>(
					User.class), user.getUsername());
			if (registerUser != null) {
				return true;
			} else if (user.getPassword() != null) {
				runner.update(sql2, user.getUsername(), user.getPassword(),
						user.getBonus(), user.getLevel(), user.getUserid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 注销登陆
	 */
	public void logout(int userid) {
		String sql = "update user set token=? where userid=?";
		try {
			runner.update(sql, 0, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据 用户ID获取用户信息
	 * 
	 * @return
	 */
	public User getUserInfo(String userid) {

		System.out.println(userid);
		String sql = "SELECT userName,bonus,level,user_id FROM user WHERE user_id=?";
		try {
			User userinfo = runner.query(sql,
					new BeanHandler<User>(User.class), userid);
			return userinfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有
	 * 
	 * @return null
	 */
	public List<User> getAllData() {
		String sql = "select * from user";
		try {
			List<User> users = runner.query(sql, new BeanListHandler<User>(
					User.class));
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
