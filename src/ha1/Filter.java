package ha1;

/**
 * Filter interface
 */
public interface Filter {
	
	/**
	 * abstrakte methode zum �berpr�fen, ob ein Wert dem Filter entspricht
	 * 
	 * @param x der zu pr�fende Wert
	 * @return true falls Zahl dem Filter entspricht
	 */
	public boolean evaluate(int x); 
	
}
