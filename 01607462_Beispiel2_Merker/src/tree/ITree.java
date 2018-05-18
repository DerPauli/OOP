package tree;

import domain.copy.IDeepCopy;
import tree.node.ITreeNode;
import util.searchable.ISearchableByFilter;

public interface ITree<TREETYPE> extends IDeepCopy, ISearchableByFilter<ITreeNode<TREETYPE>> {
	ITree<TREETYPE> deepCopy();
	ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode);
	ITreeNode<TREETYPE> 	findNode(TREETYPE searchValue);
	String generateConsoleView(String spacer);
	ITreeNode<TREETYPE> getRoot();
	void setRoot(ITreeNode<TREETYPE> root);
}
