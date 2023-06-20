package com.api.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Cart implements Comparable<Cart> {

	@Id
	private Long cartId;
	private Long totalProducts;
	private double totalAmount;
	
	@OneToOne(mappedBy = "cart")
	@JsonBackReference
	private User user;
	
	@OneToMany(
			cascade = CascadeType.ALL
	)
	@JoinTable(
			name = "cart_products_map",
			joinColumns = @JoinColumn(
					name="cart_id",
					referencedColumnName = "cartId"
			),
			inverseJoinColumns = @JoinColumn(
					name="product_id",
					referencedColumnName = "productId"
			)
	)
	private List<Product> products;
	
	@OneToMany(
	 cascade =  CascadeType.ALL		
	)
	@JoinTable(
			name = "products_quantity_map",
			joinColumns=@JoinColumn(
					name = "cart_id",
					referencedColumnName = "cartId"
			),
			inverseJoinColumns = @JoinColumn(
					name= "quantity_id",
					referencedColumnName= "id"
			)
			
	)
	private List<Quantity> productQuantity;
	
	public Cart() {}

	public Cart(Long cartId, Long totalProducts,double totalAmount) {
		super();
		this.cartId = cartId;
		this.totalProducts = totalProducts;
		this.totalAmount = totalAmount;
	}

	public Cart(Long cartId, Long totalProducts, double totalAmount, User user) {
		super();
		this.cartId = cartId;
		this.totalProducts = totalProducts;
		this.totalAmount = totalAmount;
		this.user = user;
	}

	public Cart(Long cartId, Long totalProducts, double totalAmount, User user, List<Product> products,
			ArrayList<Quantity> productQuantity) {
		super();
		this.cartId = cartId;
		this.totalProducts = totalProducts;
		this.totalAmount = totalAmount;
		this.user = user;
		this.products = products;
		this.productQuantity = productQuantity;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Quantity> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(List<Quantity> quantitys) {
		this.productQuantity = quantitys;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, productQuantity, products, totalAmount, totalProducts, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(productQuantity, other.productQuantity)
				&& Objects.equals(products, other.products)
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount)
				&& Objects.equals(totalProducts, other.totalProducts) && Objects.equals(user, other.user);
	}

	@Override
	public int compareTo(Cart o) {
		if(this.cartId>o.cartId) {
			return 1;
		}
		if(this.cartId<o.cartId) {
			return -1;
		}
		
		return 0;
	}
	
	
	
}
