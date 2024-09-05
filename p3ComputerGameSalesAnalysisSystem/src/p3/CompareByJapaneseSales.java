/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CompareByJapaneseSales implements Comparator<Game> {

		@Override
		public int compare(Game o1, Game o2) {
			if (o1.getJapaneseMarketSales() > o2.getJapaneseMarketSales()) {
				return 1;
			} else if (o1.getJapaneseMarketSales() < o2.getJapaneseMarketSales()) {
				return -1;
			} else {
				return 0;
			}
	}

}
