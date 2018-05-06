import java.util.Collection;
import java.util.Iterator;

import container.Container;
import container.ContainerElement;
import container.IContainerElement;
import container.Itr;
import domain.copy.IDeepCopy;
import domain.product.ExtendedProduct;
import domain.product.IProduct;
import domain.product.JointProduct;
import domain.product.SimpleProduct;
import tree.GenericTree;
import tree.ITree;
import tree.ProductTree;
import tree.node.CategoryTreeNode;
import tree.node.CopyableTreeNode;
import tree.node.GenericTreeNode;
import tree.node.ITreeNode;
import tree.node.ProductCategoryTreeNode;
import tree.node.ProductTreeNode;
import util.ProductCategory;
import util.searchable.ProductNameFilter;
import util.searchable.TextSearchIgnoreCaseFilter;

/**
 * The examples in this class should not yield any compile time errors. However,
 * not all functionality is tested and additional tests have to be performed.
 * The behavior of the methods is not tested and has to be tested separately.
 *
 */
public class TestBsp2 {

	public static void main(String[] args) {
		TestBsp2.testContainer();
		TestBsp2.testTree();
	}

	@SuppressWarnings("unused")
	public static void testContainer() {
		// All methods call in this method should not yield any compile errors

		// Container
		Container<String> con = new Container<String>();
		boolean firstTrue = con.add("First");
		con.addAll(con);
		con.clear();
		boolean containsFirst = con.contains("First");
		boolean containsAll = con.containsAll(con);
		boolean equals = con.equals("First");
		String element = con.get(0);
		boolean isEmpty = con.isEmpty();
		boolean removeFirst = con.remove("First");

		boolean removeAll = con.removeAll(con);
		con.searchByFilter(new TextSearchIgnoreCaseFilter(), "First");
		con.retainAll(con);
		Object[] obj = con.toArray();
		String resString = con.toString();

		Collection<String> col = con;
		Iterator<String> it = con.iterator();

		// ContainerElement
		ContainerElement<String> elem = new ContainerElement<String>("Test", new ContainerElement<String>("Next"));
		String data = elem.getData();
		IContainerElement<String> nextElem = elem.getNextElement();
		elem.setNextElement(new ContainerElement<String>("Next"));
		boolean hasNext = elem.hasNextElement();
		IContainerElement<String> nextElem2 = nextElem.getNextElement();
		String stringVAlue = nextElem.getData();
		boolean hasNext2 = nextElem.hasNextElement();
		nextElem.setNextElement(new ContainerElement<String>("Next"));

		// Iterator
		Itr<String> itr = new Itr<>(elem);
		Iterator<String> it2 = itr;
		boolean itrHasNext = itr.hasNext();
		String nextValue = itr.next();
		itr.remove();
	}

