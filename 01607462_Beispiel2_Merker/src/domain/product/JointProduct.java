/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;
import java.util.ArrayList;
import java.util.Collection;

public class JointProduct extends Product {
	
	private float discountPercentage;
	private Collection<Product> products = new ArrayList<Product>();

	public JointProduct(String name, float discountPercentage) {
		super(name);
		
		if(discountPercentage < 0) {
			this.discountPercentage = 0;
		} else if(discountPercentage > 100) {
			this.discountPercentage = 100;
		} else {
			this.discountPercentage = discountPercentage;
		}
	}

	public JointProduct(String name, float discountPercentage, Collection<Product> products) {
		super(name);
		
		if(discountPercentage < 0) {
			this.discountPercentage = 0;
		} else if(discountPercentage > 100) {
			this.discountPercentage = 100;
		} else {
			this.discountPercentage = discountPercentage;
		}
		
		if(products != null) {
			for(Product prod : products) {
				this.products.add(prod);
			}
		}
	}
	
	public void addProduct(Product product) {
		if(product != null) {
			this.products.add(product);
		} else throw new NullPointerException();
	}
	
	public boolean removeProduct(Product product) {
		return (this.products.contains(product)) ? (this.products.remove(product)) : false;
	}

	public Collection<Product> getProducts() {
		return this.products;
	}
	
	public float getPrice() {
		float gPrice = 0;
		
		for(Product prod : this.products) {
			gPrice += (prod.getPrice() * (this.discountPercentage / 100));
		}
		
		return gPrice;
	}
	
	public String toString() {
		String str1 = new String("");
		String str2 = new String("Name: " + super.getName() + " | Price: " + super.getPrice() + 
				" | discountPercentage: " + this.discountPercentage + " | totalPrice: " + this.getPrice());
		str1 += "[ ";
		for(Product prod : this.products) {
			str1 += " Product Name: " + prod.getName() + " | Product Price: " + prod.getPrice();
		}
		str1 += " ]";
		
		return str2+str1;
	}
	
	@Override
	public JointProduct deepCopy() {
		Collection<Product> prods = new ArrayList<Product>();
		for(Product prod : this.products) {
			prods.add(prod.deepCopy());
		}
		JointProduct newJProd = new JointProduct(this.getName(), this.discountPercentage, prods);
		return newJProd;
	}

}
