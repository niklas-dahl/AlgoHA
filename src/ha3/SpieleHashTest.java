package ha3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Testet die SpieleHash Klasse
 *
 */
public class SpieleHashTest {

	private static SpieleHash set = new SpieleHash();

	/**
	 * Befüllt das Set.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("src/games50.txt");
		try (Scanner s = new Scanner(f)) {
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String spiel = line.split("\t")[1];
				set.add(spiel);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		isGameInSet("FIFA 15");
		isGameInSet("Star Wars: Battlefront");
		isGameInSet("WOW");

		System.out.println("Elemente im Set: " + set.size());

	}

	/**
	 * Überprüft ob sich das Spiel im Set befindet.
	 * 
	 * @param game
	 */
	private static void isGameInSet(String game) {
		if (set.contains(game)) {
			System.out.println(game + " ist vorhanden");
		} else {
			System.out.println(game + " ist nicht vorhanden");
		}
	}

}