	@SuppressWarnings("unused")
	public static void testTree() {
		// All methods call in this method should not yield any compile errors

		// Products
		JointProduct jointProduct = TestBsp2.generateJointProduct();
		jointProduct.removeProduct(null);
		IDeepCopy copyJointProduct = jointProduct.deepCopy();

		JointProduct copyJointProduct2 = jointProduct.deepCopy();

		ExtendedProduct exProd = new ExtendedProduct("Fanta", 3.0f);
		exProd.setName("Cola");
		exProd.setPrice(5.0f);
		exProd.undo();

		ExtendedProduct exProdCopy = exProd.deepCopy();
		IDeepCopy exProdCopy2 = exProd.deepCopy();

		// CategoryTreeNode
		CategoryTreeNode<IProduct, String> rootCategory = new CategoryTreeNode<IProduct, String>("Products");
		rootCategory.checkNodeByValue(rootCategory.nodeValue());
		rootCategory.findNodeByNode(rootCategory);
		rootCategory.findNodeByValue(rootCategory.nodeValue());
		rootCategory.generateConsoleView(" ", "");
		String cat = rootCategory.getCategory();
		Collection<ITreeNode<IProduct>> colChilds = rootCategory.getChildren();
		String label = rootCategory.getLabel();
		boolean isLeaf = rootCategory.isLeaf();
		IProduct prod = rootCategory.nodeValue();
		rootCategory.searchByFilter(new TextSearchIgnoreCaseFilter(), null);

		CategoryTreeNode<IProduct, String> rootCategoryCopy = rootCategory.deepCopy();
		IDeepCopy rootCategoryCopy2 = rootCategory.deepCopy();

		CategoryTreeNode<IProduct, ProductCategory> rootCategory2 = new CategoryTreeNode<IProduct, ProductCategory>(
				ProductCategory.BEVERAGE);
		CategoryTreeNode<IProduct, Integer> rootCategory3 = new CategoryTreeNode<IProduct, Integer>(5);

		// ProductTree
		ProductTree productTree = new ProductTree(rootCategory);
		ITree<IProduct> productTreeCopy = productTree.deepCopy();
		IDeepCopy productTreeCopy2 = productTree.deepCopy();

		productTree.findNode(jointProduct);
		productTree.findNode(rootCategory);
		productTree.generateConsoleView(" ");
		ITreeNode<IProduct> root = productTree.getRoot();
		productTree.setRoot(rootCategory);
		productTree.searchByFilter(new ProductNameFilter(), null);

		// ProductCategoryTreeNode
		ProductCategoryTreeNode<IProduct> catBeverage = new ProductCategoryTreeNode<IProduct>(ProductCategory.BEVERAGE);
		ProductCategoryTreeNode<IProduct> catFood = new ProductCategoryTreeNode<IProduct>(ProductCategory.FOOD);
		ProductCategoryTreeNode<IProduct> catDefault = new ProductCategoryTreeNode<IProduct>(ProductCategory.DEFAULT);

		CategoryTreeNode<IProduct, ProductCategory> tempCategoryTreeNode = catBeverage;

		// ProductTreeNode
		ProductTreeNode productNode = new ProductTreeNode(jointProduct);

		productNode.checkNodeByValue(exProd);
		IDeepCopy productNodeCopy = productNode.deepCopy();
		ProductTreeNode productNodeCopy2 = productNode.deepCopy();
		productNode.findNodeByNode(rootCategory);
		productNode.findNodeByValue(exProd);
		productNode.generateConsoleView(" ", "");
		Collection<ITreeNode<IProduct>> colProdChilds = productNode.getChildren();
		String prodLabel = productNode.getLabel();
		boolean prductNodeIsLeaf = productNode.isLeaf();
		IProduct productValueOfProductNode = productNode.nodeValue();
		productNode.searchByFilter(new TextSearchIgnoreCaseFilter(), null);

		// GenericTree
		GenericTree<IProduct> temp = new GenericTree<>();
		temp = new GenericTree<>(new ProductTreeNode(null));
		temp.setRoot(new ProductTreeNode(null));
		temp.generateConsoleView("   ");
		temp.findNode(exProd);
		temp.findNode(productNode);
		ITreeNode<IProduct> prodRoot = temp.getRoot();
		temp.searchByFilter(new ProductNameFilter(), null);
		ITree<IProduct> tempCopy = temp.deepCopy();
		IDeepCopy tempCopy2 = temp.deepCopy();

		// ITree
		ITree<IProduct> tempCopyRec = tempCopy.deepCopy();
		IDeepCopy tempCopyRec2 = tempCopy.deepCopy();

		tempCopy.findNode(exProd);
		tempCopy.findNode(productNode);
		tempCopy.generateConsoleView(" ");
		ITreeNode<IProduct> tempCopyNode = tempCopy.getRoot();
		tempCopy.searchByFilter(new ProductNameFilter(), null);
		tempCopy.setRoot(productNode);

		// GenericTree String
		GenericTree<String> tree = new GenericTree<String>();
		tree.setRoot(new GenericTreeNode<String>("Test", "Test"));
		ITree<String> copyTree = tree.deepCopy();
		tree.findNode(new GenericTreeNode<String>("TestLabel", "Test"));
		tree.findNode("Test");
		tree.generateConsoleView(" ");
		ITreeNode<String> rootNodeString = tree.getRoot();
		tree.searchByFilter(new TextSearchIgnoreCaseFilter(), "Test");

		// ITree String
		ITree<String> copyTree2 = copyTree.deepCopy();
		copyTree.findNode(new GenericTreeNode<String>("TestLabel", "Test"));
		copyTree.findNode("Test");
		copyTree.generateConsoleView(" ");
		copyTree.searchByFilter(new TextSearchIgnoreCaseFilter(), "Test");

		// ITreeNode
		rootNodeString.checkNodeByValue("Test");
		ITreeNode<String> rootNodeStringCopy = rootNodeString.deepCopy();
		IDeepCopy rootNodeStringCopy2 = rootNodeString.deepCopy();
		rootNodeString.findNodeByNode(rootNodeStringCopy);
		rootNodeString.findNodeByValue("Test");
		rootNodeString.generateConsoleView(" ", "____");
		Collection<ITreeNode<String>> colChildrenString = rootNodeString.getChildren();
		String labelNodeString = rootNodeString.getLabel();
		boolean isLeafString = rootNodeString.isLeaf();
		String value = rootNodeString.nodeValue();
		rootNodeString.searchByFilter(new TextSearchIgnoreCaseFilter(), null);

		// GenericTreeNode
		GenericTreeNode<Integer> intNode = new GenericTreeNode<Integer>("IntLabel", 5);
		intNode.checkNodeByValue(8);
		ITreeNode<Integer> intNodeCopy = intNode.deepCopy();
		IDeepCopy intNodeCopy2 = intNode.deepCopy();
		intNode.findNodeByNode(intNode);
		intNode.findNodeByValue(8);
		intNode.generateConsoleView(" ", "____");
		Collection<ITreeNode<Integer>> colChildrenInteger = intNode.getChildren();
		String labelNodeInteger = intNode.getLabel();
		boolean isLeafInteger = intNode.isLeaf();
		Integer valueInteger = intNode.nodeValue();
		intNode.searchByFilter(new TextSearchIgnoreCaseFilter(), null);

		// CopyableTreeNode
		CopyableTreeNode<IProduct> nodeProduct = new CopyableTreeNode<IProduct>("TestProduct", exProd);
		ITreeNode<IProduct> nodeProductCopy = nodeProduct.deepCopy();
		IDeepCopy nodeProductCopy2 = nodeProduct.deepCopy();
		nodeProduct.findNodeByNode(nodeProduct);
		nodeProduct.findNodeByValue(exProd);
		nodeProduct.generateConsoleView(" ", "____");
		Collection<ITreeNode<IProduct>> colChildrenProduct = nodeProduct.getChildren();
		String labelNodeProduct = nodeProduct.getLabel();
		boolean isLeafProduct = nodeProduct.isLeaf();
		IProduct prodValue = nodeProduct.nodeValue();
		rootNodeString.searchByFilter(new TextSearchIgnoreCaseFilter(), null);
	}

	public static JointProduct generateJointProduct() {

		JointProduct jp = new JointProduct("Menue 1", 10);

		jp.addProduct(new SimpleProduct("Jugendgetränk", 2.0f));
		jp.addProduct(new SimpleProduct("Pizza nach Wahl", 8.0f));
		jp.addProduct(new SimpleProduct("Nachspeise", 4.0f));

		JointProduct jp2 = new JointProduct("Menue 2", 6);

		jp2.addProduct(new SimpleProduct("Suppe", 4.0f));
		jp2.addProduct(new SimpleProduct("Cola", 4.4f));
		jp2.addProduct(new SimpleProduct("Spaghetti Tagesvariante", 7.5f));

		jp2.addProduct(new ExtendedProduct("Fanta", 3.0f));

		jp.addProduct(jp2);
		return jp;
	}
}
