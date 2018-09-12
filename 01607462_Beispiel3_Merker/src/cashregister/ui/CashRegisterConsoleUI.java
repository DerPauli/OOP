/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister.ui;

import java.util.Collection;

import cashregister.IShoppingCart;
import domain.product.IProduct;
import domain.product.IShoppingCartElement;
import domain.record.IInvoice;
import domain.record.PaymentTransaction;
import tree.ITree;

public class CashRegisterConsoleUI implements ICashRegisterUI {

	@Override
	public void displayProducts(ITree<IProduct> products) {
		System.out.println(products.generateConsoleView("_"));
	}

	@Override
	public void displayRecords(Collection<IInvoice> records) {
		long i = 1;
		for(IInvoice inv : records) {
			System.out.println("------------------------");
			System.out.println("Invoice Count: " + i);
			this.displayTransaction(inv.getPaymentTransaction());
			System.out.println("");
			for(IShoppingCartElement p : inv.getInvoiceProducts()) {
				System.out.println(p.getName() + " ......... " + p.getPrice());
			}
			System.out.println("------------------------");
			++i;
		}
	}

	@Override
	public void displayShoppingCart(IShoppingCart shoppingCart) {
		System.out.println("------------------------");
		System.out.println("ShoppingCart Id: " + shoppingCart.getShoppingCartID());
		System.out.println("Number of Elements: " + shoppingCart.getNumberOfElements());
		System.out.println("Total Price: " + shoppingCart.getTotalPriceOfElements());
		System.out.println("");
		for(IShoppingCartElement p : shoppingCart.currentElements()) {
			System.out.println(p.getName() + " ......... " + p.getPrice());
		}
		System.out.println("------------------------");
	}

	@Override
	public void displayShoppingCarts(Collection<IShoppingCart> shoppingCarts) {
		for(IShoppingCart cart : shoppingCarts) {
			this.displayShoppingCart(cart);
		}
	}

	@Override
	public void displayTransaction(PaymentTransaction transaction) {
		System.out.println("------------------------");
		System.out.println("Timestamp: " + transaction.getTimestamp());
		System.out.println("Transaction Id: " + transaction.getTransactionId());
		System.out.println("PaymentProvider: " + transaction.getPaymentProviderName());
		System.out.println("Price paid: " + transaction.getPaidPrice());
		System.out.println("------------------------");
	}

	@Override
	public void pushUpdateInformation(ITree<IProduct> products, Collection<IShoppingCart> carts,
			Collection<IInvoice> records) {
		// NOOP
	}

}
