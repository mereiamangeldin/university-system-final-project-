package Actors;

import java.io.Serializable;
import java.util.Date;
import Attributes.*;
import Interfaces.*;
/**Dean signs official documents.*/

public class Dean extends Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	public Dean(User user) {
    	super(user);
    }
    
    public Dean(User user, String id, Date hireDate, double salary, String insuranceNumber) {
		super(user, id, hireDate, salary, insuranceNumber);
    }
    
    {
		Database.getUsers().add(this);
    }
    /**
     * gets request from Manager and decides to sign or not
     * */
    public boolean signRequest(Request r) {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Dean: %s signed request of date: %s", getUsername(), r.getDateOfRequest())));
        if(r.getDescription().length() >= 25) return true;
        	return false;
    }
    
	public String toString() {
		return "Dean [viewStudent()=" + viewStudent() + ", getId()=" + getId() + ", getHireDate()=" + getHireDate()
				+ ", getSalary()=" + getSalary() + ", getInsuranceNumber()=" + getInsuranceNumber() + ", getEmail()="
				+ getEmail() + ", toString()=" + super.toString() + ", hashCode()=" + hashCode() + ", logout()="
				+ logout() + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getName()="
				+ getName() + ", getSurname()=" + getSurname() + ", getDateOfBirth()=" + getDateOfBirth()
				+ ", getLogged()=" + getLogged() + ", getFullName()=" + getFullName() + ", getClass()=" + getClass()
				+ "]";
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		return true;
	} 
}