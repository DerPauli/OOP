/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;

public class SimpleProduct extends Product {

	public SimpleProduct(String name, float price) {
		super(name, price);
	}

	@Override
	public SimpleProduct deepCopy() {
		SimpleProduct newProd = new SimpleProduct(this.getName(), this.getPrice());
		return newProd;
	}

}
