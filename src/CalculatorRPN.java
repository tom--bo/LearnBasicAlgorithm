import java.io.*;
import java.util.*;

public class CalculatorRPN {
	private final MyStack stack;
	
	public CalculatorRPN()
	{
		stack = new MyStack();
	}
	
	public long compute(String exp) throws IOException
	{
		stack.clear();
		
		PushbackReader input = new PushbackReader(new StringReader(exp + ";"));
		int c;
		while((c = input.read()) != ';') {
			char ch = (char)c;
			
			if(Character.isDigit(ch)) {
				long num = 0;
				while(Character.isDigit(ch)){
					num = 10 * num + (ch - '0');
					c = input.read();
					ch = (char)c;
				}
				input.unread(c);
				stack.push(num);
			} else {
				long a,b;
				switch(ch) {
				case '+': 
					b = (Long)stack.pop();
					a = (Long)stack.pop(); 
					stack.push(a+b);
					break;
				case '-': 
					b = (Long)stack.pop();
					a = (Long)stack.pop(); 
					stack.push(a-b);
					break;
				case '*': 
					b = (Long)stack.pop();
					a = (Long)stack.pop(); 
					stack.push(a*b);
					break;
				case '/': 
					b = (Long)stack.pop();
					a = (Long)stack.pop(); 
					stack.push(a/b);
					break;
				case ' ':
				case '\t':
				case '\r':
					break;
				default:
					throw new IllegalArgumentException("不正な文字がありました");
				}
			}
		}
        if (!stack.isEmpty()) {
            return (Long)stack.pop();
        } else {
            throw new IllegalArgumentException("式がありません");
        }
	}
}
