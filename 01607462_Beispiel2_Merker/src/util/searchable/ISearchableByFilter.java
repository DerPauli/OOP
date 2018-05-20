/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package util.searchable;

import java.util.Collection;

public interface ISearchableByFilter<T> {
	Collection<T> searchByFilter(ISearchFilter filter, Object compareObject);
}
