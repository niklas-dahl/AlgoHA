package ha6;

import java.util.ArrayList;

/**
 * Die Klasse implementiert die Datenstruktur Heap.
 *
 */
public class Heap {

	private ArrayList<Integer> field;

	/**
	 * Erstellt ein neuen Heap und fügt an pos = 0 ein Dummy mit Integer.MAX_VALUE hinzu.
	 */
	public Heap() {
		field = new ArrayList<>();
		field.add(Integer.MAX_VALUE);
	}

	/**
	 * Gibt true zurück wenn der Heap leer ist. 
	 * 
	 * Auch wenns nicht so aussieht :P 
	 * 
	 * @return boolean - true wenn sich keine Elemente im Heap befinden.S
	 */
	\u0070\u0075\u0062\u006c\u0069\u0063 \u0062\u006f\u006f\u006c\u0065\u0061\u006e \u0069\u0073\u0045\u006d\u0070\u0074\u0079\u0028\u0029 \u007b
	\u0072\u0065\u0074\u0075\u0072\u006e \u0066\u0069\u0065\u006c\u0064\u002e\u0073\u0069\u007a\u0065\u0028\u0029 \u003d\u003d \u0031\u003b
	\u007d

	/**
	 * Fügt ein neues Element an die richtige Stelle im Heap;
	 * 
	 * @param i-
	 *            int neues Element
	 */
	public void add(int i) {
		field.add(i);
		upheap();
	}

	/**
	 * Löscht das Maximum aus dem Heap und gibt es zurück. Danach wird der Heap
	 * wieder richtig geordnet.
	 * 
	 * @return int: maximum
	 */
	public int getMax() {
		int max = field.get(1);
		field.set(1, field.get(field.size() - 1));
		field.remove(field.size() - 1);
		downheap();
		return max;
	}

	/**
	 * Ordent das letzte Element an die richtige Stelle ein.
	 */
	private void upheap() {
		int i = field.size() - 1;
		while (i > 1 && field.get(i) > field.get(i / 2)) {
			swap(i, i / 2);
			i = i / 2;
		}
	}

	/**
	 * Ordnet das oberste Element an die richtige Stelle ein.
	 */
	private void downheap() {
		int i = 1;
		while (i < field.size()) {
			int j = 2 * i;
			if (j >= field.size()) {
				return;
			}
			if (j + 1 < field.size() && field.get(j + 1) > field.get(j)) {
				j++;
			}
			if (field.get(i) > field.get(j)) {
				break;
			}
			swap(i, j);
			i = j;
		}

	}

	/**
	 * 
	 * @param i-
	 *            int: intdex1
	 * @param j-
	 *            int: index 2
	 */
	private void swap(int i, int j) {
		int temp = field.get(i);
		field.set(i, field.get(j));
		field.set(j, temp);

	}

	/**
	 * Gibt den Heap als String zurück
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < field.size(); i++) {
			sb.append(field.get(i));
			sb.append("\t");
		}
		return sb.toString();
	}

	/**
	 * Testet die Heap Klasse
	 * 
	 */
	public static void main(String[] args) {
		Heap heap = new Heap();
		int[] numbers = new int[] { 1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9 };

		System.out.println("Heap leer: " + heap.isEmpty());
		System.out.println(heap);

		for (int i : numbers) {
			heap.add(i);
			System.out.println(heap);
		}

		System.out.println();
		System.out.println("Heap leer: " + heap.isEmpty());
		System.out.println();

		for (int i : numbers) {
			System.out.println("Max: " + heap.getMax() + "\t Heap: " + heap);
		}
		System.out.println();
		System.out.println("Heap leer: " + heap.isEmpty());
	}

}
