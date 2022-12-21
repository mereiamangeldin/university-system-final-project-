package Attributes;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
	private int id;
    private String name;
    private int quantity;
    private String author;
    private int yearOfPublication;
    private static int numberOfBooks;
    
    public Book(String name, int quantity, String author, int yearOfPublication) {
    	this.name = name;
    	this.quantity = quantity;
    	this.author = author;
    	this.yearOfPublication = yearOfPublication;
    }
    
    {
    	Database.getBooks().add(this);
    	numberOfBooks++;
    	id = numberOfBooks;
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYearOfPublication() {
		return yearOfPublication;
	}
	
	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public int hashCode() {
		return Objects.hash(author, name, quantity, yearOfPublication);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(name, other.name) && quantity == other.quantity
				&& yearOfPublication == other.yearOfPublication;
	}

	public String toString() {
		return String.format("Book [id: %s, name: %s, quantity: %s, year of publication: %s", id, name, quantity, yearOfPublication);
	}
}