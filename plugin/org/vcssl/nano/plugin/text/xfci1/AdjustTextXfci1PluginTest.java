package org.vcssl.nano.plugin.text.xfci1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;
import org.vcssl.nano.plugin.text.xvci1.TextModeConstants;

public class AdjustTextXfci1PluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSettings() {
		ExternalFunctionConnectorInterface1 function = new AdjustTextXfci1Plugin();

		// Check function name
		// 関数名を検査
		assertEquals("adjustText", function.getFunctionName());

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
		ExternalFunctionConnectorInterface1 function = new AdjustTextXfci1Plugin();

		String expected, actual;

		actual = (String)function.invoke(new Object[]{ " \r\n  ABCDE 	\n ", TextModeConstants.TRIM });
		expected = "ABCDE";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "ABCDE", TextModeConstants.LOWER_CASE });
		expected = "abcde";
		assertTrue(expected.equals(actual));

		actual = (String)function.invoke(new Object[]{ "abcde", TextModeConstants.UPPER_CASE });
		expected = "ABCDE";
		assertTrue(expected.equals(actual));
	}

}
