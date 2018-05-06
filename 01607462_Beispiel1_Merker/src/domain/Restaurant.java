/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import bonus.RestaurantChain;
import domain.product.ExtendedProduct;
import domain.product.IProduct;
import domain.product.JointProduct;
import domain.product.SimpleProduct;

public class Restaurant extends Object {
	
	private String restaurantName;
	private long uniqueOrderIdentifier = 0;
	private List<IProduct> productAssortment = new ArrayList<IProduct>();
	private List<Order> orderHistory = new ArrayList<Order>();
	private List<Table> tables = new ArrayList<Table>();
	

	public Restaurant(String name) {
		this.restaurantName = name;
	}
	
	public String getName() {
		return this.restaurantName;
	}
	
	public boolean createTable(String tableIdentifier) {
		for(Table tab : this.tables) {
			if(tab.getTableIdentifier() == tableIdentifier) {
				return false;
			} else {
				continue;
			}
		}
		//no entry for this uid
		this.tables.add(new Table(tableIdentifier));
		return true;
	}
	
	public boolean createTable(String tableIdentifier, int seats) {
		for(Table tab : this.tables) {
			if(tab.getTableIdentifier() == tableIdentifier) {
				return false;
			} else {
				continue;
			}
		}
		//no entry for this uid
		this.tables.add(new Table(tableIdentifier, seats));
		return true;
	}
	
	public List<String> getTableIdentifiers() {
		List<String> ids = new ArrayList<String>();
		
		for(Table tab : this.tables) {
			ids.add(tab.getTableIdentifier());
		}
		return ids;
	}
	
	public Table getSpecificTable(String identifier) {
		for(Table tab : this.tables) {
			if(tab.getTableIdentifier().equals(identifier)) {
				return tab;
			} else {
				continue;
			}
		}
		return null;
	}
	
	public boolean addProduct(IProduct product) throws DuplicateProductException {
		if(product != null) {
			if(!this.productAssortment.contains(product)) {
				this.productAssortment.add((IProduct)product.deepCopy());
				return true;
			} else {
				throw new DuplicateProductException(product);
			}
		}
		return false;
	}
	
	public boolean addProduct(Collection<IProduct> products) throws DuplicateProductException {
		
		for(IProduct product : products) {
			if(product != null) {
				if(!this.productAssortment.contains(product)) {
					this.productAssortment.add((IProduct)product.deepCopy());
					continue;
				} else {
					throw new DuplicateProductException(product);
				}
			} else {
				continue;
			}
		}
		return true;
	}
	
	public List<IProduct> getProducts() {
		return this.productAssortment;
	}
	
