package ha7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 
 * Klasse aus der Uebung, deswegen keine Kommentare, ausser bei den Hausaufgaben
 * Methoden
 *
 */
public class Graph {

	/**
	 * Hausaufgaben Anfang
	 */

	/**
	 * Main Methode zum testen der Funktionen
	 */
	public static void main(String[] args) {
		int[] vlist = { 6, 10, 1, 5, 1, 4, 2, 3, 2, 6, 3, 4, 3, 5, 4, 5, 4, 2, 5, 6, 6, 1 };
		Graph g = new Graph(vlist);
		System.out.println(g.dfs(1));
		System.out.println(g.bfs(1));
	}

	/**
	 * Die Methode startet die rekursive Tiefensuche von einen beliebigen Startpunkt aus
	 * 
	 * @param start Der Startpunkt der Tiefensuche
	 * @return Die Liste der besuchten Knoten in der Reihenfolge in der sie besucht wurden
	 */
	public ArrayList<Integer> dfs(int start) {
		return dfs(new ArrayList<Integer>(Arrays.asList(1)), new LinkedList<Integer>(Arrays.asList(1)));
	}

	/**
	 * Die rekursive Tiefensuche
	 * 
	 * @param visited Die Liste der Knoten die schon besucht wurden
	 * @param result Die Liste die die Besuch-Reihenfolge speichert
	 * @return Die Liste der besuchten Knoten in der Reihenfolge in der sie besucht wurden
	 */
	private ArrayList<Integer> dfs(ArrayList<Integer> visited, LinkedList<Integer> result) {
		ArrayList<Integer> myConnections = adjacentList[result.getLast() - 1];
		visited.add(result.getLast() - 1);
		Collections.sort(myConnections);
		myConnections.remove(result);
		for (Integer c : myConnections) {
			if (!result.contains(c)) {
				result.add(c);
				dfs(visited, result);
			}
		}
		return new ArrayList<>(result);
	}
	
	/**
	 * 
	 * Die Methode fuehrt eine Breitensuche mithilfe einer Queue durch, von einen beliebigen Startpunkt aus
	 * 
	 * @param start Der Startpunkt der Breitensuche
	 * @return Die Liste der besuchten Knoten in der Reihenfolge in der sie besucht wurden
	 */
	ArrayList<Integer> bfs(int start) {
		ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(start));
		LinkedList<Integer> myQueue = new LinkedList<Integer>();
		myQueue.push(start);
		while (!myQueue.isEmpty()) {
			int currentNode = myQueue.removeFirst();
			ArrayList<Integer> myConnections = adjacentList[currentNode - 1];
			for (Integer c : myConnections) {
				if (!result.contains(c)) {
					result.add(c);
					myQueue.add(c);
				}
			}
		}
		return result;
	}

	/**
	 * Hausaufgaben Ende
	 * 
	 * Rest der Klasse aus der Uebung:
	 * 
	 */

	private ArrayList<Integer>[] adjacentList;

	@SuppressWarnings("unchecked")
	public Graph(int v) {
		adjacentList = (ArrayList<Integer>[]) new ArrayList[v];
	}

	@SuppressWarnings("unchecked")
	public Graph(int[] list) {
		adjacentList = (ArrayList<Integer>[]) new ArrayList[list[0]];
		for (int i = 0; i < list[0]; i++) {
			adjacentList[i] = new ArrayList<>();
		}
		for (int i = 2; i < list.length; i += 2) {
			adjacentList[list[i] - 1].add(list[i + 1]);
		}
	}

	public int getEdgeCount() {
		AtomicInteger atom = new AtomicInteger(0);
		IntStream.range(0, adjacentList.length).parallel().forEach(j -> atom.addAndGet(adjacentList[j].size()));
		return atom.get();
	}

	public int getVertexCount() {
		return adjacentList.length;
	}

	public void addEdge(int from, int to) {
		adjacentList[from - 1].add(to);
	}

	public ArrayList<Integer> getAdjacent(int v) {
		ArrayList<Integer> result = new ArrayList<>(adjacentList[v - 1]);
		return result;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (ArrayList<Integer> c : adjacentList) {
			builder.append(c.toString());
		}
		builder.append("]");
		return builder.toString();
	}

}
