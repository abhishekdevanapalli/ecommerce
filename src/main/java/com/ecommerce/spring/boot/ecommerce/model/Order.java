package com.ecommerce.spring.boot.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {

	private String email;
	@Id
	private int id;

	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * private Product product;
	 * 
	 * public Product getProduct() { return product; }
	 * 
	 * public void setProduct(Product product) { this.product = product; }
	 */

	/*
	 * private String orderId;
	 * 
	 * public String getOrderId() { return orderId; }
	 * 
	 * public void setOrderId(String orderId) { this.orderId = orderId; }
	 */

	@OneToMany(mappedBy = "order")
	private List<Product> product;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
