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
		if(this.next.getNextElement() != null) {
			this.next = this.next.getNextElement();
			return (E) ret;
		} else {
			return null;
		}
	}

	@Override
	public boolean hasNext() {
		return (this.next.getNextElement() != null) ? true : false;
	}
	
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
