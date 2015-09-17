package nl.hiephiepmuziek.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private List<Product> cart;
	
	public ShoppingCart() {
		this.cart = new ArrayList<Product>();
	}

	public List<Product> getCart() {
		return cart;
	}
	
	public void addToCart(Product product) {
		boolean add = false;
		
		for (Product p : cart) {
			if (p.getId() == product.getId()) {
//				p.setQuantity(p.getQuantity() + 1);
				add = true;
			}
		}
		
		if (!add) {
			cart.add(product);
		}
	}
	
	public void removeFromCart(int id) {
		for (Product product : cart) {
			if (product.getId() == id) {
				cart.remove(product);
				return; // Remove only one product
			}
		}
	}
	
	public int productCount() {
		int prodCount = 0;
		
		for (Product product : cart) {
//			prodCount += product.getQuantity();
		}

		return prodCount;
	}
	
	public BigDecimal total() {
		BigDecimal totalCost = BigDecimal.ZERO;
		BigDecimal itemCost = BigDecimal.ZERO;
		
		for (Product product : cart) {
			itemCost = BigDecimal.ZERO; // reset itemCost
//			itemCost = product.getPrice().multiply(new BigDecimal(product.getQuantity()));
			itemCost = product.getPrice();
			totalCost = totalCost.add(itemCost);
		}
		
		return totalCost;
	}

}
