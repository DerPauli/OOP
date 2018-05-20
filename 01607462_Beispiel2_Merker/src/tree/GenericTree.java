/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree;

import java.util.Collection;

import container.Container;
import tree.node.ITreeNode;
import util.searchable.ISearchFilter;

public class GenericTree<TREETYPE> extends Object implements ITree<TREETYPE> {
	
	protected ITreeNode<TREETYPE> root;
	
	public GenericTree() {
		this.setRoot(null);
	}
	
	public GenericTree(ITreeNode<TREETYPE> root) {
		this.setRoot(root);
	}

	@Override
	public Collection<ITreeNode<TREETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		return (this.getRoot() != null) ? this.getRoot().searchByFilter(filter, compareObject) : new Container<ITreeNode<TREETYPE>>();
	}

	@Override
	public ITree<TREETYPE> deepCopy() {
		GenericTree<TREETYPE> copy = new GenericTree<TREETYPE>(this.getRoot().deepCopy());
		return copy;
	}

	@Override
	public ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode) {
		if(this.getRoot().equals(searchNode))
			return this.getRoot();
		else
			return this.getRoot().findNodeByNode(searchNode);
	}

	@Override
	public ITreeNode<TREETYPE> findNode(TREETYPE searchValue) {
		if(this.getRoot().checkNodeByValue(searchValue))
			return this.getRoot();
		else
			return root.findNodeByValue(searchValue);
	}

	@Override
	public String generateConsoleView(String spacer) {
		return this.getRoot().generateConsoleView(spacer, "");
	}

	@Override
	public ITreeNode<TREETYPE> getRoot() {
		return (this.root != null) ? this.root : null;
	}

	@Override
	public void setRoot(ITreeNode<TREETYPE> root) {
		this.root = root;
	}

}
