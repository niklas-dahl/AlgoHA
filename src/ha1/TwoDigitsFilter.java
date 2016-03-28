package ha1;

/**
 * 
 * Filter für Zahlen >9
 *
 */
public class TwoDigitsFilter implements Filter {

	/**
	 * Überprüft ob die Zahl >9 ist
	 */
	@Override
	public boolean evaluate(int x) {
		return (x > 9 && x < 100) || (x < -9 && x > -100);
	}

}
