package ha9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Die Klasse repraesentiert das HandlungsreisendenProblem.
 *
 */
public class HandlungsreisendenProblem {

	private ArrayList<String> cities;
	private HashMap<String, ArrayList<Integer>> map;
	private Scanner scanner;

	/**
	 * Erzeugt ein neues Objekt der Klasse.
	 */
	public static void main(String[] args) {
		new HandlungsreisendenProblem().run();
	}

	/**
	 * Inizialisiert die Attribute und liest die Staedte und die jeweiligen
	 * Entfernungen ein.
	 */
	public HandlungsreisendenProblem() {
		scanner = new Scanner(System.in);
		cities = new ArrayList<>();
		map = new HashMap<>();
		try {
			getCities("src/ha9/hauptstaedte.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lasst das Programm solange laufen bis der User das Programm beendet.
	 */
	private void run() {
		while (true) {
			String start = getStartingPoint();
			if ("x".equals(start)) {
				System.out.println("Ende");
				break;
			}
			ArrayList<Integer> distances = map.get(start);
			if (distances == null) {
				System.out.println("Stadt existiert nicht");
				continue;
			}
			getShortestWay(distances, start);
		}
		closeScanner();
	}

	/**
	 * Gibt den kuerzesten Weg von einer Start Stadt, ueber alle Staedte, wieder
	 * zurueck zur Start Stadt aus.
	 * 
	 * @param distances
	 *            - Die Entfernung zu allen Staedtenvon der Start Stadt.
	 * @param start-
	 *            Die start Stadt
	 */
	private void getShortestWay(ArrayList<Integer> distances, String start) {
		StringBuilder sb = new StringBuilder();
		sb.append(start);
		boolean[] visited = new boolean[map.size()];
		for (int i = 0; i < map.size(); i++) {
			int next = getNextCity(distances, visited);
			distances = map.get(cities.get(next));
			sb.append(" - " + cities.get(next));
		}
		System.out.println(sb.toString());
	}

	/**
	 * Gibt den index der Stadt zurück die als narchstes besucht wird und setzt
	 * diese auf besucht.
	 * 
	 * @param distances-
	 *            Distanz zu allen Staedten
	 * @param visited
	 *            - schon besuchte Staedte
	 * @return index der naechsten Stadt
	 */
	private int getNextCity(ArrayList<Integer> distances, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		for (int i = 0; i < distances.size(); i++) {
			if (!visited[i] && distances.get(i) > 0 && distances.get(i) < min) {
				min = distances.get(i);
				minIndex = i;
			}
		}
		visited[minIndex] = true;
		return minIndex;
	}

	/**
	 * Liest alle Staedte und Entfernungen aus dem uebergebenen Pfad ein.
	 * 
	 * @throws FileNotFoundException
	 */
	private void getCities(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner s = new Scanner(file);
		while (s.hasNextLine()) {
			String[] row = s.nextLine().split("\t");
			ArrayList<Integer> al = new ArrayList<>();
			for (int i = 1; i < row.length; i++) {
				al.add(Integer.parseInt(row[i]));
			}
			cities.add(row[0]);
			map.put(row[0], al);

		}
		s.close();
	}

	/**
	 * Gibt durch User input die start Stadt zurueck.
	 * 
	 * @return String - start Stadt
	 */
	private String getStartingPoint() {
		System.out.println("Bitte Anfangsstadt der Reise eingeben (x für Ende):");
		String start = scanner.next();
		return start;
	}

	/**
	 * Schliesst den Scanner.
	 */
	private void closeScanner() {
		scanner.close();
	}

}
