package ha1;

public class Aufgabe2 {

	/**
	 * Die Methode filtert eine Liste mit einem beliebigen Filter und gibt die entsprechenden Werte aus
	 * 
	 * @param liste Liste
	 * @param filter Filter
	 */
	public static void print(int[] liste, Filter filter) {
		for (int x : liste) 
			if (filter.evaluate(x)) System.out.print(x + " ");
		System.out.println();
	}
	
	/**
	 * Test case
	 */
	public static void main(String[] args) {
		int[] test = {5, 9, 2, 7, 2, 6, 7, 12, 5, 43, 4};
		print(test, new PrimeFilter());
		print(test, new TwoDigitsFilter());
		print(test, new UniqueFilter());
	}
}
