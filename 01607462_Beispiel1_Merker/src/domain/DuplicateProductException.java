/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain;
import domain.product.IProduct;

public class DuplicateProductException extends Exception {

	private static final long serialVersionUID = 1L;
	private IProduct product;

	public DuplicateProductException(IProduct product) {
		this.product = product;
	}
	
	public String getMessage() {
		return new String("Product " + this.product.getName() + " already exists!");
	}

}
