/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;

public class NotRegisteredException extends Exception {

	private static final long serialVersionUID = -4898623214800756010L;
	
	public NotRegisteredException(String message) {
		super(message);
	}
}
