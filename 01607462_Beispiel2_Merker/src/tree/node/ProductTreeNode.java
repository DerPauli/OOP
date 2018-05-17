package tree.node;

import java.util.Collection;

import container.Container;
import domain.product.IProduct;
import domain.product.JointProduct;
import domain.product.Product;

public class ProductTreeNode extends GenericTreeNode<IProduct> {

	
	public ProductTreeNode(String label, IProduct value) {
		super(label, value);
		this.initialize(value);
	}
	
	public ProductTreeNode(Product value) {
		super(value.getName(), value);
		this.initialize(value);
	}
	private ProductTreeNode(JointProduct value) {
		super(value.getName(), value);
	}
	
	private void initialize(IProduct item) {
		if(item instanceof JointProduct) {
			Collection<Product> children = ((JointProduct) item).getProducts();
			Collection<ITreeNode<IProduct>> newNodes = super.getChildren();
			
			for(Product child : children) {
				GenericTreeNode<IProduct> node = new ProductTreeNode(child.getName(), child);
				newNodes.add(node);
			}
		}
	}
	
	public ProductTreeNode deepCopy() {
		return (ProductTreeNode) super.deepCopy();
	}

}
