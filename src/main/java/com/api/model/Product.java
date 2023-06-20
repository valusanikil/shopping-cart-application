package com.api.model;

import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "PRODUCT_TYPE",
		discriminatorType = DiscriminatorType.STRING
)
public class Product implements Comparable<Product> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long productId;
	
	protected String productName;
	
	protected float price;
	
	public Product() {}
	
	public Product(String productName,float price) {
		super();
		this.productName = productName;
		this.price = price;
	}

	public Product(Long productId, String productName, float price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, productId, productName);
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
		return Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName);
	}

	@Override
	public int compareTo(Product o) {
		if(this.productId>o.productId) {
			return 1;
		}
		if(this.productId<o.productId) {
			return -1;
		}
		return 0;
	}
	
	

}
