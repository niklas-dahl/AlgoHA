package ha1;

/**
 * 
 * Filter f�r Zahlen >9
 *
 */
public class TwoDigitsFilter implements Filter {

	/**
	 * �berpr�ft ob die Zahl >9 ist
	 */
	@Override
	public boolean evaluate(int x) {
		return x > 9;
	}

}
