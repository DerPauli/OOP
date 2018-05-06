/***
 * 
 * @author Merker
 * @matrNr 01607462
 * 
***/

package com.merker.pcbdesign;


public class Configurator {

	public static void main(String[] args) {
		PCB pcb = new PCB();
		
		Capacitor cap1 = new Capacitor("Capacitor 50F", 5, 50);
		Capacitor cap2 = new Capacitor("Capacitor 40F", 3, 40);
		Capacitor cap3 = new Capacitor("Capacitor 22F", 1, 22);
		Capacitor cap4 = new Capacitor("Capacitor 16F", 0.5f, 16);
		
		Resistor res1 = new Resistor("32 Ohm Resistor", 400, 32);
		Resistor res2 = new Resistor("3k2 Ohm Resistor", 300, 3200);
		Resistor res3 = new Resistor("2k4 Ohm Resistor", 150.79f, 2400);
		Resistor res4 = new Resistor("32k5 Ohm Resistor", 602.4f, 32500);
		
		
		pcb.placeComponent(cap1);
		pcb.placeComponent(cap2);
		pcb.placeComponent(cap3);
		pcb.placeComponent(res1);
		pcb.placeComponent(res2);
		pcb.placeComponent(res3);
		
		CircuitPath cp = new CircuitPath(res4, cap4);
		pcb.addConnection(cp);
		
		pcb.connectComponents(res1, cap1);
		pcb.connectComponents(res2, cap2);
		pcb.connectComponents(res3, cap3);
		
		pcb.showConnectionDetails();

	}

}
