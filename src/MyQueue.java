import java.util.*;

public class MyQueue {
	private Object queue[];
	private int queueSize;
	private int front;
	private int rear;
	
	private static final int DEFAULT_QUEUE_SIZE = 100;
	
	public MyQueue()
	{
		this(DEFAULT_QUEUE_SIZE);
	}
	
	public MyQueue(int size)
	{
		queueSize = size;
		queue = new Object[size];
		front = rear = 0;
	}
	
	private int next(int a)
	{
		return (a + 1) % queueSize;
	}
	
	public void clear()
	{
		front = rear = 0;
		Arrays.fill(queue, 0, queueSize, null);
	}

	public void enqueue(Object x)
	{
		if(next(rear) == front) {
			throw new IllegalStateException("これ以上要素を追加できません");
		}
		queue[rear] = x;
		rear = next(rear);
	}
	
	public Object dequeue()
	{
		if (front == rear) {
			throw new NoSuchElementException("待ち行列が空なので、要素を取り出せません");
		}
		Object x = queue[front];
		queue[front] = null;
		front = next(front);
		return x;
	}
	
	public boolean isEmpty()
	{
		return front == rear;
	}

	public String toString()
	{
		String s = "MyQueue=[";
		for (int i = front; i != rear; i = next(i)) {
			s += queue[i] + " ";
		}
		s += "] front=" + front + " rear=" + rear;
		return s;
	}
}





