/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree.node;

import java.util.Collection;

import container.Container;
import domain.copy.IDeepCopy;

public class CopyableTreeNode<NODETYPE extends IDeepCopy> extends GenericTreeNode<NODETYPE> {

	public CopyableTreeNode(String label, NODETYPE value) {
		super(label, value);
	}
	
	public CopyableTreeNode<NODETYPE> deepCopy() {
		CopyableTreeNode<NODETYPE> ret = new CopyableTreeNode<NODETYPE>(this.getLabel(), this.nodeValue());
		Collection<CopyableTreeNode<NODETYPE>> children = new Container<CopyableTreeNode<NODETYPE>>();
		
		for(ITreeNode<NODETYPE> node : super.getChildren()) {
			children.add((CopyableTreeNode<NODETYPE>) node.deepCopy());
		}
		ret.getChildren().addAll(children);
		return ret;
	}

}
