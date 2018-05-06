/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain;

public class Table extends Object{
	private String id;
	private int seats;

	public Table(String id) {
		this.id = id;
		this.seats = 2;
	}
	
	public Table(String id, int seats) {
		this.id = id;
		this.seats = seats;
	}
	
	public int getSeatCount() {
		return this.seats;
	}
	
	public String getTableIdentifier() {
		return this.id;
	}
	
	public String toString() {
		return new String("Identifier: " + this.getTableIdentifier() + " | Seats: " + this.getSeatCount());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Table) {
			Table tempTbl = (Table) obj;
			if((this.getTableIdentifier() == tempTbl.getTableIdentifier()) && 
				(this.getSeatCount() == tempTbl.getSeatCount())) {
				return true;
			}
		}
		return false;
	}

}
