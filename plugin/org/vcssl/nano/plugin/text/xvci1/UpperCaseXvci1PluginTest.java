package org.vcssl.nano.plugin.text.xvci1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalVariableConnectorInterface1;

public class UpperCaseXvci1PluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ExternalVariableConnectorInterface1 variable = new UpperCaseXvci1Plugin();

		// Check variable name
		// 変数名を検査
		assertEquals("UPPER_CASE", variable.getVariableName());

		// Check data type
		// データ型を検査
		assertTrue(variable.getDataClass() == long.class);

		// Check value
		// 値を検査
		try {
			Long value = (Long)(variable.getData());
			assertEquals(Long.valueOf(20003L), value);
		} catch (ConnectorException e) {
			e.printStackTrace();
			fail();
		}

		// Check constantness
		// 書き換え不可能である事を確認
		try {
			variable.setData(Long.valueOf(0L));
			fail("Expected exception have not occurred");
		} catch (ConnectorException e) {
			// 例外が発生するのが正しい挙動
		}
	}

}
