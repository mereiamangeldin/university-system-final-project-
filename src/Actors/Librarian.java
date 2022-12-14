package Actors;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import Attributes.*;

public class Librarian extends Employee {
    private Vector<Order> takers;
  
    public Librarian() {
        super();
    }

    public Librarian(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
        super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
        this.takers = new Vector<Order>();
    }
    
    {
    	Database.getLibrarians().add(this);
    }
    
    public String orderBook(Order o) {
    	if(Database.getBooks().contains(o.getBook())) {
    		if(o.getBook().getQuantity() > 0) {
    			o.getBook().setQuantity(o.getBook().getQuantity() - 1);
    			takers.add(o);
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
  
    public void checkForDebt(Student s) {	
    	for(Order o: takers) {
    		if(s.equals(o.getStudent())) {
    			System.out.println("Yes, you have taken book: " + o.getBook().getName() + ", Author: " + o.getBook().getAuthor());
    			return;
    		}
    	}
    	System.out.println("You have no debts!");
    }
  
    public void makeRequest(Request request, Employee employee) {    
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