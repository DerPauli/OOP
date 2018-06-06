package cashregister;

import java.util.UUID;

public class ShoppingCartFactory {
	private static long SHOPPING_CART_ID;
	
	public ShoppingCartFactory() {}
	
	public static IShoppingCart createShoppingCart() {
		SHOPPING_CART_ID = generateUUID();
		return new ShoppingCart(SHOPPING_CART_ID);
	}
	
	public static IShoppingCart createShoppingCart(long id) {
		SHOPPING_CART_ID = generateUUID();
		return new ShoppingCart(SHOPPING_CART_ID);
	}
	
	// create unique identifier and limit it to 8 bits
	// https://stackoverflow.com/a/1389747/8641285
	private static long generateUUID() {
		return UUID.randomUUID().getLeastSignificantBits() % 256;
	}
}
