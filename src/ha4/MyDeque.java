package ha4;

/**
 * Die Klasse stellt eine Schlange dar, an der man vorne und hinten elemente hinzufuegen und entfernen kann
 * @author ndahl
 *
 */
public class MyDeque {
  /**
   * Referenzen auf das erste und letzte Element
   */
  private Node first, last;
  
  /**
   * Konstruktor der Klasse, der nichts macht
   */
  public MyDeque() {
  }
  
  /**
   * Die Methode fuegt ein Element vorne an die Liste an
   * @param Das Element
   */
  public void addFirst(String s) {
    if(first == null) {
      first = new Node(s);
      last = first;
    } else {
      first = new Node(s, null, first);
    }
  }
  
  /**
   * Die Methode fuegt ein Element hinten an die Liste an
   * @param Das Element
   */
  public void addLast(String s) {
    if(first == null) {
      first = new Node(s);
      last = first;
    } else {
      last = new Node(s, last, null);
    }
  }
  
  /**
   * Die Methode entfernt das erste Element und gibt den Wert zurueck
   * @return Das erste Element, null falls die Liste leer ist
   */
  public String removeFirst() {
    if(first == null) return null;

    String s;
    if(first == last) {
      s = first.data;
      first = last = null;
    } else {
      s = first.data;
      first = first.next;
      first.prev = null;
    }
    return s;
  }
  
  /**
   * Die Methode entfernt das letzte Element und gibt den Wert zurueck
   * @return Das erste Element, null falls die Liste leer ist
   */
  public String removeLast() {
    if(first == null) return null;
    
    String s;
    if(first == last) {
      s = first.data;
      first = last = null;
    } else {
      s = last.data;
      last = last.prev;
      last.next = null;
    }
    return s;
  } 
  
  /**
   * Gibt die Datenstruktur als String zurück
   */
  @Override
  public String toString() {
    String s = "[";
    for(Node ref = first; ref != null; ref = ref.next) {
      s += ref.data + ", ";
    }
    return s + "]";
  }
  
  /**
   * Gibt die Datenstruktur als String zurück, geht von hinten durch die Liste
   * @return Die String Representation
   */
  public String toStringReverse() {
    String s = "]";
    for(Node ref = last; ref != null; ref = ref.prev) {
      s = ref.data + ", " + s;
    }
    return "[" + s;
  }
  
  /**
   * Hilfklasse fuer die verkettete Liste
   * @author ndahl
   *
   */
  private class Node {
    /**
     * Die String Daten die der Knoten enthaelt
     */
    String data;
    /**
     * Der Knoten vor und nach diesem Knoten
     */
    Node prev, next;
    
    /**
     * Konstruktor der nur die Daten bekommt
     * @param data
     */
    Node(String data) {
      this(data, null, null);
    }
    
    /**
     * Konstructor der auch den vorhaenger und nachfolger uebergeben bekommt
     * @param data Die Daten
     * @param prev Der Vorhaenger
     * @param next Der Nachfolger
     */
    Node(String data, Node prev, Node next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
      if(next != null) next.prev = this;
      if(prev != null) prev.next = this;
    }
  }
}
