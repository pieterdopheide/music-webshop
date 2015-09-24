package nl.hiephiepmuziek.control;

import java.security.Principal;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.dao.OrderDao;
import nl.hiephiepmuziek.dao.UserDao;
import nl.hiephiepmuziek.dao.UserRoleDao;
import nl.hiephiepmuziek.model.Order;
import nl.hiephiepmuziek.model.User;
import nl.hiephiepmuziek.model.UserRole;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(method=RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void registerUser(@RequestBody User user) {
		try {
			user.setEnabled(true);
			
			UserRole userRole = new UserRole();
			userRole.setUserName(user.getUserName());
			userRole.setRole("ROLE_USER");
			
			System.out.println("Trying to register " + user.getUserName());
			System.out.println("with password: " + user.getPassword());
			System.out.println("and id: " + user.getId());
			System.out.println("Role of " + userRole.getUserName() + " is " + userRole.getRole());
			
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
			UserDao userDao = context.getBean(UserDao.class);
			userDao.saveUser(user);
			UserRoleDao userRoleDao = context.getBean(UserRoleDao.class);
			userRoleDao.saveUserRole(userRole);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/orders")
	public List<Order> orderHistory() {
		// Get username of currently logedin user
		// Use username to get user ID from db
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		UserDao userDao = context.getBean(UserDao.class);
		
		OrderDao orderDao = context.getBean(OrderDao.class);
		List<Order> orderList = orderDao.list(userDao.getUser(userName).getId());
		
//		System.out.println("Found " + orderList.size() + " orders");
//		System.out.println("First order found: " + orderList.get(0).getUser().getUserName());
		
//		return orderList.get(0);
		return orderList;
	}

}
