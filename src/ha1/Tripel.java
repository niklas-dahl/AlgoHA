package ha1;

import java.util.ArrayList;

/**
 * Komplexer Datentyp zum speichern von 3 Zahlen
 *
 */
public class Tripel {
	private int x, y, z;
	
	/**
	 * Konstruktor der die 3 Werte übergeben bekommt
	 * 
	 * @param x x-Wert
	 * @param y y-Wert
	 * @param z z-Wert
	 */
	public Tripel(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * toString Methode, die alle 3 Werte ausgibt
	 */
	@Override
	public String toString() {
		return "(" + x + "/" + y + "/" + z + ")";
	}

	/**
	 * Erzeugt alle Tripel die der Aufgabestellung entsprechen
	 */
	public static ArrayList<Tripel> getTripel(int n) {
		ArrayList<Tripel> result = new ArrayList<Tripel>();
		for (int z = 0; z < n; ++z) {
			for (int y = 0; y < z; ++y) {
				for (int x = 0; x < y; ++x) {
					if (x*x + y*y != z*z || x + y + z != n) continue;
					result.add(new Tripel(x, y, z));
				}
			}
		}
		return result;
	}
	
	/**
	 * Test case
	 */
	public static void main(String[] args) {
		for (int n : new int[]{30, 252}) {
			System.out.println("Tripel für n = " + n + ":");
			ArrayList<Tripel> result = getTripel(n);
			for (Tripel t : result)
				System.out.println(t);
		}
	}
}


