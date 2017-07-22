package dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import util.DataSourceManager;
import bean.Brand;
import bean.BrandInfo;

public class BrandInfoDao {
	private QueryRunner runner = new QueryRunner(DataSourceManager.getSource());

	public List<BrandInfo> getBrandInfos() {
		String sql = "select * from brand_info";
		try {
			List<BrandInfo> BrandInfoList = runner.query(sql,
					new BeanListHandler<BrandInfo>(BrandInfo.class));
			return BrandInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<BrandInfo> getBrandInfoById(int brandId) {

		String sql = "select * from brand_info where brand_id=?";
		try {
			List<BrandInfo> BrandInfoList = runner.query(sql,
					new BeanListHandler<BrandInfo>(BrandInfo.class), brandId);
			return BrandInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Brand> getBrands() {
		String sql = "select * from brand";
		try {
			List<Brand> brandList = runner.query(sql,
					new BeanListHandler<Brand>(Brand.class));

			for (Brand brand : brandList) {

				List<BrandInfo> brandInfos = getBrandInfoById(brand.getId());
				brand.setValue(brandInfos);
			}

			return brandList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
