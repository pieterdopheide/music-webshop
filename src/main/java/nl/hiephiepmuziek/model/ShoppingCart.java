package nl.hiephiepmuziek.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
@Scope(value="session")
public class ShoppingCart implements Serializable {
	
	private List<Product> cart = new ArrayList<Product>();
	
	public List<Product> getCart() {
		return cart;
	}
	
	public void addToCart(Product product) {
		cart.add(product);
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
		return cart.size();
	}
	
	public BigDecimal total() {
		BigDecimal totalCost = BigDecimal.ZERO;
		
		for (Product product : cart) {
			totalCost = totalCost.add(product.getPrice());
		}
		return totalCost;
	}

}
