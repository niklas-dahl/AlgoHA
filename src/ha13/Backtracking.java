import java.util.ArrayList;
import java.util.Scanner;

package ha13;

public class Backtracking {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> weights = new ArrayList<Integer>();
		int targetWeight = 0;

		try {
			System.out.print("Gewicht des Artikels (in Gramm): ");
			targetWeight = sc.nextInt();

			System.out.print("Anzahl Gewichte: ");
			int weightNum = sc.nextInt();

			System.out.print("Bitte Gewichte eingeben: ");
			for (int i = 0; i < weightNum; ++i) {
				weights.add(sc.nextInt());
			}

		} catch (Exception ex) {
			System.out.println("Fehler bei der Eingabe!");
			sc.next();
		}

		sc.close();

		// weights.add(1);
		// weights.add(3);
		// weights.add(8);
		// weights.add(20);

		ArrayList<String> results = new ArrayList<>();
		findAllResults(new ArrayList(), new ArrayList(), weights, targetWeight, results);

		System.out.println("Found " + results.size() + " results:");
		for (String res : results)
			System.out.println(res);
	}

	public static void findAllResults(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> notAssigned,
			int goal, ArrayList<String> results) {

		if (Math.abs(sum(left) - sum(right)) == goal) {
			String resString = "(";
			for (int i : left)
				resString += "+" + i + ", ";
			for (int i : right)
				resString += "-" + i + ", ";
			resString += ")";
			results.add(resString);
		}

		if (notAssigned.size() > 0) {
			int toAssign = notAssigned.get(0);
			notAssigned.remove(new Integer(toAssign));

			// add element to left
			ArrayList<Integer> leftCopy = (ArrayList<Integer>) left.clone();
			ArrayList<Integer> rightCopy = (ArrayList<Integer>) right.clone();
			ArrayList<Integer> notAssignedCopy = (ArrayList<Integer>) notAssigned.clone();
			leftCopy.add(toAssign);
			findAllResults(leftCopy, rightCopy, notAssignedCopy, goal, results);

			// add element to right
			leftCopy = (ArrayList<Integer>) left.clone();
			rightCopy = (ArrayList<Integer>) right.clone();
			notAssignedCopy = (ArrayList<Integer>) notAssigned.clone();
			rightCopy.add(toAssign);
			findAllResults(leftCopy, rightCopy, notAssignedCopy, goal, results);

			// do not use element
			findAllResults(left, right, notAssigned, goal, results);
		}
	}

	public static int sum(ArrayList<Integer> arr) {
		int sum = 0;
		for (int i : arr)
			sum += i;
		return sum;
	}
}
