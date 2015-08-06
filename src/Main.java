import java.io.*;
import java.util.*;
import java.util.EmptyStackException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// mergesort テスト
		int[] arr = {23, 1, 55, 22, 12, 49, 78, 88, 34, 27, 65};
		MergeSortArray.sort(arr);
		for(int a: arr) {
			System.out.println(a);
		}
		
		// quicksort テスト
		/*
		int[] arr = {23, 1, 55, 22, 12, 49, 78, 88, 34, 27, 65};
		// Quicksort1.sort(arr);
		Quicksort2.sort(arr);
		for(int a: arr) {
			System.out.println(a);
		}
		*/
		
		// BTree　テスト
		/*
		 * テスト用のコマンド
		 *  +n		: nを挿入する
		 *  -n		: nを削除する
		 *  /n 		: nを探索する
		 *  =string	: 直前に成功した/コマンドで発見した要素をstringにする
		 *  p		: B木の内容を表示する
		 *  q		: 終了する
		 */
		/*
		BTree tree = new BTree();
		int[] data = {1, 100, 27, 45, 3, 135, 13 };
		for (int x: data ){
			tree.insert(x, "["+x+"]");
		}
		System.out.print(">");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		while((str = input.readLine()) != null){
			if(str.length() == 0) {
                System.out.print(">");
                continue;
			}
			char command = str.charAt(0);
			
			String arg = str.substring(1).trim();
			if(command == 'q') {
				break;
			} else if (command == 'p') {
				System.out.println(tree);
			} else if (command == '=') {
				if(tree.setData(arg)) {
					System.out.println("値" + arg + "の設定に成功しました");;
				} else {
					System.out.println("値" + arg + "の設定に失敗しました");;
				}
			} else if (command == '+' || command == '-' || command == '/') {
				int num = 0;
				try {
					num = Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					System.err.println("整数以外の者が指定されました");
					continue;
				}
				
				if(command == '+') {
					if(tree.insert(num,  "[" + num + "]")) {
						System.out.println(num + "の挿入に成功しました");
					} else{
						System.out.println(num + "の挿入に失敗しました");
					}
				} else if (command == '-') {
					if(tree.search(num)) {
						System.out.println(num + "が見つかりました。値=" + tree.getData());
					} else {
						System.out.println(num + "が見つかりませんでした");
					}
				}
			} else {
				System.out.println("コマンド" + command + "はサポートしていません");
			}
			System.out.print(">");
		}
		*/
		
		// BinaryTreeNode テスト
		/*
		BinaryTreeNode tree = new BinaryTreeNode(
				"a",
				new BinaryTreeNode(
                        "b",
						new BinaryTreeNode("c", null, null),
						null
				),
				new BinaryTreeNode("d", null, null)
        );
		
		System.out.println("行きがけ順");
		tree.traversePreorder(tree);

		System.out.println("通りがけ順");
		tree.traverseInorder(tree);
				
		System.out.println("帰りがけ順");
		tree.traversePostorder(tree);
		*/
		
		// MyDoubleLinkedList テスト
		/*
		MyDoublyLinkedList list = new MyDoublyLinkedList();
		
		System.out.println("初期状態（空リスト");
		System.out.println(list);
		list.insertFirst("a");
		list.insertLast("b");
		list.insertFirst("c");
		list.insertFirst("d");
		list.insertLast("e");
		System.out.println(list);
		*/
		
		// MyLinkedList テスト
		/*
		MyLinkedList list = new MyLinkedList();
		list.insert(20);
		list.insert(15);
		list.insert(18);
		list.insert(37);
		list.insert(3);
		System.out.println(list);
		
		System.out.println("---< イテレータ >----");
		Iterator iter = list.iterator();
		int count = 1;
		while (iter.hasNext()) {
			System.out.println(count++ + "番目の要素： " + iter.next());
		}
		
		System.out.println("---< 拡張for文 >----");
		count = 1;
		for (Object o: list) {
			System.out.println(count++ + "番目の要素： " + o);
		}
		*/
		
		// MyLinkedList テスト
		/*
		MyLinkedList list = new MyLinkedList();
		
		System.out.println(list);
		list.insert(5);
		list.insert(7);
		list.insert(2);
		list.insert(12);
		list.insert(4);
		System.out.println(list);
		*/
		
		// MyQueue テスト
		/*
		MyQueue q = new MyQueue();

		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		System.out.println(q);
		System.out.println(q.dequeue() + "を取り出した");
		System.out.println(q.dequeue() + "を取り出した");
		System.out.println(q);

		q.enqueue("d");
		System.out.println(q.dequeue() + "を取り出した");
		System.out.println(q.dequeue() + "を取り出した");
		System.out.println(q);
		*/
		
		// 逆ポーランド記法電卓
		/*
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		CalculatorRPN calculator = new CalculatorRPN();
		
		String line;
		while((line = input.readLine()) != null){
			try {
				long answer = calculator.compute(line);
				System.out.println("値は" + answer + "です。");
			} catch (EmptyStackException e) {
				System.out.println("式が正しくありません。");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		*/

		// MyStack テスト
		/*
		MyStack stack = new MyStack();
		
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack);
		System.out.println("pop:" + stack.pop());
		
		stack.push("d");
		stack.push("e");
		stack.push("f");
		System.out.println(stack);
		while(!stack.isEmpty()){
			System.out.println("pop:" + stack.pop());
		}
		System.out.println("DONE! " + stack);
		*/
		
		// 線形探索
		/*
		LinearSearch table = new LinearSearch();
		table.add(1, "one");
		table.add(2, "two");
		table.add(5, "five");
		
		String x;
		x = (String)table.search(5);
		if(x != null){
			System.out.println("value = " + x);
		} else {
			System.out.println("Not found!");
		}
		*/
	}

}
