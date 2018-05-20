/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package container;

import java.util.Iterator;

public class Itr<E> extends Object implements Iterator<E>{
	private IContainerElement<E>	next;
	
	
	public Itr(IContainerElement<E> firstElement) {
		this.next = firstElement;
	}
	
	@Override
	public E next(){
		IContainerElement<E> ret = this.next;
		this.next = this.next.getNextElement();
		return (ret.getData() == null) ? null : ret.getData();
	}

	@Override
	public boolean hasNext() {
		return (this.next != null) ? true : false;
	}
	
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
