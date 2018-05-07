package container;

import java.util.Arrays;
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
		return (this.firstElement == null) ? true : false;
	}

	@Override
	public boolean contains(Object o) {
		IContainerElement<E> head = this.firstElement;
		while(head.hasNextElement()) {
			if(head.getData().equals(o)) {
				return true;
			} else {
				head = head.getNextElement();
			}
		}
		//check last position
		if(head.getData().equals(o))
			return true;
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr<E>(this.firstElement);
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
	public E get(int index) throws IndexOutOfBoundsException {
		// get list length
		IContainerElement<E> head = this.firstElement;
		IContainerElement<E> search = this.firstElement;
		int size = 0;
		
		while(head.hasNextElement()) {
			head = head.getNextElement();
			size++;
		}
		size++;
		System.out.println("Size: " + size);
		if(index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
		
		// get element at index
		for(int i = 0; i < index; ++i) {
			search = search.getNextElement();
		}
		
		return search.getData();
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
		
		IContainerElement<E> next = this.firstElement;
		
		while(next.hasNextElement()) {
			if(next.getData().equals(element.getData())) {
				return false;
			}
			next = next.getNextElement();
		}
		// check last position
		if(next.getData().equals(element.getData())) {
			return false;
		}
		// e not in list --> add
		IContainerElement<E> last = this.firstElement;
		while(last.hasNextElement())
			last = last.getNextElement();
		ContainerElement<E> el = new ContainerElement<E>(e); // new element
		el.setNextElement(null);
		last.setNextElement(el);
		return true;
		
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean[] cont = new boolean[c.size()];
		Arrays.fill(cont, false);
		int cycles = 0;
		
		for(Object e : c) {
			IContainerElement<E> head = this.firstElement;
			while(head.hasNextElement()) {
				if(head.getData().equals(e)) {
					cont[cycles] = true;
				}
				head = head.getNextElement();
				
			}
			//check last position
			if(head.getData().equals(e))
				cont[cycles] = true;

			cycles++;
		}
		for(boolean check : cont) {
			if(!check)
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		int[] changed = new int[c.size()];
		Arrays.fill(changed, 0);
		int cycle = 0;
		
		for(E e : c) {
			ContainerElement<E> element = new ContainerElement<E>(e);
			if(this.firstElement == null) {
				this.firstElement = element;
				changed[cycle] = 1;
			}
			if(element.getData().equals(null))
				throw new NullPointerException();
			
			if(element.getData().equals(this.firstElement.getData()))
				changed[cycle] = 2;
			
			IContainerElement<E> next = this.firstElement;
			
			while(next.hasNextElement()) {
				if(next.getData().equals(element.getData())) {
					changed[cycle] = 2;
					break;
				}
				next = next.getNextElement();
			}
			// check last position
			if(next.getData().equals(element.getData())) {
				changed[cycle] = 2;
			}
			if(changed[cycle] == 0) {
				IContainerElement<E> last = this.firstElement;
				while(last.hasNextElement())
					last = last.getNextElement();
				ContainerElement<E> el = new ContainerElement<E>(e); // new element
				el.setNextElement(null);
				last.setNextElement(el);
				changed[cycle] = 1;
			}
			cycle++;
		}
		// check if every insert was successfull
		for(int check : changed) {
			if(check == 0 || check == 1)
				return true;
		}
		return false;
		
	}
	// FIXME: remove later, debug only
//	public void printContents() {
//		if(this.firstElement != null) {
//			IContainerElement<E> head = this.firstElement;
//			while(head.hasNextElement()) {
//				System.out.println(head.getData().toString());
//				head = head.getNextElement();
//			}
//			System.out.println(head.getData().toString());
//		} else {
//			System.out.println("There is no first element!");
//		}
//	}

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
		// just delete the firstElement
		// reference will be lost, automatic garbage collection will delete all nodes
		this.firstElement = null;
		
	}

}
