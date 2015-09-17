package nl.hiephiepmuziek.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import nl.hiephiepmuziek.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list() {
		Session session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("from Product").list();
        session.close();
        return productList;
	}

}
