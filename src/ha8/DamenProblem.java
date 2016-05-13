package ha8;

import java.util.ArrayList;

/**
 * Das Problem mit den Damen ... tihihi ... *räusper* ...
 */
public class DamenProblem {

    /**
     * Die eigentliche Damen-Problem-Funktion.
     *
     * @param n Anzahl der Damen
     */

    public static void damenProblem(int n) {
        // In dieser Liste werden alle möglichen Aufstellungen gespeichert
        ArrayList<Aufstellung> list = new ArrayList<>();
        // Dann wird die damenProblem-Funktion, in der die eigentliche Berechnung abläuft, ausgeführt
        // Dafür werden als Parameter eine neue leere Aufstellung und die Liste übergeben
        damenProblem(new Aufstellung(n), list);
        for (Aufstellung a : list)
            System.out.println(a.toString());
        System.out.printf("%n(Insgesamt %d Lösungen)%n", list.size());
    }

    /**
     * ... hier findet dann die richtige Berechnung statt ...
     *
     * @param a    Bisherige Aufstellung
     * @param list Liste mit den Ergebnissen
     */

    private static void damenProblem(Aufstellung a, ArrayList<Aufstellung> list) {
        // Ist die Aufstellung schon voll, heißt das, dass die Aufstellung komplett und gültig ist.
        if (a.full()) {
            list.add(a); // Daher wird die Aufstellung zu den Ergebnissen hinzugefügt.
            return;
        }
        // In der aktuellen Spalte (a.getSpalte()) werden alle Zeilen (y) durchlaufen
        for (int y = 0; y < a.getSize(); y++) {
            Position p = new Position(a.getSpalte(), y);
            if (!a.isValid(p)) // Es wird gecheckt, ob die neue Position für die Aufstellung gültig wäre
                continue;
            Aufstellung a2 = new Aufstellung(a); // Dann wird die Aufstellung kopiert, die Position eingefügt und
            // diese Funktion wieder rekursiv ausgeführt. (Nächste Spalte)
            a2.add(p);
            damenProblem(a2, list);
        }
    }

    public static void main(String[] args) {
        damenProblem(8);
    }

    /**
     * Eine Klasse Position, die die Position einer Dame abbildet.
     */

    public static class Position {
        public final int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Diese Funktion prüft, ob diese Dame die andere Dame (p) schlagen könnte bzw. vice versa. true bedeutet, dass
         * die Dame nicht (ge)schlagen (werden) kann.
         *
         * @param p Die andere Dame
         * @return Position "unschlagbar"?
         */

        public boolean valid(Position p) {
            if (p.x == this.x || p.y == this.y) // = andere Position in der selben Zeile / Spalte
                return false;
            return Math.abs(this.x - p.x) != Math.abs(this.y - p.y);
        }

        public String toString() {
            return String.format("(%d/%d)", x, y);
        }
    }

    /**
     * Die Klasse Aufstellung beinhaltet alle in diesem Zweig des Baumes schon aufgestellten Damen. Sie lässt sich für
     * jeden weiteren Unterzweig klonen.
     */

    public static class Aufstellung {
        private Position[] pos; // Die Länge entspricht der erwarteten Anzahl Damen / Größe des Feldes
        private int index = 0; // Der Index der nächsten hinzuzufügenden Position im Array

        public Aufstellung(int n) {
            pos = new Position[n];
        }

        /**
         * Der Copy-Konstruktor. Kopiert (wow...)
         */
        public Aufstellung(Aufstellung copy) {
            this.pos = copy.getAufstellung();
            this.index = copy.index;
        }

        public Position[] getAufstellung() {
            Position[] ret = new Position[pos.length];
            for (int x = 0; x < pos.length; x++)
                ret[x] = pos[x];
            return ret;
        }

        /**
         * Überprüft, ob die neue Dame (p) von den bereits aufgestellten Damen geschlagen werden könnte. Gibt true
         * zurück, wenn alles ok ist und die Dame somit eingefügt werden könnte.
         *
         * @param p Die neue Dame
         * @return Aufstellbar?
         */

        public boolean isValid(Position p) {
            for (int x = 0; x < index; x++)
                if (!pos[x].valid(p))
                    return false;
            return true;
        }

        /**
         * Die Spalte, in der die nächste Dame aufgestellt werden soll
         */

        public int getSpalte() {
            return index;
        }

        /**
         * Erwartete Anzahl der Damen / Spalten
         */

        public int getSize() {
            return pos.length;
        }

        /**
         * Alle Damen platziert? (Mann, hört sich das scheiße an :D)
         */

        public boolean full() {
            return index == pos.length;
        }

        /**
         * Fügt eine neue Dame zur Aufstellung hinzu, wenn möglich.
         *
         * @param p Die neue Dame
         * @return Konnte die Dame hinzugefügt werden?
         */

        public boolean add(Position p) {
            if (index == pos.length)
                throw new ArrayIndexOutOfBoundsException();
            if (!isValid(p))
                return false;
            pos[index++] = p;
            return true;
        }

        /**
         * toString-Methode. Ausgabeformat: [Zeilennummer,Zeilennummer,Zeilennummer,...]
         */

        public String toString() {
            StringBuilder ret = new StringBuilder();
            boolean empty = true;
            for (int x = 0; x < index; x++) {
                ret.append(empty ? '[' : ',');
                empty = false;
                ret.append(pos[x].y + 1); // damit die Zeilennummerierung auch wirklich bei 1 beginnt...
            }
            ret.append(']');
            return ret.toString();
        }
    }
}
