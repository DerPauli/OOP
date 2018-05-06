/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;

import java.util.ArrayList;
import java.util.Collection;


public class PCB {
	
	private Collection<HardwareComponent> hwComponents = new ArrayList<HardwareComponent>();
	private Collection<CircuitPath> connections = new ArrayList<CircuitPath>();
	
	
	public void placeComponent(HardwareComponent hw) {
		this.hwComponents.add(hw);
	}
	
	public Boolean connectComponents(HardwareComponent hw1, HardwareComponent hw2) {
		for(HardwareComponent i_hw1 : this.hwComponents) {
			if (i_hw1.equals(hw1)) {
				for(HardwareComponent i_hw2 : this.hwComponents) {
					if(i_hw2.equals(hw2)) {
						this.connections.add(new CircuitPath(hw1, hw2));
						return true;
					}
				} 
			}
		}
		return false;
	}
	
	public void addConnection(CircuitPath connection) {
		this.connections.add(connection);
		this.hwComponents.add(connection.getHwComponent1());
		this.hwComponents.add(connection.getHwComponent2());
	}
	
	public float calculatePrice() {
		float oPrice = 0;
		
		for(HardwareComponent i_hw : this.hwComponents) {
			oPrice += i_hw.getPrice();
		}
		return oPrice;
	}
	
	public void showConnectionDetails() {
		
		for(CircuitPath i_path : this.connections) {
			System.out.println(i_path.getHwComponent1().getId() + " <---- Connected ----> " + i_path.getHwComponent2().getId());
		}
		
		
		System.out.println("Gesamtpreis der Platine beträgt: " + this.calculatePrice());
	}

}
