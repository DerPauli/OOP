/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree;

import domain.product.IProduct;
import tree.node.ITreeNode;
import tree.node.ProductTreeNode;

public class ProductTree extends GenericTree<IProduct>{
	
	public ProductTree() {
		super();
	}
	
	public ProductTree(ITreeNode<IProduct> root) {
		super(root);
	}
	
	public ProductTree(IProduct product) {
		ProductTreeNode root = new ProductTreeNode(product.getName(), product);
	}
}
