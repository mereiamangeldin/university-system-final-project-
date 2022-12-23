package Actors;

import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Interfaces.*;
/**Librarian takes order of Students, checking students for debt.*/

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
    /**
     * receives order from Student, checks for containing of book in Database
     * @throws Exception 
     * */
    public String orderBook(Order order) throws Exception {
    	if(Database.getBooks().contains(order.getBook())) {
    		if(order.getBook().getQuantity() > 0) {
    			order.getBook().setQuantity(order.getBook().getQuantity() - 1);
    			takers.add(order);
    			return "Accepted";
    		} 
    		else {
    			throw new Exception("Books over");
        	}
    	}
    	else {
    		return "Not accepted";
    	}
    }
    
    /**
     * checks if the student owes books to the library
     * */
    public String checkForDebt(Student student) {	
    	for(Order order: takers) {
    		if(student.equals(order.getStudent())) {
    			return "Yes, you have taken book: " + order.getBook().getName() + ", Author: " + order.getBook().getAuthor();
    		}
    	}
    	return "You have no debts!";
    }
  
    public Vector<Order> getTakers() {
    	return takers;
    }
  
    public void setTakers(Vector<Order> takers) {
    	this.takers = takers;
    }
  
    public String toString() {
    	String answer = String.format("Librarian: %s, ID: %s, username: %s", this.getFullName(), this.getId(), this.getUsername());
    	return answer;
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