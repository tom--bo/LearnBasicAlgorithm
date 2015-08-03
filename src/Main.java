
public class Main {

	public static void main(String[] args) {

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
		
		
	}

}
