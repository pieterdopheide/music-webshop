package nl.hiephiepmuziek.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hiephiepmuziek.model.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public User getUser(String userName) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.get(User.class, userName);
		return user;
	}

}
