/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package tree.node;

import java.util.Collection;

import domain.copy.IDeepCopy;
import util.searchable.ISearchableByFilter;

public interface ITreeNode<NODETYPE> extends IDeepCopy, ISearchableByFilter<ITreeNode<NODETYPE>> {
	boolean isLeaf();
	Collection<ITreeNode<NODETYPE>> getChildren();
	NODETYPE nodeValue();
	String getLabel();
	ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue);
	ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode);
	boolean checkNodeByValue(NODETYPE value);
	String generateConsoleView(String spacer, String preamble);
	ITreeNode<NODETYPE> deepCopy();
}
