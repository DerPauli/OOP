/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;

public class ShoppingCartNotFoundException extends Exception {

	private static final long serialVersionUID = -7022502604988563793L;
	
	public ShoppingCartNotFoundException(Long cashregisterid, Long shoppingcartid) {
		System.out.println("CashRegister with id " + cashregisterid + " accessing"
				+ " ShoppingCart with id " + shoppingcartid + " not found.");
	}
}
