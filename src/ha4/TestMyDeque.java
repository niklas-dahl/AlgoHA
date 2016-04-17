package ha4;

/**
 * Nicht Teil der Aufgabe nur zum testen der Klasse MyDeque
 * @author ndahl
 *
 */
public class TestMyDeque {
  
  public static void main(String[] args) {
    MyDeque test = new MyDeque();
    test.addFirst("first1");
    test.addFirst("first2");
    
    test.addLast("last 1");
    test.addFirst("first3");
    test.addLast("last2");
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println("Remove last: " + test.removeLast());
    System.out.println("Remove first: " + test.removeFirst());
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println("Remove first: " + test.removeFirst());
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println("Remove first: " + test.removeFirst());
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println("Remove first: " + test.removeFirst());
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");

    test.addFirst("1");
    test.addFirst("2");
    test.addFirst("3");
    test.addFirst("4");
    test.addFirst("5");
    
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println(test.removeLast());
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println(test.removeLast());
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println(test.removeLast());
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println(test.removeLast());
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
    System.out.println(test.removeLast());
    System.out.println(test);
    System.out.println(test.toStringReverse());
    System.out.println("--------");
    
  }
  
}
