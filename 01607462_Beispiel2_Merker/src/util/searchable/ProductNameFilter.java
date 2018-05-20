/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package util.searchable;

import domain.product.IProduct;

public class ProductNameFilter extends Object implements ISearchFilter {

	public ProductNameFilter() {}

	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		if(originalObject == null || compareObject == null)
			return false;
		
		if(originalObject instanceof IProduct) {
			if(compareObject instanceof IProduct) {
				// both products
				return (((IProduct) originalObject).getName() == ((IProduct) compareObject).getName()) ? true : false;
			} else {
				// original product compare object
				return (compareObject.toString().equalsIgnoreCase(((IProduct) originalObject).getName())) ? true : false;
			}
			
		} else {
			if(compareObject instanceof IProduct) {
				// original object compare product
				return (originalObject.toString().equalsIgnoreCase(((IProduct) compareObject).getName())) ? true : false;
			} else {
				// both objects
				return (originalObject.toString().equalsIgnoreCase(compareObject.toString())) ? true : false;
			}
		}
		
	}

}
