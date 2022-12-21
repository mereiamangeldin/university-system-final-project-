package Actors;
import java.util.*;
import java.io.Serializable;
import java.text.*;
import Attributes.*;

public class Admin extends Employee implements Serializable  {

	private static final long serialVersionUID = 1L;

	public Admin() {};
	
	public Admin(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
	}
	
	{
		Database.getUsers().add(this);
	}

    public void addUser(User u) {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s added user: %s", getFullName(), u.getFullName())));
    }
    
    public void removeUser(User u) {
		Database.getUsers().remove(u);
		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s removed user: %s", getFullName(), u.getUsername())));
    }

    public void updateUser(User u, int type, String toChange) throws Exception {
    	if(type == 1) {              // change username
    		u.setUsername(toChange);
    	}
    	else if(type == 2) {         // change name 
    		u.setName(toChange);
    	}
    	else if(type == 3) {         // change surname 
    		u.setSurname(toChange);
    	}
    	else if(type == 4) {        // change password 
    		u.setPassword(toChange);
    	}
    	else if(type == 5) {        // change date date of birth 
    		u.setDateOfBirth(new SimpleDateFormat("yyyy/MM/dd").parse(toChange));
    	}
		Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s updated user: %s", getFullName(), u.getFullName())));
    }
    
    public Vector<Action> seeUsersActions() {
    	return Database.getUserActions();
    }
    
    public Vector<Action> seeUserActions(User u) {
    	Vector<Action> v = new Vector<Action>();
    	for(Action a : Database.getUserActions()) {
    		if(a.equals(u)) {
    			v.add(a);
    		}
    	}
    	return v;
    }
   
    public void blockStudentsWhoNotPayForSf(Accountant a) {
    	for(Student s : a.getNotPayForStudentFee()) {
    		s.setIsBlocked(true);
    		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s blocked student: %s", getFullName(), s.getFullName())));
    	}
    }
}