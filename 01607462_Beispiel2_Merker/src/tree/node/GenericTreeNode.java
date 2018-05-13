package tree.node;

import java.util.ArrayList;
import java.util.Collection;

import container.Container;
import util.searchable.ISearchFilter;

public class GenericTreeNode<NODETYPE> extends Object implements ITreeNode<NODETYPE> {
	private Collection<ITreeNode<NODETYPE>> children;
	private NODETYPE nodeValue;
	private String label;
	
	public GenericTreeNode(String label, NODETYPE value) {
		this.label = label;
		this.nodeValue = value;
		this.children = new Container<ITreeNode<NODETYPE>>();
	}
	
	@Override
	public Collection<ITreeNode<NODETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		return (this.children.isEmpty()) ? true : false;
	}

	@Override
	public Collection<ITreeNode<NODETYPE>> getChildren() {
		return this.children;
	}

	@Override
	public NODETYPE nodeValue() {
		return this.nodeValue;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue) {
		if(this.checkNodeByValue(searchValue))
			return this;
		
		Collection<ITreeNode<NODETYPE>> curr = this.getChildren();
		
		for(ITreeNode<NODETYPE> child : curr) {
			while(!child.isLeaf()) {
				this.findNodeByValue(searchValue);
			}
		}
		return null;
	}

	@Override
	public ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode) {
		if(this.equals(searchNode))
			return this;
		
		Collection<ITreeNode<NODETYPE>> curr = this.getChildren();
		
		for(ITreeNode<NODETYPE> child : curr) {
			while(!child.isLeaf()) {
				this.findNodeByNode(searchNode);
			}
		}
		return null;
	}

	@Override
	public boolean checkNodeByValue(NODETYPE value) {
		if(value == null && this.nodeValue == null)
			return true;
		
		if(this.nodeValue != null)
			return (this.nodeValue.equals(value)) ? true : false;
		
		return false;
	}

	@Override
	public String generateConsoleView(String spacer, String preamble) {
		// maybe ask michael
		return null;
	}

	@Override
	public ITreeNode<NODETYPE> deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
