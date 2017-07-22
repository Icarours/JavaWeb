package test;

import java.util.List;

import org.junit.Test;





import bean.Pic;
import dao.PicDao;

public class PicDaoTest {
	@Test
	public void testGetPicList(){
		PicDao dao = new PicDao();
		List<Pic> picList = dao.getPicList(1);
		System.out.println(picList);
	}
	
}
