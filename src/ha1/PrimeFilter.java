package ha1;

/**
 * Filter f�r Primzahlen
 */
public class PrimeFilter implements Filter {

	/**
	 * �berpr�ft ob die Zahl eine Primzahl ist
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
