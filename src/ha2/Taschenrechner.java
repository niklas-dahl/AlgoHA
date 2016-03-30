package ha2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * (C) 2016 AlgoHA ((TM) und (R)) ...
 */

public class Taschenrechner {

    /**
     * Hier werden die Variablen gespeichert ...
     */
    private static HashMap<String, Double> mapVars = new HashMap<>();

    /**
     * ... und hier werden die Operatoren gespeichert!
     * Ganz praktisch, wenn man noch was anderes definieren will!
     */
    private static final HashMap<String, MathOp> mathOps;

    static {
        mathOps = new HashMap<>();
        mathOps.put("+", (a, b) -> a + b);
        mathOps.put("-", (a, b) -> a - b);
        mathOps.put("*", (a, b) -> a * b);
        mathOps.put("/", (a, b) -> a / b);

        // Hier mal ein paar Extras!
        mathOps.put("pow", Math::pow);
        mathOps.put("root", (a, b) -> Math.pow(b, 1. / a));
    }

    /**
     * Naja, macht halt das, was im AB verlangt wurde -- Rechnen.
     * Allerdings gibt es nicht Double.NaN zurück, sondern wirft Exceptions,
     * damit die Leute auch wissen, WAS sie falsch gemacht haben :D
     *
     * @param exprUPN Der UPN-Ausdruck
     * @return hoffentlich ein hübsches Ergebnis ohne zu viele Nachkommastellen ;)
     * @throws UPNException Wird geworfen, wenn mit der Eingabe etwas nicht stimmt. Dann kann man die Nachricht auslesen, in der steht, woran's liegt.
     */
    private static double eval(String exprUPN) throws UPNException {
        Stack<Double> stack = new Stack<>();
        // Mit novalues wird überprüft, ob nach dem ersten Operator auch keine Zahlen mehr eingegeben werden.
        boolean novalues = false;
        for (String proc : exprUPN.split(" +")) {
            // Wenn's eine Nummer ist, dann wird es auf den Stack gelegt ...
            if (isNumeric(proc)) {
                if (novalues)
                    throw new UPNException("Nach dem ersten Operator darf es keine Werte mehr geben!");
                stack.push(Double.parseDouble(proc));
                continue;
            }
            // ... wenn's keine Nummer ist, dann wird geprüft, ob es der Name einer Variable ist ...
            if (mapVars.containsKey(proc)) {
                if (novalues)
                    throw new UPNException("Nach dem ersten Operator darf es keine Werte mehr geben!");
                stack.push(mapVars.get(proc));
                continue;
            }
            novalues = true;
            // ... wenn's auch keine Variable ist, dann muss es wohl ein Operator sein ...
            if (mathOps.containsKey(proc)) {
                if (stack.size() < 2)
                    throw new UPNException("Es wurden zu wenige Werte eingegeben!");
                double two = stack.pop(), one = stack.pop();
                stack.push(mathOps.get(proc).process(one, two));
                continue;
            }
            // ... und schließlich dreht der Taschenrechner durch und wirft eine nette kleine Exception!
            throw new UPNException("Der Taschenrechnr kann mit " + proc + " nichts anfangen! (Ist die Variable definiert?)");
        }
        // Es sollte am Ende nur noch ein Wert im Stack übrig bleiben, und zwar das Ergebnis!
        if (stack.size() != 1)
            throw new UPNException("Es wurden zu wenige Operatoren eingegeben!");
        return stack.pop();
    }

    /**
     * analyze gibt eine "Antwort" auf einen eingegebenen Ausdruck
     * @param expr Ausdruck
     * @return Antwort
     */

    public static String analyze(String expr) {
        String saveTo = null;
        int index;
        // Gibt es ein "=" im Ausdurck?
        if ((index = expr.indexOf("=")) != -1) {
            saveTo = expr.substring(0, index).trim();
            if (isNumeric(saveTo) || mathOps.containsKey(saveTo))
                return "Fehler: Variablenname darf weder eine Zahl noch ein Operator sein!";
            expr = expr.substring(index + 1).trim();
        }
        double ev;
        try {
            ev = eval(expr);
        } catch (UPNException ex) {
            // Wenn's einen Fehler gibt, wird der ausgegeben
            return "Fehler: " + ex.getMessage();
        }
        String ret = "";
        // Dann wird noch in die Variable gespeichert
        if (saveTo != null) {
            mapVars.put(saveTo, ev);
            ret += saveTo + " = ";
        }
        return ret + ev;
    }

    /**
     * So ziemlich die Testmethode vom AB
     * @param args Die braucht kein Schwein ...
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String proc;
        while (true) {
            System.out.print("> ");
            proc = sc.nextLine();
            if (proc.equals("quit"))
                break;
            if (proc.equals("clear")) {
                mapVars.clear();
                System.out.println("Variablenspeicher gelöscht!");
                continue;
            }
            System.out.println(analyze(proc));
        }
    }

    /**
     * Prüft mit regex, ob der String eine Zahl ist
     * (weil ich das nicht mit Parse und try/catch machen wollte)
     * @param str String ...
     * @return  ... ist eine Zahl?
     */

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Das funktionale Interface für Operatoren
     */

    private interface MathOp {
        double process(double one, double two);
    }

    /**
     * Und die Klasse für die Exceptions
     */

    private static class UPNException extends Exception {
        public UPNException(String msg) {
            super(msg);
        }
    }

}
