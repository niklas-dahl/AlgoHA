package ha11;

import java.util.HashMap;

/**
 * Algo Gruppe 5 HA 11
 */
public class Wortsuche {
    public final String text;

    public Wortsuche(String text) {
        this.text = text;
    }

    /**
     * Hier wird die Last-Tabelle erstellt.
     *
     * @param pattern Suchpattern
     * @return Last-Tabelle
     */

    private static HashMap<Character, Integer> getLastTable(String pattern) {
        HashMap<Character, Integer> ret = new HashMap<>();
        for (int x = 0; x < pattern.length(); x++)
            ret.put(pattern.charAt(x), x);
        return ret;
    }

    public static void main(String[] args) {
        Wortsuche meinWort = new Wortsuche("DieguteBabanane123Lecker");
        int ergebnis = meinWort.findFirst("banane123");
        if (ergebnis == -1) {
            System.out.println("Pattern wurde nicht gefunden.");
        } else {
            System.out.println("Gefunden an Position " + ergebnis);
        }
    }

    /**
     * Hier wird geprüft, ob der Text an der angegebenen Stelle dem Pattern gleicht
     *
     * @param pattern Das Pattern
     * @param pos     Die Position
     * @return Gleich?
     */

    private boolean fits(String pattern, int pos) {
        // Er checkt hier zuerst ob pos < 0 ist oder das Pattern länger als der Rest des Textes
        // Wenn nicht, dann vergleicht er den entsprechenden Substring vom Text mit dem Pattern.
        return !(pos < 0 || text.length() < pos + pattern.length()) && text.substring(pos, pos + pattern.length()).equals(pattern);
    }

    /**
     * Sooo, hier wird dann auch mal wirklich was gesucht...
     *
     * @param pattern Das Suchpattern
     * @return Wo gibt es das Pattern?
     */

    public int findFirst(String pattern) {
        HashMap<Character, Integer> table = getLastTable(pattern);
        // Kleine Performanceoptimierung - Da er sowieso von Anfang an die Last-Tabelle prüft, kann ich das machen.
        int i = pattern.length() - 1;
        // So lange der Suchindex noch im Text liegt ...
        while (i < text.length()) {
            // ... wenn der char bei dem Index nicht in der Last-Tabelle ist, index++ und weiter ...
            if (!table.containsKey(text.charAt(i))) {
                i++;
                continue;
            }
            // ... ansonsten prüfen ob es sich vielleicht um das gesuchte Pattern im Text handelt ...
            if (fits(pattern, i -= table.get(text.charAt(i))))
                return i;
            // ... wenn es sich nicht um das Pattern handelt den Index weitersetzen ...
            i += pattern.length();
        }
        // ... und schließlich, wenn das Pattern nicht im Text ist, dem User eine -1 vor den Latz knallen.
        return -1;
    }
}
