package tree.node;

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
		return (CategoryTreeNode<NODETYPE, CATEGORY>) super.deepCopy();
	}
	
}
