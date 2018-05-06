/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain.product;
import ict.basics.IDeepCopy;

public interface IProduct extends IDeepCopy {
	
	String getName();
	float getPrice();

}
