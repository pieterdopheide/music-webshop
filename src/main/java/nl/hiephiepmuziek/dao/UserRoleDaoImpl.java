package nl.hiephiepmuziek.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import nl.hiephiepmuziek.model.UserRole;

public class UserRoleDaoImpl implements UserRoleDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public void saveUserRole(UserRole userRole) {
		Session session = this.sessionFactory.openSession();
		session.save(userRole);
		session.close();
	}

}
