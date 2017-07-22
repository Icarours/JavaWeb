package test;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import bean.Value;
import dao.ProductDao;

public class ProductTest {
	
	
	@Test
	public void testCategoryKeyIds() {
		
		ProductDao dao = new ProductDao();
		List<Value> values = dao.getBrandInfoValues();
		
		System.out.println(values);
	}
	
	@Test
	public void testGetBrandInfoIdByKeyId() {
		
		ProductDao dao = new ProductDao();
		Integer brandInfoIdByKeyId = dao.getBrandInfoIdByKeyId("s1");
		
		System.out.println(brandInfoIdByKeyId);
	}
	
	@Test
	public void testGetPriceValues() {
		
		ProductDao dao = new ProductDao();
		List<Value> values = dao.getPriceValues();
		System.out.println(values);
	}
	
	@Test
	public void testGetColorValues() {

		ProductDao dao = new ProductDao();
		List<Value> values = dao.getColorValues();
		System.out.println(values);
	}
	
	@Test
	public void testGetColorIdByKeyId() {
		
		ProductDao dao = new ProductDao();
		Integer colorIdByKeyId = dao.getColorIdByKeyId("t4");
		System.out.println(colorIdByKeyId);
	}

}
