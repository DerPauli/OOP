package container;

import java.util.Collection;
import java.util.Iterator;

import util.searchable.ISearchFilter;
import util.searchable.ISearchableByFilter;

public class Container<E> extends Object implements Collection<E>, ISearchableByFilter<E> {
	private IContainerElement<E>	firstElement;
	
	public Container() {
		this.firstElement = null;
	}

	@Override
	public Collection<E> searchByFilter(ISearchFilter filter, Object compareObject) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(E e) throws NullPointerException{
		ContainerElement<E> element = new ContainerElement<E>(e);
		if(this.firstElement == null) {
			this.firstElement = element;
			return true;
		}
		if(element.getData().equals(null))
			throw new NullPointerException();
		
		if(element.getData().equals(this.firstElement.getData()))
			return false;
		
		Itr<E> search = new Itr<E>(this.firstElement);
		Itr<E> add = new Itr<E>(this.firstElement);
		while(search.hasNext()) {
			E tmp = search.next();
			if(tmp != null) {
				if(element.getData().equals(tmp)) {
					return false;
				}
			}
		}
		// e not in list --> add
		while(true) {
			if(!add.hasNext()) {
				ContainerElement<E> elem = new ContainerElement<E>(e);
				IContainerElement<E> output = ((IContainerElement<E>) add.next());
				output.setNextElement(elem);
				return true;
			}
			add.next();
		}
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		
	}

}
