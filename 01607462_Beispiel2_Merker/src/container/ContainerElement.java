/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package container;

public class ContainerElement<E> extends Object implements IContainerElement<E> {
	private E data;
	private IContainerElement<E>	nextElement;
	
	
	public ContainerElement(E data) {
		this.data = data;
		this.nextElement = null;
	}
	public ContainerElement(E data, IContainerElement<E> next) {
		this.data = data;
		this.setNextElement(next);
	}
	

	@Override
	public void setNextElement(IContainerElement<E> next) {
		this.nextElement = next;
	}

	@Override
	public boolean hasNextElement() {
		return (this.nextElement != null) ? true : false;
	}

	@Override
	public IContainerElement<E> getNextElement() {
		return (this.hasNextElement()) ? this.nextElement : null;
	}

	@Override
	public E getData() {
		return (this.data != null) ? this.data : null;
	}

}
