package nl.hiephiepmuziek.control;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.dao.ProductDao;
import nl.hiephiepmuziek.model.Product;

@RestController
@RequestMapping(value="/rest/products")
public class ProductsController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getProducts() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		ProductDao productDao = context.getBean(ProductDao.class);
		List<Product> products = productDao.list();
		context.close();
		
		return products;
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public String getCount() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		ProductDao productDao = context.getBean(ProductDao.class);
		int count = productDao.list().size();
		return String.valueOf(count);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable int id) {
		Product product = null;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		ProductDao productDao = context.getBean(ProductDao.class);
		
		for (Product p : productDao.list()) {
			if (p.getId() == id) {
				product = p;
			}
		}
		
		if (product == null) {
			throw new RuntimeException("Get: Product with " + id + " not found");
		}
		
		return product;
	}

}
