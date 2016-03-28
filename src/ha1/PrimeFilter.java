package ha1;

/**
 * Filter für Primzahlen
 */
public class PrimeFilter implements Filter {

	/**
	 * Überprüft ob die Zahl eine Primzahl ist
	 */
	@Override
	public boolean evaluate(int x) {
	    for(int i = 2; i < x; ++i) {
	        if(x % i==0)
	            return false;
	    }
	    return true;
	}
}
