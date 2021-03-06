/*
 * ==================================================
 * Method to XFCI Plug-in Adapter
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2020 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * <p>
 * ホスト言語側のメソッドを、{@link org.vcssl.connect.ExternalFunctionConnectorInterface1 XFCI 1}
 * 形式の外部変数プラグイン仕様に変換し、XFCI 1 対応の言語処理系に接続するためのアダプタです。
 * </p>
 *
 * @author RINEARN (Fumihiro Matsui)
 */
public class MethodToXfci1Adapter implements ExternalFunctionConnectorInterface1 {

	/** ホスト言語側のメソッドへの、リフレクションによるアクセスを提供するMethodインスタンスです。 */
	private Method method = null;

	/** ホスト言語のメソッドが属するオブジェクトのインスタンスです。 */
	private Object objectInstance = null;


	/**
	 * 指定されたホスト言語側のインスタンスメソッドを、
	 * XFCI準拠の外部変数プラグインへと変換するアダプタを生成します。
	 *
	 * @param method 対象メソッドへのリフレクションによるアクセスを提供するMethodインスタンス
	 * @param objectInstance 対象メソッドが属するオブジェクトのインスタンス
	 */
	public MethodToXfci1Adapter(Method method, Object objectInstance) {
		this.method = method;
		this.objectInstance = objectInstance;
	}


	/**
	 * 指定されたホスト言語側のクラスメソッドを、
	 * XFCI準拠の外部変数プラグインへと変換するアダプタを生成します。
	 *
	 * @param method 対象メソッドへのリフレクションによるアクセスを提供するMethodインスタンス
	 */
	public MethodToXfci1Adapter(Method method) {
		this.method = method;
		this.objectInstance = null;
	}


	/**
	 * 関数名を取得します。
	 *
	 * @return 関数名
	 */
	@Override
	public String getFunctionName() {
		return this.method.getName();
	}


	/**
	 * 全ての仮引数の名称情報を保持しており、
	 * {@link ExternalFunctionConnectorInterface1 getParameterNames}
	 * メソッドによって取得可能であるかどうかを返しますが、
	 * このアダプタは常に false を返します。
	 *
	 * @return 各仮引数の名称を取得可能かどうか
	 */
	@Override
	public boolean hasParameterNames() {
		return false;
	}


	/**
	 * 全ての仮引数の名称を配列として取得するメソッドですが、
	 * このアダプタではサポートしない（
	 * {@link MethodToXfci1Adapter#hasParameterNames hasParameterNames}
	 * の戻り値が false）ため常に null を返します。
	 *
	 * @return null
	 */
	@Override
	public String[] getParameterNames() {
		return null;
	}


	/**
	 * 引数のデータ型と配列次元数をまとめて表すClassインスタンスを、
	 * 各仮引数ごとに各要素値として格納する配列を取得します。
	 *
	 * @return 各仮引数の型/次元を表すClassインスタンスを格納する配列
	 */
	@Override
	public Class<?>[] getParameterClasses() {
		return this.method.getParameterTypes();
	}


	/**
	 * データの自動変換を無効化している場合
	 * ({@link ExternalFunctionConnectorInterface1#isDataConversionNecessary()} が false を返す場合)
	 * において、各仮引数のデータのやり取りに使用するデータコンテナの型を表す、Class配列を取得します。
	 *
	 * ただし、この実装では上記メソッドは true を返すため、このメソッドの戻り値は参照されません。
	 *
	 * @return 各仮引数のデータのやり取りに使用するデータコンテナの型を表すClass配列
	 */
	@Override
	public Class<?>[] getParameterUnconvertedClasses() {
		return null;
	}


