package nl.hiephiepmuziek.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.dao.ProductDao;
import nl.hiephiepmuziek.model.Product;
import nl.hiephiepmuziek.model.ShoppingCart;

@Controller
@Scope("request")
@RestController
@RequestMapping(value="/rest/shopping-cart")
public class ShoppingCartController {
	
    @Autowired
    private ShoppingCart shoppingcart;
    
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> getProducts() {
		return shoppingcart.getCart();
	}
	
	@RequestMapping(value="/count", method = RequestMethod.GET)
	public String getCount() {
		int count = shoppingcart.productCount();
		return String.valueOf(count);
	}
	
	@RequestMapping(value="/total", method = RequestMethod.GET)
	public String getTotal() {
		BigDecimal count = shoppingcart.total();
		return String.valueOf(count);
	}
	
	@RequestMapping(value="/add/{id}", method = RequestMethod.POST)
	public void addProduct(@PathVariable int id) {
		System.out.println("Added product with id: " + id + " to cart");
		
		Product product = null;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		ProductDao productDao = context.getBean(ProductDao.class);
		product = productDao.getProduct(id);
		System.out.println("Found: " + product.getName());
		shoppingcart.addToCart(product);
		System.out.println("Cart: " + shoppingcart.productCount());
	}
	
	@RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)
	public void removeProduct(@PathVariable int id) {
		System.out.println("Remove product with id: " + id);
		shoppingcart.removeFromCart(id);
	}

}
