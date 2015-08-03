import java.util.*;

public class MyLinkedListIterator implements Iterator {
	private Cell p;
	
	public MyLinkedListIterator(MyLinkedList list)
	{
		p = list.header;
	}

	public boolean hasNext() {
		return p.next != null;
	}

	public Object next() {
		if (p.next == null) {
			throw new NoSuchElementException();
		}
		
		p = p.next;
		return p.data;
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

}
