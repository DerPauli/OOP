/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;

public abstract class Product extends Object implements IProduct {
	
	private String name;
	private float price;
	
	Product(String name) {
		this.initialize(name, 0);
	}
	
	Product(String name, float price) {
		this.initialize(name, price);
	}
	
	private void initialize(String name, float price) {
		if(name != null) {
			//this.setName(name);
			this.name = name;
		} else {
			//this.setName("");
			this.name = "";
		}
		//this.setPrice(price);
		this.price = price;
	}
	
	public String toString() {
		return new String("name = " + this.getName() + " | price = " + this.getPrice());
	}
	
	
	public final boolean equals(Object obj) {
		return (obj instanceof Product) ? (this.getName().equals(((Product) obj).getName())) : false;
	}
	
	public abstract Product deepCopy();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) throws IllegalArgumentException {
		this.price = price;
	}
	

}
