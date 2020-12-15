<<<<<<< HEAD
all:
	javac Hello.java -classpath "../.."
	javac Frequencer.java -classpath "../.."
	javac InformationEstimator.java -classpath "../.."
	javac TestCase.java -classpath "../.."

# 利用する場合は s4.学籍番号/プログラム名 になるように書き換える
run:
	java -classpath "../.." s4.B203313/Hello
	java -classpath "../.." s4.B203313/Frequencer
#	java -classpath "../.." s4.B203313/InformationEstimator
#	java -classpath "../.." s4.B203313/TestCase

# runやrunHelloなどがファイル名ではなくターゲット名であることをmakeに伝えるための定義
.PNOHY: run runHello runFrequencer runInformationEstimator runTestCase

# コンパイルしてある.classを使ってコマンドを実行するための定義
# 使い方： "make runFrequencer", "make runTestCase" など
# 実行するときに対応する.classファイルがない場合は、
# 下に書いてある「*.classをコンパイルするための定義」を使って
# コンパイルが実行された後にプログラムを実行する。
runHello: Hello.class
	java -classpath "../.." s4.B203313/Hello

runFrequencer: Frequencer.class
	java -classpath "../.." s4.B203313/Frequencer

runInformationEstimator: InformationEstimator.class
	java -classpath "../.." s4.B203313/InformationEstimator

runTestCase: TestCase.class
	java -classpath "../.." s4.B203313/TestCase


# *.classをコンパイルするための定義
Hello.class: Hello.java
	javac -classpath "../.." Hello.java

Frequencer.class: Frequencer.java
	javac -classpath "../.." Frequencer.java

InformationEstimator.class: InformationEstimator.java
	javac -classpath "../.." InformationEstimator.java

TestCase.class: TestCase.java
	javac -classpath "../.." TestCase.java
=======
all:
	javac Hello.java -classpath "../.."
	javac Frequencer.java -classpath "../.."
	javac InformationEstimator.java -classpath "../.."
	javac TestCase.java -classpath "../.."

# 利用する場合は s4.学籍番号/プログラム名 になるように書き換える
run:
	java -classpath "../.." s4.B203313/Hello
#	java -classpath "../.." s4.B203313/Frequencer
#	java -classpath "../.." s4.B203313/InformationEstimator
#	java -classpath "../.." s4.B203313/TestCase

# runやrunHelloなどがファイル名ではなくターゲット名であることをmakeに伝えるための定義
.PNOHY: run runHello runFrequencer runInformationEstimator runTestCase

# コンパイルしてある.classを使ってコマンドを実行するための定義
# 使い方： "make runFrequencer", "make runTestCase" など
# 実行するときに対応する.classファイルがない場合は、
# 下に書いてある「*.classをコンパイルするための定義」を使って
# コンパイルが実行された後にプログラムを実行する。
runHello: Hello.class
	java -classpath "../.." s4.B203313/Hello

runFrequencer: Frequencer.class
	java -classpath "../.." s4.B203313/Frequencer

runInformationEstimator: InformationEstimator.class
	java -classpath "../.." s4.B203313/InformationEstimator

runTestCase: TestCase.class
	java -classpath "../.." s4.B203313/TestCase


# *.classをコンパイルするための定義
Hello.class: Hello.java
	javac -classpath "../.." Hello.java

Frequencer.class: Frequencer.java
	javac -classpath "../.." Frequencer.java

InformationEstimator.class: InformationEstimator.java
	javac -classpath "../.." InformationEstimator.java

TestCase.class: TestCase.java
	javac -classpath "../.." TestCase.java
>>>>>>> 06b87de5f67c65731e290e8fd86241cbb4c0ffd2
