
package com.ecommerce.spring.boot.ecommerce.model;

//@Component
public class Status {
	private String message;
	private Product product;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}