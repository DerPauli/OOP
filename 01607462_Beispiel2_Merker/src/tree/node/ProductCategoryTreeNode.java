/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree.node;
import util.ProductCategory;

public class ProductCategoryTreeNode<NODETYPE> extends CategoryTreeNode<NODETYPE,ProductCategory> {

	public ProductCategoryTreeNode(ProductCategory category) {
		super(category);
	}
	
	public String getLabel() {
		return super.getLabel();
	}

}
