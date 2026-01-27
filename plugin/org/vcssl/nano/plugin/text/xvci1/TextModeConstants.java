package org.vcssl.nano.plugin.text.xvci1;

public class TextModeConstants {

	/** 文字列と一致する、全ての部分を探す検索モードです。 */
	public static final long ALL = 101;

	/** 正規表現と一致する、全ての部分を探す検索モードです。 */
	public static final long ALL_PATTERN = 102;

	/** 文字列と一致する、最初の部分を探す検索モードです。 */
	public static final long FIRST = 201;

	/** 正規表現と一致する、最初の部分を探す検索モードです。 */
	public static final long FIRST_PATTERN = 202;

	/** 文字列と一致する、最後の部分を探す検索モードです。 */
	public static final long LAST = 301;

	/** 正規表現と一致する、最後の部分を探す検索モードです。 */
	public static final long LAST_PATTERN = 302;


	/** 文字列の先頭が、部分文字列と一致するかを判断する判定モードです。 */
	public static final long START = 10001;

	/** 文字列の先頭が、正規表現と一致するかを判断する判定モードです。 */
	public static final long START_PATTERN = 10002;

	/** 文字列の終端が、部分文字列と一致するかを判断する判定モードです。 */
	public static final long END = 10003;

	/** 文字列の終端が、正規表現と一致するかを判断する判定モードです。 */
	public static final long END_PATTERN = 10004;

	/** 文字列のどこかに、部分文字列と一致する部分が存在するかを判断する判定モードです。 */
	public static final long CONTAIN = 10005;

	/** 文字列のどこかに、正規表現と一致する部分が存在するかを判断する判定モードです。 */
	public static final long CONTAIN_PATTERN = 10006;

	/** 文字列の全体が、正規表現と一致するかを判断する判定モードです。 */
	public static final long FULL_PATTERN = 10007;


	/** 前後の空白・改行を切り詰める、adjustText 関数のモードです。 */
	public static final long TRIM = 20001;

	/** 小文字のアルファベットを大文字にする、adjustText 関数のモードです。 */
	public static final long LOWER_CASE = 20002;

	/** 大文字のアルファベットを小文字にする、adjustText 関数のモードです。 */
	public static final long UPPER_CASE = 20003;
}
