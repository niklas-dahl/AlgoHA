package ha10;

public class Dijkstra {
	
	/**
	 * Die Testmethode die auf 2 Graphen den Dijkstra Algorithmus anwendet
	 * @param args Not used
	 */
	public static void main(String[] args) {
		printDijkstra(new int[] {4,1,2,2,1,4,5,2,4,1,2,3,4,3,1,1,4,3,1});
		System.out.println();
		printDijkstra(new int[] {10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,10,15,9,8,10,9,10,30});
	}	
	
	/**
	 * Integer "Unendlich" Wert 
	 */
	public static final int infinite = Integer.MAX_VALUE/3;
	
	/**
	 * Die Methode die den Algorithmus durchfuehrt und die tabellarische Ausgabe ausgibt
	 * @param kanten
	 */
	public static void printDijkstra(int[] kanten) {
		// +1 weil die Knoten mit 1 beginnen, 0 ist der dummy Knoten
		int nodeCount = kanten[0] + 1;
		// array der speichert ob ein Knoten schon besucht wurde
		boolean[] visited = new boolean[nodeCount];
		// array der den Vorgaenger fuer jeden Knoten speichert
		int[] predecessors = new int[nodeCount]; // 0 bedeutet keinen Vorgänger
		// array der das Gewicht des Weges speichert
		int[] minDist = new int[nodeCount];
		// der Graph selbst als Adjazenzmatrix
		int[][] graph = new int[nodeCount][nodeCount];
		
		// setzte alle Wege an Anfang auf unendlich
		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = infinite;
		}
		// setzt den Abstand des Startknoten auf 0
		minDist[1] = 0;
		
		// setzt alle Kantengewichte der Adjazenzmatrix auf unendlich
		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph.length; y++) {
				graph[x][y] = infinite;
			}
		}
		
		// lese die Daten ein und speicher die Kantengewichte in der Adjazenzmatrix
		for (int i = 1; i < kanten.length; i+=3) {
			int source = kanten[i];
			int dest = kanten[i+1];
			int weight = kanten[i+2];
			graph[source][dest] = weight;
		}
		
		// Gibt den Kopf der Ausgabetabelle aus
		System.out.print("vi |");
		for (int i = 2; i < nodeCount; i++) {
			System.out.print(String.format("% 3d", i));
		}
		System.out.print("|");
		for (int i = 2; i < nodeCount; i++) {
			System.out.print(String.format("% 3d", i));
		}
		System.out.println("|");
		for (int i = 2; i < nodeCount; i++) {
			System.out.print("--------");
		}
		System.out.println("");
		
		// der zu expandierende Knoten
		int currentNode;
		// zaehlt die Iterationen 
		int interation = 0;
		while((currentNode = nodeWithMinimalDist(visited, minDist)) != -1) {
			visited[currentNode] = true;
			
			for (int neighbor = 0; neighbor < graph[currentNode].length; neighbor++) {
				if (graph[currentNode][neighbor] < infinite && !visited[neighbor]) {
					
					int newPathWeight = minDist[currentNode] + graph[currentNode][neighbor];
					if (newPathWeight < minDist[neighbor]) {
						minDist[neighbor] = newPathWeight;
						predecessors[neighbor] = currentNode;
					}
					
				}
			}
			
			// Ausgabe der Tabelle
			System.out.print(String.format("% 3d", ++interation) + "|");
			for (int i = 2; i < nodeCount; i++) {
				if (minDist[i] >= infinite) System.out.print("  -");
				else System.out.print(String.format("% 3d", minDist[i]));
			}
			System.out.print("|");
			for (int i = 2; i < nodeCount; i++) {
				if (predecessors[i] == 0) System.out.print("  -");
				else System.out.print(String.format("% 3d", predecessors[i]));
			}
			System.out.println("|");
		}
		
	}
	
	/**
	 * Hilfsmethode die den naechsten zu expandierenden Knoten zurueck gibt, oder -1 wenn alle Knoten besucht wurden
	 * @param minDist Der Array der Gewichte der Wege
	 * @param visited Der Array ob ein Knoten schon besucht wurde
	 * 
	 * @return Knoten der noch nicht besucht wurde und zurzeit den kuerzesten Weg hat
	 */
	public static int nodeWithMinimalDist(boolean[] visited, int[] minDists) {
		int minNode = -1;
		int minDist = infinite;
		
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				if (minDists[i] < minDist) {
					minDist = minDists[i]; 
					minNode = i;
				}
			}
		}
		
		return minNode;
	}
	
}
