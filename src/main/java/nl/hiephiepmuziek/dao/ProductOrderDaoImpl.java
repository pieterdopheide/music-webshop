package nl.hiephiepmuziek.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hiephiepmuziek.model.ProductOrder;

public class ProductOrderDaoImpl implements ProductOrderDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public void saveProductOrder(ProductOrder productOrder) {
		Session session = this.sessionFactory.openSession();
		session.save(productOrder);
		session.close();
	}
	
}
