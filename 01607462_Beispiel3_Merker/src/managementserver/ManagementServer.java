/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package managementserver;

import java.util.Collection;

import cashregister.CashRegister;
import cashregister.ICashRegister;
import cashregister.IObserver;
import cashregister.NotRegisteredException;
import container.Container;
import domain.product.IProduct;
import domain.product.JointProduct;
import domain.product.ProductCategory;
import tree.ITree;
import tree.ProductTree;
import tree.node.CategoryTreeNode;
import tree.node.GenericTreeNode;
import tree.node.ITreeNode;
import tree.node.ProductCategoryTreeNode;
import tree.node.ProductTreeNode;
import util.searchable.ProductNameFilter;
import warehouse.IWarehouseListener;

public class ManagementServer extends Object implements IManagementServer,
			IWarehouseListener, ISubjectManagementServer {
	
	private static ManagementServer INSTANCE;
	private ITree<IProduct> productAssortment = new ProductTree();
	private Collection<ICashRegister> cashRegisters = new Container();
	private Collection<IObserver> observer = new Container();
	
	private ManagementServer() {}

	public static IManagementServer GET_INSTANCE() {
		if(INSTANCE != null)
			return INSTANCE;
		
		INSTANCE = new ManagementServer();
		initialize(INSTANCE.productAssortment);
		return INSTANCE;
	}
	
	private static void initialize(ITree<IProduct> toAssert) {
		CategoryTreeNode root = new CategoryTreeNode("Products");
		Collection<ProductCategoryTreeNode> children = new Container();
		
		children.add(new ProductCategoryTreeNode(ProductCategory.FOOD));
		children.add(new ProductCategoryTreeNode(ProductCategory.BEVERAGE));
		children.add(new ProductCategoryTreeNode(ProductCategory.DEFAULT));
		
		root.getChildren().addAll(children);
		toAssert.setRoot(root);
	}
	
	@Override
	public ITree<IProduct> getChanges() {
		return this.productAssortment.deepCopy();
	}

	@Override
	public boolean register(IObserver obs) {
		if(obs != null) {
			if(this.observer.contains(obs)) {
				obs.activateNotifications(this);
				obs.notifyChange(this);
				return false;
			} else {
				obs.activateNotifications(this);
				obs.notifyChange(this);
				this.observer.add(obs);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean unregister(IObserver obs) {
		if(obs != null) {
			obs.deactivateNotifications(this);
			return this.observer.remove(obs);
		}
		return false;
	}

	@Override
	public void addCashRegister(ICashRegister cashRegister) {
		this.cashRegisters.add(cashRegister);
		// check if cashRegister is deriverative of IObserver
		// https://stackoverflow.com/a/12145210/8641285
		if(IObserver.class.isAssignableFrom(cashRegister.getClass())) {
			((IObserver)cashRegister).notifyChange(this);
			this.register((IObserver)cashRegister);
		}
		
	}

	@Override
	public void propagateProducts() {
		for(IObserver obs : this.observer) {
			obs.notifyChange(this);
		}
	}

	@Override
	public ITree<IProduct> retrieveProductSortiment() {
		return this.productAssortment.deepCopy();
	}

	@Override
	public ICashRegister retrieveRegisteredCashRegister(Long cashRegisterId) throws NotRegisteredException {
		for(ICashRegister reg : this.cashRegisters) {
			if(reg.getID() == cashRegisterId)
				return reg;
		}
		return null;
	}

	@Override
	public Collection<ICashRegister> retrieveRegisteredCashRegisters() {
		return this.cashRegisters;
	}

	@Override
	public void unregisterCashRegister(ICashRegister cashRegister) throws NotRegisteredException {
		// check if cashRegister is deriverative of IObserver
		// https://stackoverflow.com/a/12145210/8641285
		if(IObserver.class.isAssignableFrom(cashRegister.getClass())) {
			this.register((IObserver)cashRegister);
		}
		this.cashRegisters.remove(cashRegister);
	}

	@Override
	public void notifyChange(IProduct object) {
		
		if(this.productAssortment.findNode(object) != null) {
			ProductNameFilter filt = new ProductNameFilter();
			
			if(object instanceof JointProduct) {
				Collection<ITreeNode<IProduct>> joints = this.productAssortment.searchByFilter(filt, object);
				
				for(ITreeNode<IProduct> j : joints) {
					for(IProduct p : ((JointProduct)object).getProducts()) {
						if(j.findNodeByValue(p) == null) {
							j.getChildren().add(new ProductTreeNode(p));
						}
					}
				}
				return;
			} else {
				for(ITreeNode<IProduct> categories : this.productAssortment.getRoot().getChildren()) {
					for(ITreeNode<IProduct> c : categories.getChildren()) {
						
						if(c.checkNodeByValue(object)) {
							if(categories.getLabel().equals(object.getCategory().toString())) {
								c.nodeValue().setPrice(object.getPrice());
							} else {
								if(this.productAssortment.removeNode(c)) {
									this.productAdded(object);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void productAdded(IProduct product) {
		Collection<ITreeNode<IProduct>> nodes = this.productAssortment.getRoot().getChildren();
		ProductTreeNode elem = new ProductTreeNode(product);
		
		for(ITreeNode<IProduct> node : nodes) {
			if(ProductCategory.valueOf(node.getLabel().toUpperCase()).equals(product.getCategory())) {
				node.getChildren().add(elem);
			}
		}
	}
	
	@Override
	public void productRemoved(IProduct product) {
		while(this.productAssortment.getRoot().findNodeByValue(product) != null) {
			this.productAssortment.getRoot().removeNodeByValue(product);
		}
	}

}
