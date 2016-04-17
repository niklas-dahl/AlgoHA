package ha4;

/**
 * Die Klasse kann ueberpruefen ob ein String ebenentreu nach Aufgabenstellung ist
 * @author ndahl
 *
 */
public class Klammer {
  
  /**
   * Methode zum Testen der Klammer Klasse
   * @param args
   */
  public static void main(String[] args) {
    Klammer klammer = new Klammer();
    
    String[] tests = {"( ( [ [ ] ] ) )", "( [ ) ]", "( [ ] ] )", "( ( ) ) )", "( ( )", "( { [ ] ) }", ""};
    
    for(String s : tests) {
      boolean res = klammer.isEbenentreu(s);
      System.out.println("Test für "+s+" Result: " + res);
    }
    
  }
  
  /**
   * Die Methode ueberprueft ob ein String ebenentreu ist
   * @param Der zu ueberpruefende String
   * @return Das Ergebnis
   */
  public boolean isEbenentreu(String s) {
    MyDeque stack = new MyDeque();
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      
      if(c == '(') stack.addLast("(");
      if(c == '{') stack.addLast("{");
      if(c == '[') stack.addLast("[");
      
      if(c == ')') if(stack.removeLast() != "(") return false;
      if(c == '}') if(stack.removeLast() != "{") return false;
      if(c == ']') if(stack.removeLast() != "[") return false;
    }
    
    return stack.removeLast() == null;
  }
  
}
