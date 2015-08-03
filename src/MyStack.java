import java.util.*;

public class MyStack {
	private Object stack[];
	private int stackSize;
	private int sp;
	
	private static final int DEFAULT_STACK_SIZE = 100;
	
	public MyStack()
	{
		this(DEFAULT_STACK_SIZE);
	}
	
	public MyStack(int size)
	{
		stack = new Object[size];
		stackSize = size;
		sp = 0;
	}
	
	public void clear()
	{
		Arrays.fill(stack, 0, sp, null);
		sp = 0;
	}
	
	public void push(Object x)
	{
		if (sp >= stackSize) {
			throw new IllegalStateException("Stack overflow");
		}
		stack[sp++] = x;
	}
	
	public Object pop()
	{
		if(sp <= 0) {
			throw new EmptyStackException();
		}
        Object value = stack[--sp];
        stack[sp] = null;
        return value;
	}

	public boolean isEmpty()
	{
		return sp == 0;
	}

	public String toString()
	{
		String s = "MyStack=[";
		for(int i = 0; i< sp; i++) {
			s = s+ stack[i];
			if(i < sp - 1)
				s = s + ",";
		}
		s = s + "]";
		return s;
	}
	
}
