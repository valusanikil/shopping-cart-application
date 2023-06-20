package com.api.model;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "APPARAL")
public class Apparal extends Product implements Comparable<Product>{
	
	private String type;
	
	private String brand;
	
	private String design;
	
	public Apparal() {}

	public Apparal(String productName, float price, String type, String brand, String design) {
		super(productName,price);
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, design, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparal other = (Apparal) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(design, other.design)
				&& Objects.equals(type, other.type);
	}

	@Override
	public int compareTo(Product o) {
		if(this.productId > o.productId) {
			return 1;
		}
		if(this.productId < o.productId) {
			return -1;
		}
		return 0;
	}
	
	
}
