package com.ecommerce.spring.boot.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
//	@GeneratedValue
	private long id;

	private String user;

	@Size(min = 10, message = "Enter at least 10 Characters...")
	private String desc;

	private String mrp;
	private String sellingPrice;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email", referencedColumnName = "email")
	private Order order;

	public Product() {
		super();
	}

	public Product(long id, String user, @Size(min = 10, message = "Enter at least 10 Characters...") String desc,
			String mrp, String sellingPrice, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.mrp = mrp;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", user=" + user + ", desc=" + desc + ", mrp=" + mrp + ", sellingPrice="
				+ sellingPrice + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mrp == null) ? 0 : mrp.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((sellingPrice == null) ? 0 : sellingPrice.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (mrp == null) {
			if (other.mrp != null)
				return false;
		} else if (!mrp.equals(other.mrp))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sellingPrice == null) {
			if (other.sellingPrice != null)
				return false;
		} else if (!sellingPrice.equals(other.sellingPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}