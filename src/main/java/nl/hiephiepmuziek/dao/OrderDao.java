package nl.hiephiepmuziek.dao;

import java.util.List;

import nl.hiephiepmuziek.model.Order;

public interface OrderDao {
	
	public List<Order> list(int userId);
	
	public void saveOrder(Order order);

}
