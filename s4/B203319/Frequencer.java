package s4.B203319;
import java.lang.*;
import s4.specification.*;

/*package s4.specification;
ここは、1回、2回と変更のない外部仕様である。
public interface FrequencerInterface { // This interface provides the design for
	frequency counter.
	void setTarget(byte target[]); // set the data to search.
	void setSpace(byte space[]); // set the data to be searched target from.
	int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
	//Otherwise, it return 0, when SPACE is not set or SPACE's length is zero
	//Otherwise, get the frequency of TAGET in SPACE
	int subByteFrequency(int start, int end);
	// get the frequency of subByte of taget, i.e target[start], taget[start+1], ... ,
	target[end-1].
	// For the incorrect value of START or END, the behavior is undefined.
}
*/

public class Frequencer implements FrequencerInterface{
	// Code to start with: This code is not working, but good start point to work.
	byte [] myTarget;
	byte [] mySpace;

	boolean targetReady = false;
	boolean spaceReady = false;
	int [] suffixArray; // Suffix Array の実装に使うデータの型を int []とせよ。

	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a integer, which is the starting position in mySpace.
	// The following is the code to print the contents of suffixArray.
	// This code could be used on debugging.
	private void printSuffixArray() {
		if(spaceReady) {
			for(int i=0; i< mySpace.length; i++) {
				int s = suffixArray[i];
				for(int j=s;j<mySpace.length;j++) {
					System.out.write(mySpace[j]);
				}
				System.out.write('\n');
			}
		}
	}

	private int suffixCompare(int i, int j) {
		// suffixCompare はソートのための比較メソッドである。
		// 次のように定義せよ。
		// comparing two suffixes by dictionary order.
		// suffix_i is a string starting with the position i in "byte [] mySpace".
		// Each i and j denote suffix_i, and suffix_j.
		// Example of dictionary order
		// "i" < "o" : compare by code
		// "Hi" < "Ho" ; if head is same, compare the next element
		// "Ho" < "Ho " ; if the prefix is identical, longer string is big
		//

		//The return value of "int suffixCompare" is as follows.
		// if suffix_i > suffix_j, it returns 1
		// if suffix_i < suffix_j, it returns -1
		// if suffix_i = suffix_j, it returns 0;

		while(i < mySpace.length && j < mySpace.length){
			if(mySpace[i] == mySpace[j]){
				i++;j++;
			}else if (mySpace[i] >mySpace[j]){
				return 1;
			}else{
				return -1;
			}
		}
		if (i==j){
			return 0;
		}else if (i == mySpace.length){
			return -1;
		} else {
			return 1;
		}
	}

	public void quickSort(int top,int btm){
		int pvt = top;	//ピボット
		int i 	= top;
		for (int j = top+1; j<=btm;j++){
			if(suffixCompare(suffixArray[j], suffixArray[pvt])==-1){
				i++;
				if(j != i){
					int tmp1 = suffixArray[i];
					suffixArray[i] = suffixArray[j];
					suffixArray[j] = tmp1;
				}
			}
		}
		int tmp2 = suffixArray[pvt];
		suffixArray[pvt] = suffixArray[i];
		suffixArray[i] = tmp2;
		//再帰処理
		if(top < i-1) quickSort(top,i-1);//左側
		if(i+1 < btm) quickSort(i+1,btm);//右側
	}

	public void setSpace(byte []space) {
		// suffixArray の前処理は、setSpace で定義せよ。
		mySpace = space; if(mySpace.length>0) spaceReady = true;
		// First, create unsorted suffix array.
		suffixArray = new int[space.length];

		// put all suffixes in suffixArray.
		for(int i = 0; i< space.length; i++) {
			suffixArray[i] = i; // Please note that each suffix is expressed by one	integer.
		}
		//
		// ここに、int suffixArray をソートするコードを書け。
		// 順番は suffixCompare で定義されるものとする。
		//クイックソート
		if (space.length >1){quickSort(0,space.length-1);}

	}

	// Suffix Array を用いて、文字列の頻度を求めるコード
	// ここから、指定する範囲のコードは変更してはならない。
	public void setTarget(byte [] target) {
		myTarget = target; if(myTarget.length>0) targetReady = true;
	}
	public int frequency() {
		if(targetReady == false) return -1;
		if(spaceReady == false) return 0;
		return subByteFrequency(0, myTarget.length);
	}

	public int subByteFrequency(int start, int end) {
		/* This method be work as follows, but much more efficient
		int spaceLength = mySpace.length;
		int count = 0;
		for(int offset = 0; offset< spaceLength - (end - start); offset++) {
			boolean abort = false;
			for(int i = 0; i< (end - start); i++) {
				if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
			}
			if(abort == false) { count++; }
		}
		*/
		int first = subByteStartIndex(start, end);
		int last = subByteEndIndex(start, end);
		return last - first;
	}

	// 変更してはいけないコードはここまで。

