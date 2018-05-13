package container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import util.searchable.ProductNameFilter;

public class Run {

	public Run() {
	}

	public static void main(String[] args) {
		
		ProductNameFilter filt = new ProductNameFilter();
		

		Container<Number> cont = new Container<Number>();
		System.out.println("List is empty: " + cont.isEmpty());
		cont.add(12);
		cont.add(13);
		cont.add(14);
		System.out.println("List is empty: " + cont.isEmpty());
		
		Integer t = new Integer(12);
		Collection<Number> c = new ArrayList<Number>();
		c.add(t);
		c.add(new Integer(12));
		c.add(new Integer(3));
		c.add(new Integer(42));
		cont.addAll(c);
		System.out.println(cont.get(3));
		
		System.out.println("Contains 3: " + cont.contains(3));
		System.out.println("Contains 42: " + cont.contains(42));
		System.out.println("Contains 19: " + cont.contains(9));
		//cont.printContents();
		//System.out.println(cont.remove(t));
		
		Collection<Number> co = new ArrayList<Number>();
		co.add(12);
		co.add(13);
		co.add(14);
		co.add(3);
		co.add(42);
		//System.out.println("removed from other list ? " + cont.removeAll(co));
		Iterator<Number> itr = cont.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		// TODO: test filter functions with IProducts
		Collection<Number> num = cont.searchByFilter(filt, 12);
		
		System.out.println(num.size());
		
		System.out.println("Contains All: " + cont.containsAll(co));
		System.out.println(cont.get(2));
		System.out.println("String representation: " + cont.toString());
		System.out.println("\n");
		System.out.println(cont.size());
		System.out.println("List is empty: " + cont.isEmpty());
		System.out.println("Clearing the SLL...");
		//cont.clear();
		System.out.println("List is empty: " + cont.removeAll(cont));
		System.out.println(cont.size());
		//cont.printContents();
		
		
	}

}
