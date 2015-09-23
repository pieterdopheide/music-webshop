package nl.hiephiepmuziek.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hiephiepmuziek.model.Order;

public class OrderDaoImpl implements OrderDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> list(int userId) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("FROM Order WHERE user_id = :userId");
		query.setParameter("userId", userId);
        List<Order> orderList = query.list();
        session.close();
        return orderList;
	}

	@Override
	public void saveOrder(Order order) {
		Session session = this.sessionFactory.openSession();
		session.save(order);
		session.close();
	}

}
