/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;

public class Capacitor extends HardwareComponent {

	private float capacitorValue;
	
	public Capacitor(String id, float price, float capacitorValue) {
		super(id, price);
		this.capacitorValue = capacitorValue;
	}

	public float getCapacitorValue() {
		return capacitorValue;
	}

	public void setCapacitorValue(float capacitorValue) {
		this.capacitorValue = capacitorValue;
	}

}
