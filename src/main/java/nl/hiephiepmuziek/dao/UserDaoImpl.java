package nl.hiephiepmuziek.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hiephiepmuziek.model.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("rawtypes")
	@Override
	public User getUser(String userName) {
		Session session = this.sessionFactory.openSession();
//		User user = (User) session.get(User.class, userName);
		
		Query query = session.createQuery("FROM User o WHERE o.userName = :userName");
		query.setParameter("userName", userName);
		List userList = query.list();
		User user = (User) userList.get(0);
		
		return user;
	}

	@Override
	public void saveUser(User user) {
		Session session = this.sessionFactory.openSession();
		session.save(user);
		session.close();
	}

}
