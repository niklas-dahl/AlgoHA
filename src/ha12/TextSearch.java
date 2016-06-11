package ha12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class TextSearch {

	/**
	 * Testet die Methode textSearch.
	 */
	public static void main(String[] args) {
		System.out.println(textSearch("abcabcdababdc.", "ab")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "c.")); // 2, 5, 12
		System.out.println(textSearch("abcabcdababdc.", "c\\.")); // 12
		System.out.println(textSearch("abcabcdababdc.", "b[cd]")); // 1,4,10
		System.out.println(textSearch("abcabcdababdc.", "a....c")); // 0,7
		System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); // 3,5
		System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); // 7
	}

	/**
	 * Gibt eine Liste mit allen Start-Indices von matches zurueck.
	 * 
	 * Dabei werden die Sonderflälle '[]', '.' und '\\' beachtet.
	 * 
	 * @param text
	 *            -Der Text
	 * @param pattern-
	 *            Das Pattern
	 * @return ArrayList - Indices
	 */
	public static ArrayList<Integer> textSearch(String text, String pattern) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < text.length(); i++) {
			boolean match = true;
			int skippedChars = 0;
			for (int j = 0; j < pattern.length(); j++) {
				if (i + (j - skippedChars) >= text.length()) {
					match = false;
					break;
				}
				final char c = text.charAt(i + (j - skippedChars));
				switch (pattern.charAt(j)) {
				case '\\':
					j++;
					skippedChars++;
					if (pattern.charAt(j) != c) {
						match = false;
						break;
					}
					break;
				case '[':
					j++;
					skippedChars++;
					Set<Character> chars = new HashSet<>();
					while (pattern.charAt(j) != ']') {
						chars.add(pattern.charAt(j));
						j++;
						skippedChars++;
						if (j >= pattern.length()) {
							throw new PatternSyntaxException(text, pattern, j);
						}
					}
					if (!chars.contains(c)) {
						match = false;
						break;
					}
					break;
				case '.':
					if (c == ' ') {
						match = false;
						break;
					}
					break;
				default:
					if (pattern.charAt(j) != c) {
						match = false;
						break;
					}
				}
			}
			if (match) {
				indices.add(i);
			}
		}
		return indices;
	}

}
