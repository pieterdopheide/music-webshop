package nl.hiephiepmuziek.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.model.Product;
import nl.hiephiepmuziek.model.ShoppingCart;

@RestController
public class ShoppingCartController {
	
	@Autowired
    HttpServletRequest servletRequest;
	
	private ShoppingCart getShoppingCart() {
		ShoppingCart sc = null;

		HttpSession session = servletRequest.getSession();

		if (session.getAttribute("shopping-cart") == null) {
			sc = new ShoppingCart();
			session.setAttribute("shopping-cart", sc);
		} else {
			sc = (ShoppingCart) session.getAttribute("shopping-cart");
		}
		
		return sc;
	}
	
	@RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		products.addAll(getShoppingCart().getCart());
		return products;
	}
	
	@RequestMapping(value = "/shopping-cart/count", method = RequestMethod.GET)
	public String getCount() {
		int count = getShoppingCart().productCount();
		return String.valueOf(count);
	}
	
	@RequestMapping(value = "/shopping-cart/total", method = RequestMethod.GET)
	public String getTotal() {
		BigDecimal count = getShoppingCart().total();
		return String.valueOf(count);
	}
	
	@RequestMapping(value = "/shopping-cart/count", method = RequestMethod.POST)
	public void addProduct(Product product) {
		getShoppingCart().addToCart(product);
	}
	
	@RequestMapping(value = "/shopping-cart/{id}", method = RequestMethod.GET)
	public void removeProduct(@PathVariable int id) {
		System.out.println("Remove product with id: " + id);
		getShoppingCart().removeFromCart(id);
	}

}
