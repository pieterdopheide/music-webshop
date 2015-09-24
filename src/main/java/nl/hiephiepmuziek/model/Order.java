package nl.hiephiepmuziek.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name="orders")
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="order_id")
	private int id;
	
//	@JsonManagedReference
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="user_id", insertable = false, updatable = false)
//	private User user = new User();
	
	private int userId;
	
	@JsonManagedReference
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="products_orders", joinColumns=@JoinColumn(name="orders_id"), inverseJoinColumns=@JoinColumn(name="products_id")) 
	private Set<Product> products = new HashSet<>();
	
	private BigDecimal orderTotal;
	private String orderDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//		if(!user.getOrderHistory().contains(this)) {
//			user.addOrder(this);
//		}
//	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
		if (!product.getOrders().contains(this)) {
			product.addOrder(this);
		}
	}

}
