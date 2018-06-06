package managementserver;

import java.util.Collection;
import cashregister.ICashRegister;
import domain.product.IProduct;
import tree.ITree;

public interface IManagementServer {
	void addCashRegister(ICashRegister cashRegister);
	void propagateProducts();
	ITree<IProduct> retrieveProductSortiment();
	ICashRegister retrieveRegisteredCashRegister(Long cashRegisterId);
	Collection<ICashRegister> retrieveRegisteredCashRegisters();
	void unregisterCashRegister(ICashRegister cashRegister);
}
