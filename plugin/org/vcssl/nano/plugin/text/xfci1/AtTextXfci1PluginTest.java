package org.vcssl.nano.plugin.text.xfci1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class AtTextXfci1PluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSettings() {
		ExternalFunctionConnectorInterface1 function = new AtTextXfci1Plugin();

		// Check function name
		// 関数名を検査
		assertEquals("atText", function.getFunctionName());

		// Check number and types of arguments
		// 引数の個数と型を検査
		assertEquals(2, function.getParameterClasses().length);
		assertTrue(function.getParameterClasses()[0] == String.class);
		assertTrue(function.getParameterClasses()[1] == long.class);

		// Check type of return value
		// 戻り値の型を検査
		assertEquals(String.class, function.getReturnClass(new Class<?>[] { }));
	}


	@Test
	public void test() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new AtTextXfci1Plugin();

		String expected, actual;

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(0L) });
		expected = "A";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(3L) });
		expected = "D";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(4L) });
		expected = "E";
		assertTrue(expected.equals(actual));

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(5L) });
			fail("The expected Exception had not been thrown.");
		} catch (ConnectorException ce) {
		}

		try {
			actual = (String)function.invoke(new Object[]{ "ABCDE", Long.valueOf(-1L) });
			fail("The expected Exception had not been thrown.");
		} catch (ConnectorException ce) {
		}
	}

}
