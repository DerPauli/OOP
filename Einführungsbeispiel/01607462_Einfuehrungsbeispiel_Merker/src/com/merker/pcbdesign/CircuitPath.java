/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;

import com.merker.pcbdesign.HardwareComponent;


public class CircuitPath {
	
	private HardwareComponent hwComponent1, hwComponent2;
	
	public CircuitPath(HardwareComponent hwComponent1, HardwareComponent hwComponent2) {
		this.hwComponent1 = hwComponent1;
		this.hwComponent2 = hwComponent2;
	}
	
	
	public HardwareComponent getHwComponent1() {
		return this.hwComponent1;
	}
	
	public HardwareComponent getHwComponent2() {
		return this.hwComponent2;
	}

}
