/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;

public class Resistor extends HardwareComponent {

	private float resistorValue;
	
	public Resistor(String id, float price, float resistorValue) {
		super(id, price);
		this.resistorValue = resistorValue;
	}

	public float getResistorValue() {
		return resistorValue;
	}

	public void setResistorValue(float resistorValue) {
		this.resistorValue = resistorValue;
	}

}
