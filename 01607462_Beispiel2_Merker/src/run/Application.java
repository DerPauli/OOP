package run;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import container.Container;
import container.Itr;
import domain.product.ExtendedProduct;
import domain.product.IProduct;
import domain.product.SimpleProduct;
import tree.GenericTree;
import tree.node.GenericTreeNode;
import tree.node.ITreeNode;

public class Application {

	public static void main(String[] args) {
		/***
		 * CONTAINER SECTION
		 */
		Container<IProduct> prods = new Container<IProduct>();
		Collection<IProduct> meta = new ArrayList<IProduct>();
		
		SimpleProduct simple = new SimpleProduct("SimpleTest", 12.4f);
		
		meta.add(simple);
		meta.add(new ExtendedProduct("ExTest", 10.234f));
		
		prods.addAll(meta);
		
		Iterator<IProduct> itr = prods.iterator();
		
//		while(itr.hasNext()) {
//			System.out.println("Item: " + itr.next());
//		}
		
		/***
		 * TREE SECTION
		 */
		
		GenericTreeNode<Number> root = new GenericTreeNode<Number>("Root", new Double(12.5f));
		GenericTreeNode<Number> num1 = new GenericTreeNode<Number>("Num1", new Double(13.5f));
		GenericTreeNode<Number> num2 = new GenericTreeNode<Number>("Num2", new Double(14.5f));
		GenericTreeNode<Number> num3 = new GenericTreeNode<Number>("Num3", new Double(15.5f));
		GenericTreeNode<Number> num4 = new GenericTreeNode<Number>("Num4", new Double(16.5f));
		
		GenericTreeNode<Number> num5 = new GenericTreeNode<Number>("Num5", new Double(17.5f));
		GenericTreeNode<Number> num6 = new GenericTreeNode<Number>("Num6", new Double(20.0f));
		GenericTreeNode<Number> num7 = new GenericTreeNode<Number>("Num7", new Double(30.0f));
		
		GenericTreeNode<Number> num8 = new GenericTreeNode<Number>("Num8", new Double(18.5f));
		GenericTreeNode<Number> num9 = new GenericTreeNode<Number>("Num9", new Double(19.5f));
		GenericTreeNode<Number> num10 = new GenericTreeNode<Number>("Num10", new Double(40.0f));
		GenericTreeNode<Number> num11 = new GenericTreeNode<Number>("Num11", new Double(50.0f));
		GenericTreeNode<Number> num14 = new GenericTreeNode<Number>("Num14", new Double(90.0f));
		GenericTreeNode<Number> num15 = new GenericTreeNode<Number>("Num15", new Double(100.0f));
		
		GenericTreeNode<Number> num12 = new GenericTreeNode<Number>("Num12", new Double(20.5f));
		GenericTreeNode<Number> num13 = new GenericTreeNode<Number>("Num13", new Double(21.5f));
		
		
		
		GenericTree<Number> numTree = new GenericTree<Number>();
		root.getChildren().add(num1);
		root.getChildren().add(num2);
		root.getChildren().add(num3);
		root.getChildren().add(num4);
		num5.getChildren().add(num6);
		num5.getChildren().add(num7);
		root.getChildren().add(num5);
		root.getChildren().add(num8);
		num9.getChildren().add(num10);
		num11.getChildren().add(num14);
		num11.getChildren().add(num15);
		num9.getChildren().add(num11);
		root.getChildren().add(num9);
		root.getChildren().add(num12);
		root.getChildren().add(num13);
		
		numTree.setRoot(root);
		for(ITreeNode<Number> node : numTree.getRoot().getChildren()) {
			System.out.println(node.nodeValue());
		}
		//System.out.println(numTree.generateConsoleView("_"));
		System.out.println(numTree.getRoot().generateConsoleView("**", "__"));
	}

}
