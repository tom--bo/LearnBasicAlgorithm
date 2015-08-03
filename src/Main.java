import java.io.*;
import java.util.EmptyStackException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 逆ポーランド記法電卓
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
