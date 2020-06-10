package org.vcssl.nano.plugin.math.xfci1;

import org.vcssl.nano.vm.memory.DataContainer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class SumXfci1PluginTest {

	private static final int RANK_OF_SCALAR = 0;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSettings() {
		ExternalFunctionConnectorInterface1 function = new SumXfci1Plugin();

		// Check function name
		// 関数名を検査
		assertEquals("sum", function.getFunctionName());

		// Check number and types of arguments
		// 引数の型を検査
		assertEquals(1, function.getParameterClasses().length);
		assertTrue(function.getParameterClasses()[0] == double.class);

		// Check number of parameters arbitrary
		// 引数の個数が任意に設定されている事を検査
		assertTrue(function.isParameterCountArbitrary());

		// Check type of return value
		// 戻り値の型を検査
		assertEquals(double.class, function.getReturnClass(new Class<?>[] { double.class }));
	}


	@Test
	public void testDoubleSingleArg() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new SumXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		inputDataContainer.setData(new double[] { 1.23 });
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
		Double expected = Double.valueOf(1.23);
		Double actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testDoubleDoubleArgs() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new SumXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer1 = new DataContainer<double[]>();
		DataContainer<double[]> inputDataContainer2 = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		inputDataContainer1.setData(new double[] { 1.23 });
		inputDataContainer2.setData(new double[] { 4.56 });
		outputDataContainer.setData(new double[] { 0.0 });

		// Operate data
		// 演算を実行
		function.invoke(new Object[]{ outputDataContainer, inputDataContainer1, inputDataContainer2 });

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
		Double expected = Double.valueOf(1.23 + 4.56);
		Double actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testTripleDoubleArgs() throws ConnectorException {
		ExternalFunctionConnectorInterface1 function = new SumXfci1Plugin();

		// Prepare input/output data
		// 入出力データを用意
		DataContainer<double[]> inputDataContainer1 = new DataContainer<double[]>();
		DataContainer<double[]> inputDataContainer2 = new DataContainer<double[]>();
		DataContainer<double[]> inputDataContainer3 = new DataContainer<double[]>();
		DataContainer<double[]> outputDataContainer = new DataContainer<double[]>();
		inputDataContainer1.setData(new double[] { 1.23 });
		inputDataContainer2.setData(new double[] { 4.56 });
		inputDataContainer3.setData(new double[] { 7.89 });
		outputDataContainer.setData(new double[] { 0.0 });

		// Operate data
		// 演算を実行
		function.invoke(new Object[]{ outputDataContainer, inputDataContainer1, inputDataContainer2, inputDataContainer3 });

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
		Double expected = Double.valueOf(1.23 + 4.56 + 7.89);
		Double actual = Double.valueOf(resultData[0]);
		assertTrue(expected.equals(actual));
	}

}