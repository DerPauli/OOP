/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package bonus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import domain.Restaurant;
import domain.DuplicateProductException;
import domain.product.*;
import java.io.File;
import java.io.FileNotFoundException;


public class RestaurantChain {
	private String name;
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	private List<IProduct> products = new ArrayList<IProduct>();
	
	
	public RestaurantChain(String name) {
		this.name = name;
		
			try {
				this.readFromFile();
			} catch (DuplicateRestaurantException e) {
				e.printStackTrace();
			} catch (DuplicateProductException e) {
				System.out.println();
				e.printStackTrace();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("No Configfile found " + e);
			}
	}
	
	public RestaurantChain(String name, List<IProduct> products) {
		this.name = name;
		this.products.addAll(products);
	}
	
	private void readFromFile() throws FileNotFoundException, DuplicateRestaurantException, DuplicateProductException {
		File cFile = new File("Restaurantkette.txt");
 		Scanner scanner = new Scanner(cFile);
 		
 		while(scanner.hasNextLine()) {
			try {
				//try readling the next line
				String line = scanner.nextLine();
				String[] sections = line.split(";");
				boolean contains = false;
				
				if(this.restaurants.isEmpty()) {
					this.addRestaurant(new Restaurant(sections[0]));
				}
				
				for(Restaurant res : this.restaurants) {
					if(res.getName().equals(sections[0])) {
						
						res.createTable(sections[1], Integer.valueOf(sections[2])); // [1] === table ident; [2] === seats
						
						try {
							res.addProduct(this.products);
						}
						catch(DuplicateProductException exe) {}
						contains = true;
					} else {
						continue;
					}
				}
				if(!contains) {
					Restaurant res = new Restaurant(sections[0]);
					
					this.addRestaurant(res);
					res.createTable(sections[1], Integer.valueOf(sections[2]));
					res.addProduct(this.products);
				}
			}
			catch(NumberFormatException exe)
			{
				System.out.println("Error whilst making entry to Restaurants");
			}			
		}
		scanner.close();
 		
	}
	
	public boolean addProductToChain(IProduct product) throws DuplicateProductException {
		if(product != null) {
			for(IProduct prod : this.products) {
				if(prod.equals(product)) {
					throw new DuplicateProductException(product);
				}
			}
			this.products.add((IProduct)product.deepCopy());
			for(Restaurant res : this.restaurants) {
				res.addProduct((IProduct)product.deepCopy());
			}
			return true;
		}
		return false;
	}
	
	public boolean addProductsToChain(Collection<IProduct> products) throws DuplicateProductException {
		for(IProduct prod : products) {
			if(this.products.contains(prod)) {
				throw new DuplicateProductException(prod);
			}
			else {
				if(prod != null) { // proceed
					this.products.add(prod);
					for(Restaurant res : this.restaurants) {
						res.addProduct(prod);
					}
				} // else skip
			}
		}
		return true;
	}

	private boolean addRestaurant(Restaurant restaurant) throws DuplicateRestaurantException {
		if(this.restaurants.contains(restaurant))
			throw new DuplicateRestaurantException(restaurant);
		
		if(restaurant != null) {
			this.restaurants.add(restaurant);
			return true;
		}
		return false;
	}
	
	public String toString() {
		String text = "RestaurantChain " + this.getName() + " [ ; ";
		
		for(Restaurant res : this.restaurants) {
			text += res.getName() + " ; ";
		}

		return text += "]";
	}

	public String getName() {
		return name;
	}
	
	public List<Restaurant> getRestaurants() {
		return this.restaurants;
	}
	
	public List<IProduct> getProducts() {
		List<IProduct> ret = new ArrayList<IProduct>();
		
		for(IProduct elements : this.products) {
			ret.add((IProduct)elements.deepCopy());
		}
		return ret;
	}
}
