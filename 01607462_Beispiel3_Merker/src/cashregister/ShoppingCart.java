package cashregister;

import java.util.Collection;

import container.Container;
import domain.product.IShoppingCartElement;

public class ShoppingCart extends Object implements IShoppingCart {

	private long id;
	private Collection<IShoppingCartElement> shoppingCartElements = new Container<IShoppingCartElement>();
	
	public ShoppingCart(long id) {
		this.id = id;
	}
	@Override
	public Long getShoppingCartID() {
		return this.id;
	}

	@Override
	public Collection<IShoppingCartElement> currentElements() {
		return this.shoppingCartElements;
	}

	@Override
	public void addElement(IShoppingCartElement shoppingCartElement) {
		this.shoppingCartElements.add(shoppingCartElement);
	}

	@Override
	public int getNumberOfElements() {
		return this.shoppingCartElements.size();
	}

	@Override
	public float getTotalPriceOfElements() {
		float sum = 0.0f;
		for(IShoppingCartElement el : this.currentElements()) {
			sum += el.getPrice();
		}
		return sum;
	}

	@Override
	public boolean removeElement(IShoppingCartElement element) {
		return this.currentElements().remove(element);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + '@' + Integer.toHexString(this.hashCode());
	}
}
