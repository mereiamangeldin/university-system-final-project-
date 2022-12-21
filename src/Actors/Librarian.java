package Actors;

import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Interfaces.*;

public class Librarian extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vector<Order> takers;
  
    public Librarian(User user) {
        super(user);
    }

    public Librarian(User user, String id, Date hireDate, double salary, String insuranceNumber) {
        super(user, id, hireDate, salary, insuranceNumber);
        this.takers = new Vector<Order>();
    }
    
    {
		Database.getUsers().add(this);
    }
    
    public String orderBook(Order order) {
    	if(Database.getBooks().contains(order.getBook())) {
    		if(order.getBook().getQuantity() > 0) {
    			order.getBook().setQuantity(order.getBook().getQuantity() - 1);
    			takers.add(order);
    			return "Accepted";
        }
    	else {
    		return "Books over";
        }
    	}
    	else {
    		return "Not accepted";
      }
    }
  
    public void checkForDebt(Student student) {	
    	for(Order order: takers) {
    		if(student.equals(order.getStudent())) {
    			System.out.println("Yes, you have taken book: " + order.getBook().getName() + ", Author: " + order.getBook().getAuthor());
    			return;
    		}
    	}
    	System.out.println("You have no debts!");
    }
  

    public Vector<Order> getTakers() {
    	return takers;
    }
  
    public void setTakers(Vector<Order> takers) {
    	this.takers = takers;
    }
  
    public String toString() {
    	return "Librarian takers = " + takers;
    }
  
    public int hashCode() {
    	final int prime = 31;
    	int result = super.hashCode();
    	result = prime * result + Objects.hash(takers);
    	return result;
    }
  
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (!super.equals(obj)) return false;
    	if (getClass() != obj.getClass()) return false;
    	Librarian other = (Librarian) obj;
    	return Objects.equals(takers, other.takers);
    }
}