	public boolean orderProductForTable(Table table, IProduct product) {
		if(table != null && product != null) {
			if(this.tables.contains(table)) {
				if(this.productAssortment.contains(product)) {
					List<IProduct> tempProds = new ArrayList<IProduct>();
					tempProds.add(product);
					this.orderHistory.add(new Order(this.generateUniqueIdentifier(), table, tempProds));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean orderProductForTable(Table table, IProduct product, int count) {
		if(table != null && product != null) {
			if(this.tables.contains(table)) {
				if(this.productAssortment.contains(product)) {
					List<IProduct> tempProds = new ArrayList<IProduct>();
					for(int i = 0; i < count; ++i) {
						tempProds.add(product);
					}
					this.orderHistory.add(new Order(this.generateUniqueIdentifier(), table, tempProds));
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsProduct(IProduct compareProduct) {
		return (this.productAssortment.contains(compareProduct)) ? true : false;
	}
	
	public IProduct findProduct(String productName) {
		for(IProduct prod : this.productAssortment) {
			if(prod.getName().equals(productName))
				return prod;
			else
				continue;
		}
		return null;
	}
	
	private IProduct findProduct(IProduct compareProduct) {
		for(IProduct prod : this.productAssortment) {
			if(prod.equals(compareProduct))
				return prod;
			else
				continue;
		}
		return null;
	}

	private long generateUniqueIdentifier() {
		return (++this.uniqueOrderIdentifier);
	}
	
	public static List<IProduct> generateSimpleProducts() {
		List<IProduct> sProdList = new ArrayList<IProduct>();
		
		// TODO: use iterator
		SimpleProduct prod1 = new SimpleProduct("Fish", 8.90f);
		SimpleProduct prod2 = new SimpleProduct("Schnitzel", 11.20f);
		SimpleProduct prod3 = new SimpleProduct("Fries", 4.20f);
		SimpleProduct prod4 = new SimpleProduct("Coke", 3.90f);
		SimpleProduct prod5 = new SimpleProduct("Lemonade", 3.00f);
		
		sProdList.add(prod1);
		sProdList.add(prod2);
		sProdList.add(prod3);
		sProdList.add(prod4);
		sProdList.add(prod5);
		
		return sProdList;
	}
	
	public static List<IProduct> generateJointProducts() {
		List<IProduct> jProdList = new ArrayList<IProduct>();
		
		// TODO: remove ugly code, yikes :/
		JointProduct jProd1 = new JointProduct("Schnitzel and fries", 23.2f);
		JointProduct jProd2 = new JointProduct("Fish and Coke", 10.2f);
		JointProduct jProd3 = new JointProduct("Schnitzel, Fries and all Whine", 5f);
		
		JointProduct jTempProd1 = new JointProduct("Coke and Lemonade", 7.98f);
		JointProduct jTempProd2 = new JointProduct("Double Coke", 9.60f);
		JointProduct jTempProd3 = new JointProduct("Schnitzel and Fries", 13.90f);
		
		jTempProd3.addProduct(new SimpleProduct("Schnitzel", 11.20f));
		jTempProd3.addProduct(new SimpleProduct("Fries", 4.20f));
		
		jProd1.addProduct(new SimpleProduct("Salad", 5.60f));
		jProd1.addProduct(new SimpleProduct("Nice Fish", 20.60f));
		jProd1.addProduct(jTempProd1);
		
		jProd2.addProduct(new SimpleProduct("Even Nicer Fish", 26.60f));
		jProd2.addProduct(new SimpleProduct("Expensive XXXXXL Schnitzel", 17.60f));
		jProd2.addProduct(jTempProd2);
		
		jProd3.addProduct(new SimpleProduct("Quality Wine", 29.60f));
		jProd3.addProduct(new SimpleProduct("Spritz Wine", 0.90f));
		jProd3.addProduct(jTempProd3);
		
		jProdList.add(jProd1);
		jProdList.add(jProd2);
		jProdList.add(jProd3);
		
		return jProdList;
	}
	
	public static void main(String[] args) throws DuplicateProductException {
		// Scanner for Menu input
		Scanner input = new Scanner(System.in);
		int iSel, pSel;
		float pValue;
		String sSel, pName;
		
		// create products
		Restaurant res = new Restaurant("Exquisite Restaurant");
		// 5 simple products
		res.addProduct(Restaurant.generateSimpleProducts());
		
		// 5 extended
		// TODO: use iterator
		res.addProduct(new ExtendedProduct("ExProd1", 8.90f));
		res.addProduct(new ExtendedProduct("ExProd2", 11.20f));
		res.addProduct(new ExtendedProduct("ExProd3", 4.20f));
		res.addProduct(new ExtendedProduct("ExProd4", 3.90f));
		res.addProduct(new ExtendedProduct("ExProd5", 3.00f));
		
		
		res.addProduct(Restaurant.generateJointProducts());
		
		JointProduct join1 = new JointProduct("Menu 92", 23f);
		SimpleProduct si = new SimpleProduct("Si 1", 12.25f);
		SimpleProduct si2 = new SimpleProduct("Si 2", 1.90f);
		
		JointProduct join2 = new JointProduct("Menu 4", 23f);
		ExtendedProduct ex = new ExtendedProduct("Fish with Fish", 20.90f);
		ExtendedProduct ex2 = new ExtendedProduct("Fish with Fish", 20.90f);
		
		
		join1.addProduct(si);
		join1.addProduct(si2);
		
		join2.addProduct(ex);
		join2.addProduct(ex2);
		
		// try to create duplicate product
		try {
			res.addProduct(ex);
		} catch (DuplicateProductException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			res.addProduct(ex2);
		} catch (DuplicateProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.addProduct(join1);
		
		// create 3 Tables
		res.createTable("Table 1");
		res.createTable("Table 2");
		res.createTable("Table 3");
		
		// create 2 Orders per table
		res.orderProductForTable(res.getSpecificTable("Table 2"), join1);
		res.orderProductForTable(res.getSpecificTable("Table 2"), ex);
		
		res.orderProductForTable(res.getSpecificTable("Table 1"), res.getProducts().get(2), 2);
		res.orderProductForTable(res.getSpecificTable("Table 1"), res.findProduct(new SimpleProduct("Fries", 4.20f)));
		
		res.orderProductForTable(res.getSpecificTable("Table 3"), res.findProduct("Schnitzel"), 1);
		res.orderProductForTable(res.getSpecificTable("Table 3"), new ExtendedProduct("Chicken", 10.37f));
		
		// testing resChain
		RestaurantChain testChain = new RestaurantChain("TestChain1");
		List<IProduct> tempProds = new ArrayList<IProduct>();
		
		tempProds.addAll(generateSimpleProducts());
		System.out.println(testChain.getName());
		System.out.println(testChain.toString());
		
		for(Restaurant tRes: testChain.getRestaurants()) {
			for(String str: tRes.getTableIdentifiers()) {
				System.out.println(str);
			}
		}
		ExtendedProduct exProd = new ExtendedProduct("neues ExProd", 12.93f);
		testChain.addProductsToChain(tempProds);
		for(Restaurant rest : testChain.getRestaurants()) {
			System.out.println("\n"+rest.getName());
			for(IProduct prod : rest.getProducts()) {
				System.out.println(prod.toString());
			}
		}
		testChain.addProductToChain(exProd);
		
		
		for(Restaurant rest : testChain.getRestaurants()) {
			System.out.println("\n"+rest.getName());
			for(IProduct prod : rest.getProducts()) {
				System.out.println(prod.toString());
			}
		}
		
		
		
		// Menu
		do {
			System.out.println("\n------------- Menu -------------");
			System.out.println("0 ......................... Quit");
			System.out.println("1 ....... Search product by name");
			System.out.println("2 .............. Add new product");
			
			iSel = input.nextInt();
			
			switch(iSel) {
				case 1:
					System.out.println("Search product by name. ENTER NAME: ");
					input.nextLine();
					sSel = input.nextLine();
					System.out.println("Searching in assortment for product with name " + sSel + " ...");
					
					if(res.findProduct(sSel) != null) {
						System.out.println("Product found");
					} else {
						System.out.println("Product not found");
					}
					break;
				case 2:
					System.out.println("--------- Product Type ---------");
					System.out.println("1 ............... Simple Product");
					System.out.println("2 ............. Extended Product");
					System.out.println("3 ................ Joint Product");
					input.nextLine();
					pSel = input.nextInt();
					
					switch(pSel) {
						case 1:
							System.out.println("Enter Name for Simple Product: ");
							input.nextLine();
							pName = input.nextLine();
							
							System.out.println("Enter Price for Simple Product " + pName);
							pValue = input.nextFloat();
							
							res.addProduct(new SimpleProduct(pName, pValue));
							break;
						case 2:
							System.out.println("Enter Name for Extended Product: ");
							input.nextLine();
							pName = input.nextLine();
							
							System.out.println("Enter Price for Extended Product " + pName);
							pValue = input.nextFloat();
							
							res.addProduct(new SimpleProduct(pName, pValue));
							break;
						case 3:
							System.out.println("Enter Name for Joint Product: ");
							input.nextLine();
							pName = input.nextLine();
							
							System.out.println("Enter Discount Percentage for Joint Product " + pName);
							pValue = input.nextFloat();
							
							res.addProduct(new SimpleProduct(pName, pValue));
							break;
						default:
							System.out.println("NOT VALID!");
							break;
					}
			}
		} while (iSel != 0);
		System.exit(-1);
		
	}
	
}
