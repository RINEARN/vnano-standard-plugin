package org.vcssl.nano.plugin.system.xfci1;

import java.util.Locale;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

public class RankXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	Locale locale = null;

	// 接続時の初期化
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }

	// スクリプト実行前の初期化
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException { }

	// スクリプト実行後の終了時処理
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }

	// 接続解除時の終了時処理
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }


	@Override
	public final String getFunctionName() {
		return "rank";
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { Object.class };
	}

	@Override
	public boolean hasParameterNames() {
		return true;
	}

	@Override
	public String[] getParameterNames() {
		return new String[] { "array" };
	}

	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ true };
	}


	@Override
	public boolean[] getParameterRankArbitrarinesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ true };
	}

	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return long.class;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataContainerInterface1)
				|| !(arguments[1] instanceof ArrayDataContainerInterface1) ) {

			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Get rank of the array argument
		ArrayDataContainerInterface1<?> arrayArgDataContainer = (ArrayDataContainerInterface1<?>)arguments[1];
		int rank = arrayArgDataContainer.getRank();

		// Get or allocate output data
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> outputContainer = (ArrayDataContainerInterface1<long[]>)arguments[0];
		Object outputDataObject = outputContainer.getData();
		long[] outputData = null;
		int outputOffset = -1;
		if (outputContainer.getRank() == 0 && outputDataObject instanceof long[] && 1 <= ((long[])outputDataObject).length) {
			outputData = (long[])outputDataObject;
			outputOffset = outputContainer.getOffset();
		} else {
			outputData = new long[ 1 ];
			outputOffset = 0;
		}

		// Store result data
		outputData[outputOffset] = (long)rank;

		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> outputDataContainer = (ArrayDataContainerInterface1<long[]>)arguments[0];
		outputDataContainer.setData(outputData, outputOffset);

		return null;
	}
}