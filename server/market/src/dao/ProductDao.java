package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import util.DataSourceManager;
import bean.Category;
import bean.Pics;
import bean.Product;
import bean.ProductProperty;
import bean.Value;

public class ProductDao {

	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	/**
	 * 商品信息
	 * 
	 * @param pid
	 * @return
	 */
	public Product getProduct(int pid) {
		String sql = "select * from product where id = ?";

		try {
			Product product = runner.query(sql, new BeanHandler<Product>(
					Product.class), pid);
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 热门商品列表
	 * 
	 * @param page
	 * @param pageNum
	 * @param orderby
	 * @return
	 */
	public List<Product> getHotProductList(int page, int pageNum, String orderby) {

		int index = -1;
		for (int i = 0; i < orderbyStrs.length; i++) {
			if (orderbyStrs[i].equals(orderby)) {
				index = i;
			}
		}
		String sql = "select * from product where isHot=1 order by "
				+ results[index] + " limit ?,?";
		try {
			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), page, pageNum);

			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新品商品列表
	 * 
	 * @param page
	 * @param pageNum
	 * @param orderby
	 * @return
	 */
	public List<Product> getNewProductList(int page, int pageNum, String orderby) {
		int index = -1;
		for (int i = 0; i < orderbyStrs.length; i++) {
			if (orderbyStrs[i].equals(orderby)) {
				index = i;
			}
		}
		String sql = "select * from product where isNew=1 order by "
				+ results[index] + " limit ?,?";
		try {
			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), page, pageNum);
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 限时抢购商品列表
	 * 
	 * @param leftTime
	 * @return
	 */
	public List<Product> getLeftTimeList(int page, int pageNum) {
		String sql = "select * from product where leftTime>0 limit ?,? ";
		try {

			List<Product> leftTimeList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), page, pageNum);
			return leftTimeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取分类信息
	 * 
	 * @param version
	 * @return
	 */
	public List<Category> getCategoryList() {

		String sql = "select * from category";
		try {
			List<Category> categoryList = runner.query(sql,
					new BeanListHandler<Category>(Category.class));
			return categoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String[] orderbyStrs = { "saleDown", "priceUp", "priceDown",
			"commentDown", "shelvesDown" };
	private String[] results = { "saleNum desc", "price asc", "price desc",
			"comment desc", "shelves desc" };

	/**
	 * 获取专题商品列表
	 * 
	 * @param page
	 * @param pageNum
	 * @param id
	 * @param orderby
	 * @return
	 */
	public List<Product> getTopicPList(int page, int pageNum, int id,
			String orderby) {
		int index = -1;
		for (int i = 0; i < orderbyStrs.length; i++) {
			if (orderbyStrs[i].equals(orderby)) {
				index = i;
			}
		}
		String sql = "select id,name,pic,marketprice,price from product where topic_id=? order by "
				+ results[index] + " limit ?,?";
		try {
			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), id, page,
					pageNum);
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取品牌商品列表
	 * 
	 * @param page
	 * @param pageNum
	 * @param id
	 * @param orderby
	 * @return
	 */
	public List<Product> getBrandPList(int page, int pageNum, int id,
			String orderby) {
		int index = -1;
		for (int i = 0; i < orderbyStrs.length; i++) {
			if (orderbyStrs[i].equals(orderby)) {
				index = i;
			}
		}
		String sql = "select id,name,pic,marketprice,price from product where brand_id=? order by "
				+ results[index] + " limit ?,?";
		try {
			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), id, page,
					pageNum);
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取搜索结果商品列表
	 * 
	 * @param keyword
	 * @param page
	 * @param pageNum
	 * @param orderby
	 * @return
	 */
	public List<Product> getSearchProducts(String keyword, int page,
			int pageNum, String orderby) {
		int index = -1;
		for (int i = 0; i < orderbyStrs.length; i++) {
			if (orderbyStrs[i].equals(orderby)) {
				index = i;
			}
		}
		String sql = "select id,name,pic,marketprice,price from product where name like ? order by "
				+ results[index] + " limit ?,?";
		try {
			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), "%" + keyword
							+ "%", page, pageNum);
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 
	 * 返回所有的Value(s1,name)
	 */
	public List<Value> getBrandInfoValues() {

		try {

			String sql = "select s as id,name from brand_info";
			List<Value> values = runner.query(sql, new BeanListHandler<Value>(
					Value.class));

			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 
	 * 根据品牌的keyId(s1,s2,s3....)获取品牌id
	 */
	public Integer getBrandInfoIdByKeyId(String keyId) {

		String sql = "select id from brand_info where s=?";
		try {
			Integer id = (Integer) runner
					.query(sql, new ScalarHandler(), keyId);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 获取所有商品的价格以id：p1,name:200封装到对象Value
	 */
	public List<Value> getPriceValues() {

		try {

			String sql = "SELECT key_t AS id,price AS name FROM prices";
			List<Value> values = runner.query(sql, new BeanListHandler<Value>(
					Value.class));

			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 
	 * 根据价格的keyId(p1,p2,p3....)获取价格id
	 */
	public Float getPriceByKeyId(String keyId) {

		String sql = "select price from prices where key_t=?";
		try {
			Float id = (Float) runner.query(sql, new ScalarHandler(), keyId);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 获取所有商品的颜色以id：p1,name:红色封装到对象Value
	 */
	public List<Value> getColorValues() {

		try {

			String sql = "SELECT key_t AS id,color AS name FROM colors";
			List<Value> values = runner.query(sql, new BeanListHandler<Value>(
					Value.class));

			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 
	 * 根据价格的keyId(t1,t2,t3....)获取价格id
	 */
	public Integer getColorIdByKeyId(String keyId) {

		String sql = "select id from colors where key_t=?";
		try {
			Integer id = (Integer) runner
					.query(sql, new ScalarHandler(), keyId);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取分类商品列表
	 * 
	 * @param filterStr
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<Product> getCategoryProductList(String filterStr,
			int categoryId, int page, int pageNum, String orderby) {
		int brand_id = -1;
		float price = 1000;
		int colorId = -1;
		try {
			String sql = null;
			int index = 0;
			for (int i = 0; i < orderbyStrs.length; i++) {
				if (orderbyStrs[i].equals(orderby)) {
					index = i;
				}
			}
			if (filterStr == null || filterStr == "") {
				sql = "select id,name,pic,marketprice,price,commentCount from product where category_id=? and price<? and brand_id>? and color_id>? order by "
						+ results[index] + " limit ?,?";
			} else {
				String[] filterResults = filterStr.split("-");
				for (String filterResult : filterResults) {
					if (filterResult.startsWith("s")) {

						brand_id = getBrandInfoIdByKeyId(filterResult);

					} else if (filterResult.startsWith("p")) {

						Float f_price = getPriceByKeyId(filterResult);
						if (f_price != null) {
							price = f_price;
						}

					} else if (filterResult.startsWith("t")) {

						colorId = getColorIdByKeyId(filterResult);
					}
				}

				if (brand_id == -1 && colorId == -1) {
					sql = "select id,name,pic,marketprice,price,commentCount from product where category_id=? and price<? and brand_id>? and color_id>? order by "
							+ results[index] + " limit ?,?";
				} else if (brand_id == -1 && colorId != -1) {
					sql = "select id,name,pic,marketprice,price,commentCount from product where category_id=? and price<? and brand_id>? and color_id=? order by "
							+ results[index] + " limit ?,?";
				} else if (brand_id != -1 && colorId == -1) {
					sql = "select id,name,pic,marketprice,price,commentCount from product where category_id=? and price<? and brand_id=? and color_id>? order by "
							+ results[index] + " limit ?,?";
				} else {
					sql = "select id,name,pic,marketprice,price,commentCount from product where category_id=? and price<? and brand_id=? and color_id=? order by "
							+ results[index] + " limit ?,?";
				}
			}

			List<Product> productList = runner.query(sql,
					new BeanListHandler<Product>(Product.class), categoryId,
					price, brand_id, colorId, page, pageNum);
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取商品详情
	 * 
	 * @param pId
	 * @return
	 */
	public Product getProductDetail(int pId) {
		try {
			String sql = "select id,name,marketPrice,price,limitPrice,leftTime,score,available,buyLimit,commentCount,inventoryArea,product_property_id from product where id = ?";

			String picSql = "select url from pic where product_id=?";
			String bigPicSql = "select url from bigPic where product_id=?";

			Product product = runner.query(sql, new BeanHandler<Product>(
					Product.class), pId);

			List<Object[]> results = runner.query(picSql,
					new ArrayListHandler(), pId);
			List<String> picResults = new ArrayList<String>();
			for (Object[] object : results) {
				picResults.add((String) object[0]);
			}
			product.setPics(picResults);

			List<Object[]> bigResults = runner.query(bigPicSql,
					new ArrayListHandler(), pId);
			List<String> bigPicResults = new ArrayList<String>();
			for (Object[] object : bigResults) {
				bigPicResults.add((String) object[0]);
			}
			product.setBigPic(bigPicResults);
			String product_property = product.getProduct_property_id();
			if (product_property != null && !"".equals(product_property)) {
				String[] propertyIds = product_property.split(",");
				List<ProductProperty> productPropertyList = new ArrayList<ProductProperty>();
				for (String propertyId : propertyIds) {
					int id = Integer.parseInt(propertyId);
					String propertySql = "select * from product_property where id=?";
					try {
						ProductProperty productProperty = runner.query(
								propertySql, new BeanHandler<ProductProperty>(
										ProductProperty.class), id);
						productPropertyList.add(productProperty);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				product.setProductProperty(productPropertyList);
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
