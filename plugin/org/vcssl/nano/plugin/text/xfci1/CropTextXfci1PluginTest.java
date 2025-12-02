package org.vcssl.nano.plugin.text.xfci1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class CropTextXfci1PluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSettings() {
		ExternalFunctionConnectorInterface1 function = new CropTextXfci1Plugin();

		// Check function name
		// 関数名を検査
		assertEquals("cropText", function.getFunctionName());

		// Check number and types of arguments
		// 引数の個数と型を検査
		assertEquals(3, function.getParameterClasses().length);
		assertTrue(function.getParameterClasses()[0] == String.class);
		assertTrue(function.getParameterClasses()[1] == long.class);
		assertTrue(function.getParameterClasses()[2] == long.class);

		// Check type of return value
		// 戻り値の型を検査
		assertEquals(String.class, function.getReturnClass(new Class<?>[] { }));
	}


	@Test
	public void test() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new CropTextXfci1Plugin();

		String expected, actual;

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(0L), Long.valueOf(5L)});
		expected = "ABCDE";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(1L), Long.valueOf(5L)});
		expected = "BCDE";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(1L), Long.valueOf(4L)});
		expected = "BCD";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(2L), Long.valueOf(3L)});
		expected = "C";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(2L), Long.valueOf(2L)});
		expected = "";
		assertTrue(expected.equals(actual));

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(2L), Long.valueOf(1L)});
			fail("The expected Exception has not been thrown.");
		} catch (ConnectorException ce) {
		}

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(-1L), Long.valueOf(5L)});
			fail("The expected Exception has not been thrown.");
		} catch (ConnectorException ce) {
		}

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(0L), Long.valueOf(-1L)});
			fail("The expected Exception has not been thrown.");
		} catch (ConnectorException ce) {
		}

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(0L), Long.valueOf(8L)});
			fail("The expected Exception has not been thrown.");
		} catch (ConnectorException ce) {
		}
	}

}
