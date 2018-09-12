/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;

import java.util.Collection;

import cashregister.ui.ICashRegisterUI;
import domain.product.IShoppingCartElement;
import domain.product.Product;
import domain.record.IInvoice;
import paymentprovider.IPayment;

public interface ICashRegister {
	boolean addProductToShoppingCart(long id, IShoppingCartElement element);
	Long addShoppingCart();
	void displayProducts();
	void displayRecords();
	void displayShoppingCart(long id);
	void displayShoppingCarts();
	long getID();
	Collection<IShoppingCart> getShoppingCarts();
	IInvoice payShoppingCart(long id, IPayment provider) throws ShoppingCartNotFoundException;
	void registerCashRegisterUI(ICashRegisterUI ui);
	IShoppingCartElement selectProduct(Product product);
	IShoppingCartElement selectProduct(String product);
	void unregisterCashRegisterUI(ICashRegisterUI ui);
}
