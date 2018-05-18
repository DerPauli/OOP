/**
 * @author Paul Merker
 * @matrNr 01607462
 */
package util;


public enum ProductCategory{
	BEVERAGE("Beverage"),
	DEFAULT("Default"),
	FOOD("Food");
	
	private String label;
	ProductCategory() {}
	
	private ProductCategory(String label) {
		this.label = label;
	}
	
	String getLabel() {
		return this.label;
	}
	
	
	

}
