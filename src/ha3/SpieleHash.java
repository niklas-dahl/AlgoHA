package ha3;

/**
 * Algorithmen Hausaufgabe 3 - Set mit double hashing
 * 
 */
public class SpieleHash {

	private String[] map;
	private int size;

	/**
	 * Konstruktor - setzt die default Werte
	 */
	public SpieleHash() {
		size = 0;
		map = new String[53];
	}

	/**
	 * Fügt ein neuen String zum Set hinzu.
	 * 
	 * @param spiel
	 * @throws StackOverflowError
	 *             - Falls der string nicht hinzugefügt werden kann.
	 */
	public void add(String spiel) {
		int hash = hashIndex(spiel);
		size++;
		if (map[hash] == null) {
			map[hash] = spiel;
		} else if (map[hash].equals(spiel)) {
			map[hash] = spiel;
		} else {
			int hash2 = hashIncrement(spiel);
			for (int i = 0; i < map.length / 2; i++) {
				int index = (hash + (i + 1) * hash2) % map.length;
				if (map[index] == null) {
					map[index] = spiel;
					return;
				} else if (map[index].equals(spiel)) {
					map[index] = spiel;
					return;
				}
			}
			size--;
			throw new StackOverflowError("String kann dem Set nicht hinzugefügt werden.");
		}
	}

	/**
	 * Überprüft ob sich der übergebene String in dem Set befindet.
	 * 
	 * @param spiel
	 * @return boolean - true falls String in Set.
	 */
	public boolean contains(String spiel) {
		int hash = hashIndex(spiel);
		if (map[hash] == null) {
			return false;
		} else if (map[hash].equals(spiel)) {
			return true;
		} else {
			int hash2 = hashIncrement(spiel);
			for (int i = 0; i < map.length / 2; i++) {
				if (map[hash + (i + 1) * hash2] == null) {
					return false;
				} else if (map[hash + (i + 1) * hash2].equals(spiel)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Berechnet den Index des Strings.
	 * 
	 * @param spiel
	 * @return int - index
	 */
	private int hashIndex(String spiel) {
		String hash = "" + spiel.hashCode();
		if (hash.length() > 1) {
			hash = hash.substring(hash.length() - 2, hash.length());
		}
		return Integer.parseInt(hash) % map.length;
	}

	/**
	 * Berechnet das Increment des Strings.
	 * 
	 * @param spiel
	 * @return int - hash
	 */
	private int hashIncrement(String spiel) {
		String hash = "" + spiel.hashCode();
		if (hash.length() > 4) {
			hash = hash.substring(hash.length() - 5, hash.length() - 3);
		}
		return Integer.parseInt(hash) % map.length + 1;
	}

	/**
	 * Gibt die Anzahl der Elemente zurück.
	 * 
	 * @return int - size
	 */
	public int size() {
		return size;
	}

	/**
	 * Gibt die Anzahl der felder des Arrays zurück.
	 * 
	 * @return int - length
	 */
	public int getLength() {
		return map.length;
	}
}
