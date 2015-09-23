package nl.hiephiepmuziek.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonManagedReference
//	@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User user;
	
	@JsonManagedReference // remove???
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="products_orders", joinColumns=@JoinColumn(name="order_id"), inverseJoinColumns=@JoinColumn(name="product_id")) 
	private Set<Product> products;
	
	private BigDecimal orderTotal;
	private Date orderDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
