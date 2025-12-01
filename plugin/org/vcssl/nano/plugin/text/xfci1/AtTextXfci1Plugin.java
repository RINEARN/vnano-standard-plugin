/*
 * Author:  RINEARN (Fumihiro Matsui), 2025
 * License: CC0
 */

package org.vcssl.nano.plugin.text.xfci1;

import java.util.Locale;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.EngineConnectorInterface1;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

// Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html
// インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html

public class AtTextXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	protected Locale locale = null;
	protected boolean isJapanese = false;

	// 初期化/終了時処理の引数に渡される、スクリプトエンジンと情報をやり取りするインターフェースの指定
	@Override
	public Class<?> getEngineConnectorClass() {
		return EngineConnectorInterface1.class;
	}

	// 接続時の初期化
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }

	// スクリプト実行前の初期化
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {

		// 処理系の情報を取得するコネクタ（処理系依存）の互換性を検査
		if (!(engineConnector instanceof EngineConnectorInterface1)) {
			throw new ConnectorException(
				"The type of the engine connector \"" +
				engineConnector.getClass().getCanonicalName() +
				"\" is not supported by this plug-in."
			);
		}
		EngineConnectorInterface1 eci1Connector = (EngineConnectorInterface1)engineConnector;

		// 言語ロケール情報を取得（エラーメッセージの言語を変えるため）
		if (eci1Connector.hasOptionValue("LOCALE")) {
			this.locale = (Locale)eci1Connector.getOptionValue("LOCALE");
		} else {
			this.locale = Locale.getDefault();
		}
		if (    ( this.locale.getLanguage()!=null && this.locale.getLanguage().equals("ja") )
		     || ( this.locale.getCountry()!=null && this.locale.getCountry().equals("JP")   )   ) {
			this.isJapanese = true;
		}
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
		return "atText";
	}

	// string型と1nt型の引数を一個ずつ取るので以下を返す
	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { String.class, long.class };
	}

	// データの自動変換を有効化しているので参照されない
	@Override
	public Class<?>[] getParameterUnconvertedClasses() {
		return new Class<?>[0];
	}

	// 引数名が定義されているので true を返す
	@Override
	public boolean hasParameterNames() {
		return true;
	}

	// 引数名を返す
	@Override
	public String[] getParameterNames() {
		return new String[] { "text", "index" };
	}

	// 任意型の引数は取らないので false を返す
	@Override
	public boolean[] getParameterDataTypeArbitrarinesses() {
		return new boolean[]{ false };
	}

	// 任意次元の引数は取らないので false を返す
	@Override
	public boolean[] getParameterArrayRankArbitrarinesses() {
		return new boolean[]{ false };
	}

	// 引数が参照渡しされる必要はないので false を返す
	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ false };
	}

	// 引数の中身を書き変えないので true を返す（参照渡しの場合はそう宣言しないと、リテラル等を引数に取れない上に、最適化でも少し不利になる）
	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true };
	}

	// 任意個の引数は取らないので false を返す
	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	// 可変長引数は上記の任意個引数とは少し仕様が異なり、このプラグインでは使わない（Vnanoでは未対応）ので false を返す
	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	// 戻り値は string 型なので以下を返す
	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return String.class;
	}

	// データの自動変換を有効化しているので参照されない
	@Override
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return null;
	}

	// 戻り値のデータ型は固定なので false
	@Override
	public boolean isReturnDataTypeArbitrary() {
		return false;
	}

	// 戻り値の配列次元数は固定なので false
	@Override
	public boolean isReturnArrayRankArbitrary() {
		return false;
	}

	// データ型を自動変換してほしいので true を返す
	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}

	// スクリプトから呼ばれた際に実行する処理
	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {

		if (arguments.length != 2 || !(arguments[0] instanceof String) || !(arguments[1] instanceof Long)) {
			throw new ConnectorException("The number or types of arguments is/are unexpected");
		}
		String text = (String)arguments[0]; // 引数: 文字抽出対象のテキスト
		long index = (long)arguments[1];    // 引数: 文字抽出位置のインデックス
		String result = null;              // 戻り値: 抽出した文字

		// インデックスの範囲検査
		if (index < 0) {
			String errorMessage = this.isJapanese ?
				"抽出する文字インデックスは、 0 以上の値である必要がありますが、負の値が指定されています: " + index :
				"The character index to be extracted must be zero or positive, but an negative value is specified: " + index;
			throw new ConnectorException(errorMessage);
		}
		if (text.length() - 1 < index) {
			String errorMessage = this.isJapanese ?
				"抽出する文字インデックスは、 文字列の長さ (" + text.length() + ") - 1 以内である必要がありますが、超過しています: " + index :
				"The character index to be extracted must smaller than or equals to the length of the text (" + text.length() + ") - 1, but the specified value exceeds it: " + index;
			throw new ConnectorException(errorMessage);
		}

		try {
			int intIndex = (int)index;
			char charResult = text.charAt(intIndex);
			result = Character.toString(charResult);
		} catch (Exception e) {
			throw new ConnectorException(e);
		}

		return result;
	}
}
