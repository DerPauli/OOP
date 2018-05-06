/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain;

import java.util.List;
import domain.product.IProduct;
import domain.record.Record;
import ict.basics.IDeepCopy;


public class Order extends Record implements IDeepCopy {
	
	private List<IProduct> products;
	private OrderState currentState;
	private Table table;
	

	public Order(long identifier, Table table, List<IProduct> products) {
		super(identifier);
		this.table = table;
		this.products = products;
		this.currentState = OrderState.OPEN;
	}
	
	public List<IProduct> getProducts() {
		return this.products;
	}
	
	public boolean setState(OrderState newStatus) {
		if((this.currentState != OrderState.PAID) || (this.currentState != OrderState.CANCELLED)) {
			this.currentState = newStatus;
			return true;
		} else {
			return false;
		}
	}
	
	public OrderState getState() {
		return this.currentState;
	}
	
	public boolean isCancelled() {
		return (this.currentState == OrderState.CANCELLED) ? true : false;
	}
	
	public boolean isPaid() {
		return (this.currentState == OrderState.PAID) ? true : false;
	}
	
	public Table getTable() {
		return this.table;
	}

	@Override
	public Order deepCopy() {
		Order newOrder = this;
		for(IProduct prod : this.products) {
			newOrder.products.add(prod);
		}
		return newOrder;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Order) {
			Order tempOrder = (Order)obj;
			
			if(!((this.table == tempOrder.table) && (this.currentState == tempOrder.currentState)))
				return false;
			
			for(IProduct prod : tempOrder.products) {
				if(this.products.contains(prod))
					continue;
				else
					return false;
			}
			return true;
		} else {
			return false;
		}
	}

}
