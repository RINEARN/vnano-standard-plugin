/*
 * Author:  RINEARN (Fumihiro Matsui), 2020
 * License: CC0
 */

package org.vcssl.nano.plugin.system.xfci1;

import org.vcssl.connect.ArrayDataContainerInterface1;
import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

// Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html
// インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html

public class TimeXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	private long initialNanoTime = 0;

	// 接続時の初期化
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }

	// スクリプト実行前の初期化
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
		this.initialNanoTime = System.nanoTime();
	}

	// スクリプト実行後の終了時処理
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }

	// 接続解除時の終了時処理
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }


	// 関数名を返す
	@Override
	public String getFunctionName() {
		return "time";
	}

	// 引数は取らないので void 型を返す
	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { void.class };
	}

	// 引数名は定義されていないので false を返す
	@Override
	public boolean hasParameterNames() {
		return false;
	}

	// 引数名を返す
	@Override
	public String[] getParameterNames() {
		return new String[0];
	}

	// 任意型の引数は取らないので false を返す
	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[]{ false };
	}

	// 任意次元の引数は取らないので false を返す
	@Override
	public boolean[] getParameterRankArbitrarinesses() {
		return new boolean[]{ false };
	}

	// 参照渡しする必要はないので false を返す
	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ false };
	}

	// 引数の中身を書き変えないので true を返す（そう宣言しておくと最適化で少し有利になる可能性がある）
	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true };
	}

	// 任意個の引数は取らないので false を返す
	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	// 可変長引数は取らないので false を返す
	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	// 戻り値は int 型なので、その内部表現の long 型を返す
	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return long.class;
	}

	// 自動変換を介さず、処理系のデータコンテナそのものを取得したいので false を返す
	@Override
	public boolean isDataConversionNecessary() {
		return false;
	}

	// スクリプトから呼ばれた際に実行する処理
	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		// ※ データ変換を無効化している場合、arguments[0] は戻り値格納用で、arguments[1] が最初の引数

		// データ変換を無効化しているため、処理系依存のデータコンテナそのものを扱う必要があるため、まずその互換性を検査
		if (!(arguments[0] instanceof ArrayDataContainerInterface1)) {
			throw new ConnectorException(
				"The type of the data container \"" +
				arguments[0].getClass().getCanonicalName() +
				"\" is not supported by this plug-in."
			);
		}

		// データコンテナの型に変換（型パラメータは、このクラスの型宣言メソッドが正しく認識されていれば合っているはず）
		@SuppressWarnings("unchecked")
		ArrayDataContainerInterface1<long[]> outputDataContainer = (ArrayDataContainerInterface1<long[]>)arguments[0];

		// 戻り値格納用データコンテナのデータ領域が未確保なら確保する
		if (outputDataContainer.getData() == null) {
			outputDataContainer.setData(new long[1], 0);
		}

		// 現在の時刻を取得し、ミリ秒に変換
		long currentMilliTime = (System.nanoTime() - this.initialNanoTime) / 1000L;

		// 結果を戻り値データコンテナに格納する
		outputDataContainer.getData()[ outputDataContainer.getOffset() ] = currentMilliTime;

		// 自動データ型変換を無効化している場合は、戻り値は arguments[0] に格納するため、メソッドの戻り値としては何も返さない
		return null;
	}
}
