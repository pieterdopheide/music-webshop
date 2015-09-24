package nl.hiephiepmuziek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username", unique=true)
	private String userName;
	private String password;
	private boolean enabled;
	
//	@JsonBackReference
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
//	private List<Order> orderHistory = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

//	public List<Order> getOrderHistory() {
//		return orderHistory;
//	}
//
//	public void setOrderHistory(List<Order> orderHistory) {
//		this.orderHistory = orderHistory;
//	}
//	
//	public void addOrder(Order order) {
//		this.orderHistory.add(order);
//		if (!order.getUser().equals(this)) {
//			order.setUser(this);
//		}
//	}

}
