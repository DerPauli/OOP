package cashregister;

public class ShoppingCartFactory {
	private static long SHOPPING_CART_ID = 0;
	
	public ShoppingCartFactory() {}
	
	public static IShoppingCart createShoppingCart() {
		SHOPPING_CART_ID++;
		return new ShoppingCart(SHOPPING_CART_ID);
	}
	
	public static IShoppingCart createShoppingCart(long id) {
		if(SHOPPING_CART_ID < id) {
			SHOPPING_CART_ID = ++id;
		}
		return new ShoppingCart(SHOPPING_CART_ID);
	}
}
