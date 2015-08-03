import java.util.Iterator;


public class MyLinkedList implements Iterable{
	final Cell header;
	
	public MyLinkedList()
	{
		header = new Cell("**List Head**");
	}
	
	public void insert(int num) 
	{
		Cell p = header.next;
		Cell q = header;
		while(p != null && num > (Integer)p.data) {
			q = p;
			p = p.next;
		}
		
		Cell newCell = new Cell(num);
		newCell.next = p;
		q.next = newCell;
	}
	
	public MyLinkedListIterator iterator()
	{
		return new MyLinkedListIterator(this);
	}
	
	public String toString()
	{
		String s = "[";
		for(Cell p = header.next; p != null; p = p.next) {
			s += p.data + " ";
		}
		s += "]";
		return s;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
