package com.syl.basic;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: HelloDataTest
 * @Description:
 * @author Bright
 * @date 2017年7月6日 下午11:49:52
 */
public class HelloDataTest {
	HelloData helloData = new HelloData();

	/**
	 * @Title: setUp
	 * @Description:
	 * @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @Title: tearDown
	 * @Description:
	 * @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.syl.basic.HelloData#printStr()}.
	 */
	@Test
	public void testPrintStr() {
		helloData.printStr();
	}

	/**
	 * Test method for {@link com.syl.basic.HelloData#method(java.lang.String)}.
	 */
	@Test
	public void testMethod() {
		helloData.main(null);
	}

}
