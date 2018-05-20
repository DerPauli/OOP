/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree.node;

import java.util.Collection;

import container.Container;
import domain.product.IProduct;

public class CategoryTreeNode<NODETYPE,CATEGORY> extends GenericTreeNode<NODETYPE> {

	private CATEGORY category;
	
	public CategoryTreeNode(CATEGORY category) {
		super(category.toString(), null);
		this.category = category;
	}
	public CategoryTreeNode(String label, NODETYPE value) {
		super(label, value);
	}
	
	public CATEGORY getCategory() {
		return (this.category != null) ? this.category : null;
	}
	
	public NODETYPE nodeValue() {return null;}

	public String getLabel() {
		return (this.category != null) ? this.category.toString() : null;
	}
	
	public CategoryTreeNode<NODETYPE,CATEGORY> deepCopy() {
		CategoryTreeNode<NODETYPE, CATEGORY> ret = new CategoryTreeNode<NODETYPE, CATEGORY>(this.getCategory());
		Collection<CategoryTreeNode<NODETYPE, CATEGORY>> children = new Container<CategoryTreeNode<NODETYPE, CATEGORY>>();
		
		for(ITreeNode<NODETYPE> node : super.getChildren()) {
			children.add((CategoryTreeNode<NODETYPE, CATEGORY>) node.deepCopy());
		}
		ret.getChildren().addAll(children);
		return ret;
	}
	
}
