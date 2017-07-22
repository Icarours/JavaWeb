package test;

import org.junit.Test;

import bean.Version;
import dao.VersionDao;

public class VersionDaoTest {
	@Test
	public void testGetVersionInfo() {
		VersionDao dao = new VersionDao();
		Version version = dao.getVersionInfo();
		System.out.println(version.toString());
	}
}