	private int targetCompare(int i, int j, int k) {
		// suffixArray を探索するときに使う比較関数。
		// 次のように定義せよ
		// suffix_i is a string in mySpace starting at i-th position.
		// target_i_k is a string in myTarget start at j-th postion ending k-th position.
		// comparing suffix_i and target_j_k.
		// if the beginning of suffix_i matches target_i_k, it return 0.
		// The behavior is different from suffixCompare on this case.
		// if suffix_i > target_i_k it return 1;
		// if suffix_i < target_i_k it return -1
		// It should be used to search the appropriate index of some suffix.

		// Example of search
		// suffix target
		// "o" > "i"
		// "o" < "z"
		// "o" = "o"
		// "o" < "oo"
		// "Ho" > "Hi"
		// "Ho" < "Hz"
		// "Ho" = "Ho"
		// "Ho" < "Ho " : "Ho " is not in the head of suffix "Ho"
		// "Ho" = "H" : "H" is in the head of suffix "Ho"

		while(i < mySpace.length && j < k){
			if(mySpace[i] == myTarget[j]){
				i++;j++;
			}else if (mySpace[i] > myTarget[j]){
				return 1;
			}else{
				return -1;
			}
		}
		if (j == k){
			return 0;
		}
		//kよりjが小さくて，iが最後に到達した場合
		return -1;

	}


	private int subByteStartIndex(int start, int end) {
		//suffix array のなかで、目的の文字列の出現が始まる位置を求めるメソッド
		// 以下のように定義せよ。
		/* Example of suffix created from "Hi Ho Hi Ho"
		0: Hi Ho
		1: Ho
		2: Ho Hi Ho
		3:Hi Ho
		4:Hi Ho Hi Ho
		5:Ho
		6:Ho Hi Ho
		7:i Ho
		8:i Ho Hi Ho
		9:o
		A:o Hi Ho
		*/
		// It returns the index of the first suffix
		// which is equal or greater than target_start_end.
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho", it will return 5.
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho ", it will return 6.
		//
		// ここにコードを記述せよ。
		//2分検索
		int top = 0;
		int btm = suffixArray.length-1;
		if(targetCompare(suffixArray[top], start, end)==0){
			return top;
		}
		while(top+1 < btm){
			int i = (btm+top)/2;
			if (targetCompare(suffixArray[i], start, end)>=0){
				btm = i;
			}else if (targetCompare(suffixArray[i], start, end)<0){
				top = i;
			}
		}
		return btm;

		/*
		//単純検索
		for (int i=0;i<suffixArray.length;i++){
			if(targetCompare(suffixArray[i], start, end)==0) {
				return i;
			}
		}
		return -1;
		*/
		//
		//return suffixArray.length; //このコードは変更しなければならない。
	}

	private int subByteEndIndex(int start, int end) {
		//suffix array のなかで、目的の文字列の出現しなくなる場所を求めるメソッド
		// 以下のように定義せよ。
		/* Example of suffix created from "Hi Ho Hi Ho"
		0: Hi Ho
		1: Ho
		2: Ho Hi Ho
		3:Hi Ho
		4:Hi Ho Hi Ho
		5:Ho
		6:Ho Hi Ho
		7:i Ho
		8:i Ho Hi Ho
		9:o
		A:o Hi Ho
		*/
		// It returns the index of the first suffix
		// which is greater than target_start_end; (and not equal to target_start_end)
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho", it will return 7 for "Hi Ho Hi Ho".

		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is"i", it will return 9 for "Hi Ho Hi Ho".
		//
		// ここにコードを記述せよ

		//2分検索
		int top = 0;
		int btm = suffixArray.length-1;
		if(targetCompare(suffixArray[btm], start, end)==0){
			return btm+1;
		}
		while(top+1 < btm){
			int i = (btm+top)/2;
			if (targetCompare(suffixArray[i], start, end)>0){
				btm = i;
			}else if (targetCompare(suffixArray[i], start, end)<=0){
				top = i;
			}
		}

		return btm;

		/*
		for (int i=suffixArray.length-1;i >= 0;i--){
			if(targetCompare(suffixArray[i], start, end)==0) {
				return i+1;
			}
		}
		return -1;
		*/
		//
		//return suffixArray.length; // この行は変更しなければならない、
	}
	// Suffix Array を使ったプログラムのホワイトテストは、
	// private なメソッドとフィールドをアクセスすることが必要なので、
	// クラスに属する static main に書く方法もある。
	// static main があっても、呼びださなければよい。
	// 以下は、自由に変更して実験すること。
	// 注意:標準出力、エラー出力にメッセージを出すことは、
	// static main からの実行のときだけに許される。
	// 外部から Frequencer を使うときにメッセージを出力してはならない。
	// 教員のテスト実行のときにメッセージがでると、仕様にない動作をするとみなし、
	// 減点の対象である。
	public static void main(String[] args) {
		Frequencer frequencerObject;
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
			frequencerObject.printSuffixArray(); // you may use this line for DEBUG
			/* Example from "Hi Ho Hi Ho"
			0:  Hi Ho
			1:  Ho
			2:  Ho Hi Ho
			3: Hi Ho
			4: Hi Ho Hi Ho
			5: Ho
			6: Ho Hi Ho
			7: i Ho
			8: i Ho Hi Ho
			9: o
			A: o Hi Ho
			*/
			frequencerObject.setTarget("H".getBytes());
			// you may use this line for DEBUG
			//
			// **** Please write code to check subByteStartIndex, and subByteEndIndex
			//
			//int i = frequencerObject.subByteStartIndex(0,2);
			//frequencerObject.subByteEndIndex(0,2);
			//System.out.println(i);

			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");
			if(4 == result) { System.out.println("OK"); }
			else			{ System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println("STOP");
			System.out.println(e);
		}
	}
}
