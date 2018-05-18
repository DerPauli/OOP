package tree.node;

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
		Collection<ITreeNode<NODETYPE>> ret = new Container<ITreeNode<NODETYPE>>();
		
		if(filter.searchFilterFunction(this, compareObject) ||
				filter.searchFilterFunction(this.nodeValue(), compareObject)) {
			ret.add(this);
		}
		
		for(ITreeNode<NODETYPE> node : this.getChildren()) {
			if(filter.searchFilterFunction(node, compareObject) ||
					filter.searchFilterFunction(node.nodeValue(), compareObject)) {
				if(!ret.contains(node))
					ret.add(node);
			}
		}
		return ret;
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
			return child.findNodeByValue(searchValue);
		}
		return null;
	}

	@Override
	public ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode) {
		if(this.equals(searchNode))
			return this;
		
		Collection<ITreeNode<NODETYPE>> curr = this.getChildren();
		
		for(ITreeNode<NODETYPE> child : curr) {
			return child.findNodeByNode(searchNode);
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
	    String ret;
	    if(this.isLeaf()) {
	      return "\n" + preamble + "-" + this.getLabel() + " " + this.nodeValue();
	    }
	    ret = "\n" + preamble + "+" + this.getLabel() + " " + this.nodeValue();
	    
	    for(ITreeNode<NODETYPE> element: this.getChildren()) {
	      ret += element.generateConsoleView(spacer, preamble + spacer);
	    }
	    return ret;
		
	}
	
	public String toString() {
		return this.getClass().getName() + '@' + Integer.toHexString(this.hashCode());
	}

	@Override
	public ITreeNode<NODETYPE> deepCopy() {
		GenericTreeNode<NODETYPE> ret = new GenericTreeNode<NODETYPE>(this.getLabel(), this.nodeValue());
		Collection<ITreeNode<NODETYPE>> newChilds = new Container<ITreeNode<NODETYPE>>();
		
		for(ITreeNode<NODETYPE> node : this.getChildren()) {
			newChilds.add(node.deepCopy());
		}
		
		ret.children = newChilds;
		return ret;
	}

}
