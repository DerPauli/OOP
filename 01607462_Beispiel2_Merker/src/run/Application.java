package run;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import container.Container;
import container.Itr;
import domain.product.ExtendedProduct;
import domain.product.IProduct;
import domain.product.SimpleProduct;

public class Application {

	public static void main(String[] args) {
		Container<IProduct> prods = new Container<IProduct>();
		Collection<IProduct> meta = new ArrayList<IProduct>();
		
		SimpleProduct simple = new SimpleProduct("SimpleTest", 12.4f);
		
		meta.add(simple);
		meta.add(new ExtendedProduct("ExTest", 10.234f));
		
		prods.addAll(meta);
		
		Iterator<IProduct> itr = prods.iterator();
		
		while(itr.hasNext()) {
			System.out.println("Item: " + itr.next());
		}
		
		

	}

}
