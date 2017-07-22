package test;

import java.util.List;

import org.junit.Test;

import bean.FavoritesBean;
import dao.FavoritesDao;

public class FavoritesDaoTest {
	@Test
	public void testGetFavourites(){
		FavoritesDao dao=new FavoritesDao();
		List<FavoritesBean> beans = dao.getFavorites(12345+"", 0, 1);
		System.out.println(beans.toString());
	}
}
