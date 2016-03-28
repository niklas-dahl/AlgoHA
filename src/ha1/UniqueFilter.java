package ha1;

import java.util.HashSet;
import java.util.Set;

/**
 * Filter der keine doppelten Zahlen zul�sst
 *
 */
public class UniqueFilter implements Filter {

	private Set<Integer> values = new HashSet<Integer>();

	/**
	 * gibt true zur�ck wenn die Zahl noch nicht im Set war, sonst false
	 */
	@Override
	public boolean evaluate(int x) {
		return values.add(x);
	}

}
