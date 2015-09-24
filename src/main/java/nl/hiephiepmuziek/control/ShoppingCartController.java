package nl.hiephiepmuziek.control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.dao.OrderDao;
import nl.hiephiepmuziek.dao.ProductDao;
import nl.hiephiepmuziek.dao.ProductOrderDao;
import nl.hiephiepmuziek.dao.UserDao;
import nl.hiephiepmuziek.model.Order;
import nl.hiephiepmuziek.model.Product;
import nl.hiephiepmuziek.model.ProductOrder;
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
	
	@SuppressWarnings("resource")
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
	
	@SuppressWarnings({ "resource" })
	@RequestMapping(value="/checkout")
	public void checkout(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// check if the shoppingcart is not null/empty and that the user is logged in
		if ((shoppingcart.getCart()  != null || !shoppingcart.getCart().isEmpty()) && auth.getName() != null) {
			Order order = new Order();
			
			Set<Product> products = new HashSet<Product>(shoppingcart.getCart());
			order.setProducts(products);
			order.setOrderTotal(shoppingcart.total());
			shoppingcart.emptyCart();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			order.setOrderDate(dateFormat.format(date));
			String userName = auth.getName();
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
			UserDao userDao = context.getBean(UserDao.class);
			order.setUserId(userDao.getUser(userName).getId());
			
			OrderDao orderDao = context.getBean(OrderDao.class);
			orderDao.saveOrder(order);

			ProductOrderDao productOrderDao = context.getBean(ProductOrderDao.class);
			ProductOrder productOrder = new ProductOrder();
			productOrder.setOrdersId(order.getId());
			for (Product product : order.getProducts()) {
				productOrder.setProductsId(product.getId());
				productOrderDao.saveProductOrder(productOrder);
			}
		}
	}

}
