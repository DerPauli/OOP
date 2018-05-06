/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.record;

public abstract class Record extends Object implements IRecord {

	private long id;
	
	public Record(long identifier) {
		this.id = identifier;
	}

	@Override
	public long getIdentifier() {
		return this.id;
	}

}
