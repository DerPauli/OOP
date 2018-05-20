/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package util.searchable;

public class TextSearchIgnoreCaseFilter extends Object implements ISearchFilter {

	public TextSearchIgnoreCaseFilter() {}

	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		if(originalObject == null || compareObject == null)
			return false;
		return (originalObject.toString().equalsIgnoreCase(compareObject.toString())) ? true : false;
	}

}
