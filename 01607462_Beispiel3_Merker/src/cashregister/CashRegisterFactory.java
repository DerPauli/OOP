/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;


public class CashRegisterFactory {
	private static long CASH_REGISTER_ID = 0;
	
	public CashRegisterFactory() {}
	
	public static ICashRegister createCashRegister() {
		CASH_REGISTER_ID++;
		return new CashRegister(CASH_REGISTER_ID);
	}
}
