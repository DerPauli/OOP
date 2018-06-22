import cashregister.CashRegisterFactory;
import cashregister.ICashRegister;
import cashregister.ui.CashRegisterConsoleUI;
import cashregister.ui.CashRegisterGUI;
import cashregister.ui.ICashRegisterUI;
import domain.product.IProduct;
import domain.product.ProductCategory;
import domain.product.SimpleProduct;
import managementserver.IManagementServer;
import managementserver.ManagementServer;
import managementtools.ManagementServerViewer;
import warehouse.IListener;
import warehouse.IWarehouse;
import warehouse.Warehouse;
import warehouse.ui.WarehouseManager;

public class RestaurantManagementSystem {

	public static void main(String[] args) {

		// Create ManagementServer and Warehouse
		IManagementServer mngServer = ManagementServer.GET_INSTANCE();
		IWarehouse warehouse = Warehouse.GET_INSTANCE();
		
		// TODO: register mngServer as listener at the warehouse
		((Warehouse)warehouse).registerListener((IListener<IProduct>) mngServer);

		// TODO: add Products to warehouse
		SimpleProduct s1 = new SimpleProduct("S1", 13.4f);
		s1.setCategory(ProductCategory.FOOD);
		SimpleProduct s2 = new SimpleProduct("S2", 14.4f);
		SimpleProduct s3 = new SimpleProduct("S3", 15.4f);
		s3.setCategory(ProductCategory.BEVERAGE);
		SimpleProduct s4 = new SimpleProduct("S4", 16.4f);
		s4.setCategory(ProductCategory.DEFAULT);
		warehouse.addProduct(s1);
		warehouse.addProduct(s2);
		warehouse.addProduct(s3);
		warehouse.addProduct(s4);
		
		// TODO: create CashRegister and register it as an observer at the mngServer
		ICashRegister cash = CashRegisterFactory.createCashRegister();
		mngServer.addCashRegister(cash);
		
		// TODO: Create GUI for CashRegister
		ICashRegisterUI ui = new CashRegisterConsoleUI();

		new WarehouseManager(warehouse);
		new ManagementServerViewer(ManagementServer.GET_INSTANCE());

		// TODO: register CashRegisterGUI as an UI at the previously created cashRegister
		cash.registerCashRegisterUI(ui);
	}
}
