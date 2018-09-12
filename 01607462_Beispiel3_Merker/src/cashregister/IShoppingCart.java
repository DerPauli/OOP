/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;

import java.util.Collection;

import domain.product.IShoppingCartElement;

public interface IShoppingCart {
	Long getShoppingCartID();
	Collection<IShoppingCartElement> currentElements();
	void addElement(IShoppingCartElement shoppingCartElement);
	int getNumberOfElements();
	float getTotalPriceOfElements();
	boolean removeElement(IShoppingCartElement element);
}
