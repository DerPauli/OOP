package util.searchable;

import domain.product.Product;

public class ProductNameFilter extends Object implements ISearchFilter {

	public ProductNameFilter() {}

	@Override
	public boolean searchFilterFunction(Object originalObject, Object compareObject) {
		if(originalObject == null || compareObject == null)
			return false;
		
		if(originalObject instanceof Product) {
			if(compareObject instanceof Product) {
				// both products
				return (((Product) originalObject).getName() == ((Product) compareObject).getName()) ? true : false;
			} else {
				// original product compare object
				return (compareObject.toString().equalsIgnoreCase(((Product) originalObject).getName())) ? true : false;
			}
			
		} else {
			if(compareObject instanceof Product) {
				// original object compare product
				return (originalObject.toString().equalsIgnoreCase(((Product) compareObject).getName())) ? true : false;
			} else {
				// both objects
				return (originalObject.toString().equalsIgnoreCase(compareObject.toString())) ? true : false;
			}
		}
		
	}

}
