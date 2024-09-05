/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class CompareByNameLength implements Comparator<Game> {

	@Override
	public int compare(Game o1, Game o2) {
		// TODO Auto-generated method stub
		return o1.getName().length() - o2.getName().length();
	}
	
	
	

}
