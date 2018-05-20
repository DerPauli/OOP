/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package util.searchable;

public interface ISearchFilter {
	boolean searchFilterFunction(Object originalObject, Object compareObject);
}
