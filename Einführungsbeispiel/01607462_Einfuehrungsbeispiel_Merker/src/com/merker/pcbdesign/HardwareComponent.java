/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;


abstract class HardwareComponent {
	private String id;
	private float price;
	
	public HardwareComponent(String id, float price) {
		this.setId(id);
		this.setPrice(price);
	}
	
	
	// getter | setter methods
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
