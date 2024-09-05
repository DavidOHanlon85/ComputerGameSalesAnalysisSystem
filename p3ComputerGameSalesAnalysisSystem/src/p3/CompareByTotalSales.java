/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CompareByTotalSales implements Comparator<Game> {

	@Override
	public int compare(Game o1, Game o2) {
		if (o1.getGlobalSales() > o2.getGlobalSales()) {
			return 1;
		} else if (o1.getGlobalSales() < o2.getGlobalSales()) {
			return -1;
		} else {
			return 0;
		}
	}

}
