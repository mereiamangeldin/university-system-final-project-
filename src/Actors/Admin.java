package Actors;
import java.util.*;
import java.io.Serializable;
import java.text.*;
import Attributes.*;
import Interfaces.*;
/**Admin is responsible for technical part of university system.*/

public class Admin extends Employee implements Serializable  {

	private static final long serialVersionUID = 1L;

	public Admin(User user) {super(user);};
	 
	public Admin(User user, String id, Date hireDate, double salary, String insuranceNumber) {
		super(user, id, hireDate, salary, insuranceNumber);
	}
	
	{
		Database.getUsers().add(this);
	}
	
	/**
	 * to add user to university system
	 * */
    public void addUser(User u) {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s added user: %s", getFullName(), u.getFullName())));
    }

	/**
	 * to remove user from university system
	 * */
    public String removeUser(User u) {
    	if(Database.getUsers().contains(u)) {
    		Database.getUsers().remove(u);
    		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s removed user: %s", getFullName(), u.getUsername())));
    		return "User was succesfully removed";
    	} else {
    		return "User not found";
    	}
    }
    
    /**
     * to update user's information
     * */
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
    /**
     * allows to see all user actions
     * */
    public Vector<Action> seeUsersActions() {
    	return Database.getUserActions();
    }
    
    public Vector<Action> seeUserActions(User u) {
    	Vector<Action> v = new Vector<Action>();
    	for(Action a : Database.getUserActions()) {
    		if(a.getUser().equals(u)) {
    			v.add(a);
    		}
    	}
    	return v;
    }
    /**
     * to block the student, if he has not made the required payments
     * */
    public void blockStudentsWhoNotPayForSf(Accountant a) {
    	for(Student s : a.getNotPayForStudentFee()) {
    		s.setIsBlocked(true);
    		Database.getUserActions().add(new Action(this, new Date(), String.format("Admin: %s blocked student: %s", getFullName(), s.getFullName())));
    	}
    }
    
    public String toString() {
    	String answer = String.format("Admin: %s, ID: %s, username: %s", this.getFullName(), this.getId(), this.getUsername());
    	return answer;
    }
}