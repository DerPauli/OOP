/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;
import domain.copy.IDeepCopy;

public interface IProduct extends IDeepCopy {
	
	String getName();
	float getPrice();

}
