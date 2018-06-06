package managementserver;

import java.util.Collection;

import cashregister.ICashRegister;
import cashregister.IObserver;
import container.Container;
import domain.product.IProduct;
import domain.product.ProductCategory;
import tree.ITree;
import tree.ProductTree;
import tree.node.CategoryTreeNode;
import tree.node.ProductCategoryTreeNode;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(IObserver obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unregister(IObserver obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addCashRegister(ICashRegister cashRegister) {
		this.cashRegisters.add(cashRegister);
		// check if cashRegister is deriverative of IObserver
		// https://stackoverflow.com/a/12145210/8641285
		if(IObserver.class.isAssignableFrom(cashRegister.getClass())) {
			this.register((IObserver)cashRegister);
		}
		
	}

	@Override
	public void propagateProducts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITree<IProduct> retrieveProductSortiment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICashRegister retrieveRegisteredCashRegister(Long cashRegisterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ICashRegister> retrieveRegisteredCashRegisters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unregisterCashRegister(ICashRegister cashRegister) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyChange(IProduct arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void productAdded(IProduct arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void productRemoved(IProduct arg0) {
		// TODO Auto-generated method stub
		
	}

}
