package org.vcssl.nano.plugin.simplecalculator;

import org.vcssl.connect.ExternalFunctionConnector1;

import org.vcssl.connect.ArrayDataContainer1;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ConnectorPermission;

public class SinFunctionPlugin implements ExternalFunctionConnector1 {

	@Override
	public String getFunctionName() {
		return "sin";
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
		return new String[] { "arg" };
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return parameterClasses[0];
	}

	@Override
	public boolean isVariadic() {
		return false;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return false;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// Check types of data containers.
		if (!(arguments[0] instanceof ArrayDataContainer1) || !(arguments[1] instanceof ArrayDataContainer1)) {
			throw new ConnectorException("The type of the data container is not supported by this plug-in.");
		}

		// Check types of data in data containers.
		Object inputDataObject = ( (ArrayDataContainer1<?>)arguments[1] ).getData();
		if (!(inputDataObject instanceof double[])) {
			throw new ConnectorException("The data type of the argument of \"sin\" function should be \"float\" or \"double\".");
		}
		Object outputDataObject = ( (ArrayDataContainer1<?>)arguments[0] ).getData();
		if (!(outputDataObject instanceof double[])) {
			throw new ConnectorException("The data type of the return value of \"sin\" function should be \"float\" or \"double\".");
		}

		// Cast data containers
		@SuppressWarnings("unchecked")
		ArrayDataContainer1<double[]> outputDataContainer = (ArrayDataContainer1<double[]>)arguments[0];
		@SuppressWarnings("unchecked")
		ArrayDataContainer1<double[]> inputDataContainer = (ArrayDataContainer1<double[]>)arguments[1];

		// Cast data in data containers.
		double[] inputData = (double[])inputDataObject;
		double[] outputData = (double[])outputDataObject;

		// Reallocate output data if necessary.
		int dataLength = inputData.length;
		if (outputData.length != dataLength) {
			outputData = new double[dataLength];
		}

		// Operate data
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.sin(inputData[i]);
		}

		// Store result data
		outputDataContainer.setData(outputData, inputDataContainer.getLengths());

		return null;
	}


	@Override
	public String[] getNecessaryPermissions() { return new String[] { ConnectorPermission.NONE }; }
	@Override
	public String[] getUnnecessaryPermissions() { return new String[] { ConnectorPermission.ALL }; }
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }
}
