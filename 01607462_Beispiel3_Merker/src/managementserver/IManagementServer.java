package managementserver;

import java.util.Collection;
import cashregister.ICashRegister;
import cashregister.NotRegisteredException;
import domain.product.IProduct;
import tree.ITree;

public interface IManagementServer {
	void addCashRegister(ICashRegister cashRegister);
	void propagateProducts();
	ITree<IProduct> retrieveProductSortiment();
	ICashRegister retrieveRegisteredCashRegister(Long cashRegisterId) throws NotRegisteredException;
	Collection<ICashRegister> retrieveRegisteredCashRegisters();
	void unregisterCashRegister(ICashRegister cashRegister) throws NotRegisteredException;
}
