# Vnano Standard Plug-ins


## Abstract - 概要

This repository provides/manages plug-ins to extend build-in functions and variables available in script code written in the [Vnano](https://www.vcssl.org/en-us/vnano/). These plug-ins are available on applications equipped with the Script Engine of the Vnano.

このリポジトリは、[Vnano](https://www.vcssl.org/en-us/vnano/) で記述されたスクリプトコード内から利用可能な組み込み関数/変数を拡張するための、プラグインのセットを提供/管理します。
各プラグインは、Vnano のスクリプトエンジンを搭載したアプリケーションで利用できます。


## License - ライセンス

This package is released under [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed).

このパッケージは [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) で公開されています。


## How to Use - 使用方法


## Step-1. Build the Script Engine of the Vnano - Vnano のスクリプトエンジンのビルド

At first, get and build source code of the script engine of the Vnano: 

まず、Vnanoのスクリプトエンジンのソースコードを入手してビルドします：

    cd <working-directory>
    git clone https://github.com/RINEARN/vnano.git
    cd vnano
    build.bat  (For Microsoft(R) Windows(R))
    build.sh   (For Other OSes)

When above commands have been completed successfully, "Vnano.jar" will be generated in the current directory. This JAR file is necessary for compiling plug-ins.

上記の処理が正常に完了すると、「 Vnano.jar 」がカレントディレクトリ内に生成されます。プラグインのコンパイルの際に、このJARファイルが必要になります。


## Step-2. Clone this Directory and Put "Vnano.jar" - このリポジトリをクローンして「 Vnano.jar 」を配置する

Next, clone this repository:

続いて、このリポジトリをクローンします：

    cd <working-directory>
    git clone https://github.com/RINEARN/vnano-standard-plugin.git
    cd vnano-standard-plugin

Then put "Vnano.jar" generated by Step-1 in this directory "vnano-standard-plugin". 

このフォルダ「 vnano-standard-plugin 」内に、Step-1 で生成した「 Vnano.jar 」を配置してください.


## Step-3. Compile Plug-Ins - プラグインのコンパイル

Finally, compile souce files of plug-ins.
For Microsoft&reg; Windows&reg; :

最後に、プラグインのソースコードをコンパイルします。
Microsoft&reg; Windows&reg; をご使用の場合は：

    cd plugin
    javac -classpath ".;../Vnano.jar" -encoding UTF-8 @org/vcssl/nano/plugin/sourcelist.txt

For Other OSes (e.g. Linux&reg; ) :

その他のOS（ Linux&reg; など）をご使用の場合は：

    cd plugin
    javac -classpath ".:../Vnano.jar" -encoding UTF-8 @org/vcssl/nano/plugin/sourcelist.txt

Please note that separator-characters in contents of the "-classpaths" option ( ; or : ) are different between OSes.

ここで "-classpath" オプションの指定内容における区切り文字（ ; か : ）が、OSによって異なる事にご注意ください。

After the compilation will be completed successfully, classes of plug-ins will be generated in "plugin" directory. Copy them in the directory specified by the application you are using, 
and specify them in the setting file of the application for loading them.
For details, see the document of the application you are using.

コンパイルが正常に完了すると、プラグインのクラスが「 plugin 」フォルダ内に生成されます。それらを、ご使用のアプリケーションで指定されているフォルダ内にコピーしてください。また、ご使用のアプリケーションの設定ファイルなどから、それらを読み込むように指定してください。詳細については、ご使用のアプリケーションの説明書をご参照ください。


---

## Credits - 本文中の商標など

- Oracle and Java are registered trademarks of Oracle and/or its affiliates. 

- Microsoft Windows is either a registered trademarks or trademarks of Microsoft Corporation in the United States and/or other countries. 

- Linux is a trademark of linus torvalds in the United States and/or other countries. 

- Other names may be either a registered trademarks or trademarks of their respective owners. 

- OracleとJavaは、Oracle Corporation 及びその子会社、関連会社の米国及びその他の国における登録商標です。文中の社名、商品名等は各社の商標または登録商標である場合があります。

- Windows は、米国 Microsoft Corporation の米国およびその他の国における登録商標です。

- Linux は、Linus Torvalds 氏の米国およびその他の国における商標または登録商標です。 

- その他、文中に使用されている商標は、その商標を保持する各社の各国における商標または登録商標です。

