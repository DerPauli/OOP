package tree.node;

import java.util.Collection;

import container.Container;
import domain.copy.IDeepCopy;

public class CopyableTreeNode<NODETYPE extends IDeepCopy> extends GenericTreeNode<NODETYPE> {

	public CopyableTreeNode(String label, NODETYPE value) {
		super(label, value);
	}
	
	public CopyableTreeNode<NODETYPE> deepCopy() {
		return (CopyableTreeNode<NODETYPE>) super.deepCopy();
	}

}
