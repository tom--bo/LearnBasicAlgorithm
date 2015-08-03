
public class Main {

	public static void main(String[] args) {

		// MyStack テスト
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
