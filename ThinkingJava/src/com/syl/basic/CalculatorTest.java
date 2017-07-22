package com.syl.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: CalculatorTest
 * @Description:Calculator的测试用例
 * @author Bright
 * @date 2017年7月6日 下午10:34:25
 */
public class CalculatorTest {
	private static Calculator calculator = new Calculator();

	/**
	 * @Title: setUp
	 * @Description:
	 * @throws java.lang.Exception
	 * @return void
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
		calculator.clear();
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
	 * Test method for {@link com.syl.basic.Calculator#add(int)}.
	 */
	@Test
	public void testAdd() {
		calculator.add(2);
		calculator.add(3);
		assertEquals(5, calculator.getResult());
	}

	/**
	 * Test method for {@link com.syl.basic.Calculator#substract(int)}.
	 */
	@Test
	public void testSubstract() {
		calculator.add(10);
		calculator.substract(2);
		assertEquals(9, calculator.getResult());
	}

	/**
	 * Test method for {@link com.syl.basic.Calculator#multiply(int)}.
	 */
	@Test
	public void testMultiply() {
		calculator.add(2);
		calculator.multiply(4);
		assertEquals(8, calculator.getResult());
	}

	/**
	 * Test method for {@link com.syl.basic.Calculator#divide(int)}.
	 */
	@Test
	public void testDivide() {
		calculator.add(8);
		calculator.divide(2);
		assertEquals(4, calculator.getResult());
	}

}
