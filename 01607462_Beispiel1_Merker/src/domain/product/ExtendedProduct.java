/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;

public class ExtendedProduct extends SimpleProduct {
	
	private ExtendedProduct savedState;

	public ExtendedProduct(String name, float price) {
		super(name, price);
		savedState = new ExtendedProduct(this);
	}
	
	public ExtendedProduct(ExtendedProduct product) {
		super(product.getName(), product.getPrice());
	}
	
	public void setName(String name) {
		this.savedState = new ExtendedProduct(this);		//save current state
		super.setName(name);
	}
	
	public void setPrice(float price) throws IllegalArgumentException {
		if(price < 0) {
			throw new IllegalArgumentException("Price is lower than zero !");
		} else {
			this.savedState = new ExtendedProduct(this);		//save current state
			super.setPrice(price);
		}
	}
	
	public String toString() {
		String rep = new String();
		
		// check undo state
		if((savedState.getName() == this.getName()) && (savedState.getPrice() == this.getPrice())) {
			// nothing to be done for target
			rep = "Name: " + this.getName() + " | Price: " + this.getPrice() + " | nothing to undo";
		} else {
			rep = "Name: " + this.getName() + " | Price: " + this.getPrice() + " | undo is possible";
		}
		
		return rep;
	}
	
	public boolean undo() {
		if((savedState.getName() == this.getName()) && (savedState.getPrice() == this.getPrice())) {
			return false;
		} else {
			super.setName(savedState.getName());
			super.setPrice(savedState.getPrice());
			return true;
		}
	}
	
	public ExtendedProduct deepCopy() {
		ExtendedProduct newExtProd = this;
		return newExtProd;
	}

}
