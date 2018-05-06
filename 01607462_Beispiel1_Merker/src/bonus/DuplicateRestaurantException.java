/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package bonus;

import domain.Restaurant;

public class DuplicateRestaurantException extends Exception {

	private Restaurant restaurant;
	static final long serialVersionUID = 0;
	
	public DuplicateRestaurantException(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Override
	public String getMessage() {
		String text = "Restaurant " + this.restaurant.getName() + " already exists!";
		return text;

	}

}