	/**
	 * 全ての仮引数において、データ型が可変であるかどうかを格納する配列を返します。
	 *
	 * @return 各仮引数のデータ型が可変であるかどうかを格納する配列
	 */
	@Override
	public boolean[] getParameterDataTypeArbitrarinesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}


	/**
	 * 全ての仮引数において、配列次元数が可変であるかどうかを格納する配列を返します。
	 *
	 * @return 各仮引数の配列次元数が可変であるかどうかを格納する配列
	 */
	@Override
	public boolean[] getParameterArrayRankArbitrarinesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}


	/**
	 * 全ての仮引数において、参照渡しであるかどうかを格納する配列を返します。
	 *
	 * @return 各仮引数が参照渡しであるかどうかを格納する配列
	 */
	@Override
	public boolean[] getParameterReferencenesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}


	/**
	 * 全ての仮引数において、定数であるかどうかを格納する配列を返します。
	 *
	 * @return 各仮引数が定数であるかどうかを格納する配列
	 */
	@Override
	public boolean[] getParameterConstantnesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}


	/**
	 * 仮引数の個数が任意であるかどうかを返します。
	 *
	 * @return 仮引数の個数が任意であるかどうか
	 */
	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}


	/**
	 * 可変長引数であるかどうかを判定します。
	 *
	 * @return 可変長引数であればtrue
	 */
	@Override
	public boolean hasVariadicParameters() {
		return this.method.isVarArgs();
	}


	/**
	 * 戻り値のデータ型と配列次元数をまとめて表すClassインスタンスを取得します。
	 *
	 * @param parameterClasses 全引数のデータ型のClassインスタンスを格納する配列
	 * @return データ型のClassインスタンス
	 */
	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return this.method.getReturnType();
	}


	/**
	 * データの自動変換を無効化している場合
	 * ({@link ExternalFunctionConnectorInterface1#isDataConversionNecessary()} が false を返す場合)
	 * において、戻り値のやり取りに使用するデータコンテナの型を表すClassインスタンスを取得します。
	 *
	 * ただし、この実装では上記メソッドは true を返すため、このメソッドの戻り値は参照されません。
	 *
	 * @param parameterClasses 全引数のデータ型のClassインスタンスを格納する配列
	 * @return 戻り値のやり取りに使用するデータコンテナの型を表すClassインスタンス
	 */
	@Override
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return null;
	}


	/**
	 * 戻り値のデータ型が可変（プラグイン接続時点で確定していない）であるかどうかを取得します。
	 *
	 * @return 戻り値のデータ型が任意（プラグイン接続時点で確定していない）であれば true
	 */
	@Override
	public boolean isReturnDataTypeArbitrary() {
		return false;
	}


	/**
	 * 戻り値の配列次元数が可変（プラグイン接続時点で確定していない）であるかどうかを取得します。
	 *
	 * @return 戻り値の配列次元数が任意（プラグイン接続時点で確定していない）であれば true
	 */
	@Override
	public boolean isReturnArrayRankArbitrary() {
		return false;
	}


	/**
	 * データの自動変換が必要かどうかを返します。
	 * このアダプタではデータ変換が必須であるため、常にtrueを返します。
	 *
	 * @return 常にtrue
	 */
	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}


	/**
	 * 関数を実行します。
	 *
	 * @param arguments 全ての実引数を格納する配列
	 */
	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {
		try {
			return this.method.invoke(objectInstance, arguments);

		// アクセス修飾子などが原因で呼び出せない場合
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ConnectorException(
					objectInstance.getClass().getCanonicalName() + " class has no method named \"" + this.method.getName()
					+ "\" with expected parameters.",
					illegalArgumentException
			);

		// そもそもインスタンスが対象メソッドを持っていない場合
		} catch (IllegalAccessException illegalAccessException) {
			throw new ConnectorException(
					"The method \"" + this.method.getName() + "\" of " + objectInstance.getClass().getCanonicalName()
					+ " class is not accessable (probably it is private or protected).",
					illegalAccessException
			);

		// 呼び出し対象のメソッドが、実行中に内部から例外をスローしてきた場合
		} catch (InvocationTargetException invocationTargetException) {
			throw new ConnectorException(invocationTargetException);
		}
	}


	/**
	 * 処理系への接続時に必要な初期化処理を行います。
	 *
	 * 引数には、スクリプトエンジンに依存するやり取りを行うためのオブジェクトが渡されます。
	 * このオブジェクトは、恐らく {@link EngineConnectorInterface1 EngineConnectorInterface1}
	 * もしくはその後継の、抽象化されたインターフェースでラップされた形で渡されます。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 * @throws ConnectorException 初期化処理に失敗した場合にスローされます。
	 */
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException {
	}


	/**
	 * 処理系からの接続解除時に必要な終了時処理を行います。
	 *
	 * 引数には、スクリプトエンジンに依存するやり取りを行うためのオブジェクトが渡されます。
	 * このオブジェクトは、恐らく {@link EngineConnectorInterface1 EngineConnectorInterface1}
	 * もしくはその後継の、抽象化されたインターフェースでラップされた形で渡されます。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 * @throws ConnectorException 終了時処理に失敗した場合にスローされます。
	 */
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}


	/**
	 * スクリプト実行毎の初期化処理を行います。
	 *
	 * 引数には、スクリプトエンジンに依存するやり取りを行うためのオブジェクトが渡されます。
	 * このオブジェクトは、恐らく {@link EngineConnectorInterface1 EngineConnectorInterface1}
	 * もしくはその後継の、抽象化されたインターフェースでラップされた形で渡されます。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 * @throws ConnectorException 初期化処理に失敗した場合にスローされます。
	 */
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
	}


	/**
	 * スクリプト実行毎の終了時処理を行います。
	 *
	 * 引数には、スクリプトエンジンに依存するやり取りを行うためのオブジェクトが渡されます。
	 * このオブジェクトは、恐らく {@link EngineConnectorInterface1 EngineConnectorInterface1}
	 * もしくはその後継の、抽象化されたインターフェースでラップされた形で渡されます。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 * @throws ConnectorException 終了時処理に失敗した場合にスローされます。
	 */
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException {
	}
}
