package nl.hiephiepmuziek.control;

import java.security.Principal;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.hiephiepmuziek.dao.UserDao;
import nl.hiephiepmuziek.dao.UserRoleDao;
import nl.hiephiepmuziek.model.User;
import nl.hiephiepmuziek.model.UserRole;

@RestController
public class UserController {
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/user/register", method = RequestMethod.POST)
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

}
