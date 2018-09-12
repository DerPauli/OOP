/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister.ui;

import java.util.Collection;

import cashregister.IShoppingCart;
import domain.product.IProduct;
import domain.record.IInvoice;
import domain.record.PaymentTransaction;
import tree.ITree;

public interface ICashRegisterUI {
	void	 displayProducts(ITree<IProduct> products);
	void	 displayRecords(Collection<IInvoice> records);
	void	 displayShoppingCart(IShoppingCart shoppingCart);
	void	 displayShoppingCarts(Collection<IShoppingCart> shoppingCarts);
	void	 displayTransaction(PaymentTransaction transaction);
	void pushUpdateInformation(ITree<IProduct> products, Collection<IShoppingCart> carts, Collection<IInvoice> records);
}
