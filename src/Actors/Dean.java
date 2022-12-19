package Actors;

import java.util.Date;
import Attributes.*;

public class Dean extends Employee {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dean() {
    	super();
    }
    
    public Dean(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
    }
    
    {
    	Database.getDeans().add(this);
    }

    public boolean signRequest(Request r) {
		Database.getUserActions().add(String.format("User: %s signed request of date: %s", super.getUsername(), r.getDateOfRequest()));
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