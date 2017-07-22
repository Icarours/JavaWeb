package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.ProdIdAndNum;

public class CartItemDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());
	/**
	 * 通过用户id找到当前对应商品的商品id与其商品购买的数量
	 * @param uid
	 * @return
	 */
	public Map<Integer, Integer> getProdIdAndNum(int uid) {
		//String sql = "select pid,prod_num from cart_item where user_id =?";
		String sql = "select pid,prod_num from cart_item where user_id =?;";
		try {
			Map<Integer, Integer> prod_num_item = new HashMap<Integer, Integer>();
			List<ProdIdAndNum> list = runner.query(sql, new BeanListHandler<ProdIdAndNum>(ProdIdAndNum.class), uid);
			
			for (ProdIdAndNum prodIdAndNum : list) {
				int pid = prodIdAndNum.getPid();
				int pnum = prodIdAndNum.getProd_num();
				//如果此时商品ID之前已经存在,将数量更新
				if (!prod_num_item.containsKey(pid)) {
					prod_num_item.put(pid, pnum);
				} else {
					Integer num = prod_num_item.get(pid);
					prod_num_item.put(pid, pnum+num);
				}
				
			}
			return prod_num_item;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加商品至购物车
	 * @param pid  商品id
	 * @param prod_num  商品数量
	 * @param prod_color 商品颜色
	 * @param prod_size 商品尺寸
	 */
	public void addProd(String pid, String prod_num, String prod_color,
			String prod_size,String user_id) {
		String sql = "insert into cart_item(pid,prod_num,prod_color,prod_size,user_id) values(?,?,?,?,?)";
		try {
			runner.update(sql, pid,prod_num,prod_color,prod_size,user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changeCartItemInfo(int uid) {
		String sql = "update cart_item set user_id = ? where user_id = 0";
		try {
			runner.update(sql, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新此时购物车列表中的数据
	 * @param pid 商品id
	 * @param prod_num  商品数量
	 * @param uid 
	 */
	public void updateCartList(String pid, String prod_num, String uid) {
		String sql = "update cart_item set prod_num=? where pid = ? and user_id = ?";
		try {
			runner.update(sql, prod_num,pid,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除掉购物车中某个商品
	 * @param pid
	 * @param uid
	 */
	public void deleteCartItem(String pid, String uid) {
		String sql = "delete from cart_item WHERE user_id =? and pid =?";
		try {
			runner.update(sql, uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
