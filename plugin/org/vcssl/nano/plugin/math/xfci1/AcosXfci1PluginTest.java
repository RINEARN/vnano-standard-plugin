package org.vcssl.nano.plugin.math.xfci1;

import org.vcssl.nano.vm.memory.DataContainer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class AcosXfci1PluginTest {

	private static final int RANK_OF_SCALAR = 0;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSettings() {
		ExternalFunctionConnectorInterface1 function = new AcosXfci1Plugin();

		// Check function name
		// 関数名を検査
		assertEquals("acos", function.getFunctionName());

		// Check number and types of arguments
		// 引数の個数と型を検査
		assertEquals(1, function.getParameterClasses().length);
		assertTrue(function.getParameterClasses()[0] == double.class);

		// Check type of return value
		// 戻り値の型を検査
		assertEquals(double.class, function.getReturnClass(new Class<?>[] { double.class }));
	}


	@Test
	public void testDoubleScalar() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new AcosXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		inputDataContainer.setData(new double[] { 0.123 });
		outputDataContainer.setData(new double[] { 0.0 });

		// Operate data
		// 演算を実行
		function.invoke(new Object[]{ outputDataContainer, inputDataContainer });

		// Check dimensions of the operation result
		// 演算結果の次元を確認
		assertEquals(RANK_OF_SCALAR, outputDataContainer.getRank());
		assertEquals(0, outputDataContainer.getLengths().length);

		// Get result data in data container, and check its length
		// 演算結果のデータを取り出し、データ長を確認
		double[] resultData = outputDataContainer.getData();
		assertEquals(1, resultData.length);

		// Check result value
		// 演算結果の値を確認
		Double expected = Double.valueOf(Math.acos(0.123));
		Double actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));
	}


	@Test
	public void testDoubleArray1D() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new AcosXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		int[] inputArrayLengths = new int[] { 3 };
		int[] outputArrayLengths = new int[] { 3 };
		inputDataContainer.setData(new double[] { 0.123, 0.234, 0.345 }, inputArrayLengths);
		outputDataContainer.setData(new double[] { 0.0, 0.0, 0.0 }, outputArrayLengths);

		// Operate data
		// 演算を実行
		function.invoke(new Object[]{ outputDataContainer, inputDataContainer });

		// Check dimensions of the operation result
		// 演算結果の次元を確認
		assertEquals(1, outputDataContainer.getRank());
		assertEquals(1, outputDataContainer.getLengths().length);
		assertEquals(3, outputDataContainer.getLengths()[0]);

		// Get result data in data container, and check its length
		// 演算結果のデータを取り出し、データ長を確認
		double[] resultData = outputDataContainer.getData();
		assertEquals(3, resultData.length);

		Double expected;
		Double actual;

		// Check result value[0]
		// 演算結果[0]の値を確認
		expected = Double.valueOf(Math.acos(0.123));
		actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));

		// Check result value[1]
		// 演算結果[1]の値を確認
		expected = Double.valueOf(Math.acos(0.234));
		actual = Double.valueOf(resultData[1]);
		assertTrue(expected.equals(actual));

		// Check result value[2]
		// 演算結果[2]の値を確認
		expected = Double.valueOf(Math.acos(0.345));
		actual = Double.valueOf(resultData[2]);
		assertTrue(expected.equals(actual));
	}


	@Test
	public void testDoubleArray2D() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new AcosXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		int[] inputArrayLengths = new int[] { 2, 3 };
		int[] outputArrayLengths = new int[] { 2, 3 };
		inputDataContainer.setData(new double[] { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6 }, inputArrayLengths);
		outputDataContainer.setData(new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, outputArrayLengths);

		// Operate data
		// 演算を実行
		function.invoke(new Object[]{ outputDataContainer, inputDataContainer });

		// Check dimensions of the operation result
		// 演算結果の次元を確認
		assertEquals(2, outputDataContainer.getRank());
		assertEquals(2, outputDataContainer.getLengths().length);
		assertEquals(2, outputDataContainer.getLengths()[0]);
		assertEquals(3, outputDataContainer.getLengths()[1]);

		// Get result data in data container, and check its length
		// 演算結果のデータを取り出し、データ長を確認
		double[] resultData = outputDataContainer.getData();
		assertEquals(2*3, resultData.length);

		Double expected;
		Double actual;

		// Check result value[0]
		// 演算結果[0]の値を確認
		expected = Double.valueOf(Math.acos(0.1));
		actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));

		// Check result value[1]
		// 演算結果[1]の値を確認
		expected = Double.valueOf(Math.acos(0.2));
		actual = Double.valueOf(resultData[1]);
		assertTrue(expected.equals(actual));

		// Check result value[2]
		// 演算結果[2]の値を確認
		expected = Double.valueOf(Math.acos(0.3));
		actual = Double.valueOf(resultData[2]);
		assertTrue(expected.equals(actual));

		// Check result value[3]
		// 演算結果[3]の値を確認
		expected = Double.valueOf(Math.acos(0.4));
		actual = Double.valueOf(resultData[3]);
		assertTrue(expected.equals(actual));

		// Check result value[4]
		// 演算結果[4]の値を確認
		expected = Double.valueOf(Math.acos(0.5));
		actual = Double.valueOf(resultData[4]);
		assertTrue(expected.equals(actual));

		// Check result value[5]
		// 演算結果[5]の値を確認
		expected = Double.valueOf(Math.acos(0.6));
		actual = Double.valueOf(resultData[5]);
		assertTrue(expected.equals(actual));
	}

}