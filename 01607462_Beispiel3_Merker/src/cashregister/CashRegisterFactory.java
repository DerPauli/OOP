package cashregister;

import java.util.UUID;

public class CashRegisterFactory {
	private static long CASH_REGISTER_ID;
	
	public CashRegisterFactory() {}
	
	public static ICashRegister createCashRegister() {
		CASH_REGISTER_ID = generateUUID();
		return new CashRegister(CASH_REGISTER_ID);
	}
	// create unique identifier and limit it to 8 bits
	// https://stackoverflow.com/a/1389747/8641285
	private static long generateUUID() {
		return UUID.randomUUID().getLeastSignificantBits() % 256;
	}

	
}
