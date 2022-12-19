package Attributes;

import java.util.Objects;

import Actors.*;

public class Order {
	private Student student;
    private Book book;
    private int monthLeft;
    
    public Order(Student student, Book book) {
		this.student = student;
		this.book = book;
	}
    
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getMonthLeft() {
		return monthLeft;
	}
	
	public void setMonthLeft(int monthLeft) {
		this.monthLeft = monthLeft;
	}
	
	public int hashCode() {
		return Objects.hash(book, monthLeft, student);
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Order other = (Order) obj;
		return Objects.equals(book, other.book) && monthLeft == other.monthLeft
				&& Objects.equals(student, other.student);
	}

	public String toString() {
		return "Order [student=" + student + ", book=" + book + ", monthLeft=" + monthLeft + "]";
	}
}