package test;

import org.junit.Test;

import bean.Address;
import dao.AddressDao;

public class AddressTest {
	@Test
	public void testGetAddressInfo(){
		AddressDao dao=new AddressDao();
		Address address = dao.getAddressInfoById(139);
		System.out.println(address.toString());
	}
}
