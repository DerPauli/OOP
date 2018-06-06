package cashregister;

import java.util.Collection;

import cashregister.ui.ICashRegisterUI;
import container.Container;
import domain.product.IProduct;
import domain.product.IShoppingCartElement;
import domain.product.Product;
import domain.record.IInvoice;
import domain.record.Invoice;
import managementserver.ISubjectManagementServer;
import paymentprovider.IPayment;
import tree.ITree;
import tree.ProductTree;
import util.Tuple;

public class CashRegister extends Object implements ICashRegister, IObserver {
	
	private long id;
	private ITree<IProduct> products = new ProductTree();
	private Collection<IShoppingCart> shoppingCarts = new Container<IShoppingCart>();
	private Collection<IInvoice> records = new Container<IInvoice>();
	private Collection<ICashRegisterUI> uis = new Container<ICashRegisterUI>();
	private Collection<Tuple<ISubjectManagementServer, Boolean>> subjects = 
			new Container<Tuple<ISubjectManagementServer, Boolean>>();
	
	
	public CashRegister(long id) {
		this.id = id;
	}

	@Override
	public void notifyChange(ISubjectManagementServer subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivateNotifications(ISubjectManagementServer subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateNotifications(ISubjectManagementServer subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addProductToShoppingCart(long id, IShoppingCartElement element) {
		IShoppingCart cart = this.findShoppingCartById(id);
		if(cart != null) {
			if(cart.getShoppingCartID().equals(id)) {
				IShoppingCart sel = cart;
				sel.addElement(element.deepCopy());
				return true;
			}
		}
		return false;
	}

	@Override
	public Long addShoppingCart() {
		long idx = this.id+1;
		IShoppingCart newCart = new ShoppingCart(idx);
		this.shoppingCarts.add(newCart);
		return idx;
		
	}

	@Override
	public void displayProducts() {
		for(ICashRegisterUI ui: this.uis)
			ui.displayProducts(this.products);
	}

	@Override
	public void displayRecords() {
		for(ICashRegisterUI ui: this.uis)
			ui.displayRecords(this.records);
		
	}

	@Override
	public void displayShoppingCart(long id) {
		IShoppingCart cart = this.findShoppingCartById(id);
		if(cart != null) {
			for(ICashRegisterUI ui: this.uis)
				ui.displayShoppingCart(cart);
		}
	}

	@Override
	public void displayShoppingCarts() {
		for(ICashRegisterUI ui: this.uis)
			for(IShoppingCart cart : this.shoppingCarts)
				ui.displayShoppingCart(cart);
	}

	@Override
	public long getID() {
		return this.id;
	}

	@Override
	public Collection<IShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	@Override
	public IInvoice payShoppingCart(long id, IPayment provider) throws ShoppingCartNotFoundException {
		IShoppingCart cart = this.findShoppingCartById(id);
		if(cart != null) {
			if(cart.getShoppingCartID().equals(id)) {
				
				IShoppingCart sel = cart;
				IInvoice inv = new Invoice();
				
				inv.setInvoiceProducts(sel.currentElements());
				
				for(IShoppingCartElement el : sel.currentElements()) {
					inv.addPaymentTransaction(provider.pay(el.getPrice()));
				}
				this.records.add(inv);
				return inv;
			}
		}
		return null;
	}

	@Override
	public void registerCashRegisterUI(ICashRegisterUI ui) {
		this.uis.add(ui);
	}

	@Override
	public IShoppingCartElement selectProduct(Product product) {
		for(IShoppingCart cart : this.shoppingCarts) {
			for(IShoppingCartElement el : cart.currentElements()) {
				if(el.equals(product))
					return el;
			}
		}
		return null;
	}

	@Override
	public IShoppingCartElement selectProduct(String product) {
		for(IShoppingCart cart : this.shoppingCarts) {
			for(IShoppingCartElement el : cart.currentElements()) {
				if(el.getName().equals(product))
					return el;
			}
		}
		return null;
	}

	@Override
	public void unregisterCashRegisterUI(ICashRegisterUI ui) {
		this.uis.remove(ui);
	}
	
	protected IShoppingCart findShoppingCartById(Long id) {
		for(IShoppingCart cart : this.getShoppingCarts()) {
			if(cart.getShoppingCartID().equals(id)) {
				return cart;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + '@' + Integer.toHexString(this.hashCode());
	}
}
