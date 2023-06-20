package com.api.model;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BOOK")
public class Book extends Product implements Comparable<Product>{

	private String genre;
	private String author;
	private String publications;
	
	public Book() {}

	public Book(String productName,float price,String genre, String author, String publications) {
		super(productName,price);
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, genre, publications);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(genre, other.genre)
				&& Objects.equals(publications, other.publications);
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
