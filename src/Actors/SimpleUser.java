package Actors;
import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Interfaces.*;

/**
 * The main class of our system, other actors of university system extends this class.
 * */
public class SimpleUser implements Serializable, User {
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private boolean logged;
    
    public SimpleUser(){}
    
    public SimpleUser(String name, String surname, String password, Date dateOfBirth) {
    	super();
		this.name = name;
		this.surname = surname;
		this.username = Character.toLowerCase(this.name.charAt(0)) + "_" + this.surname.toLowerCase();
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
    /**
     * to authenticate a user with a specified login account
     * */
    public boolean login(String password) {
    	if(this.password.equals(password)) {
    		Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s has logged", username)));
    		logged = true;
    	}
    	return logged;
    }
    /**
     *  to explicitly terminate the user's session
     *  */
    public boolean logout() {
    	Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s has logged out", username)));
    	logged = false;
    	return logged;
    }
    /**
     * allows to see all the news
     * */
    public Vector<News> viewNews() {
    	return Database.getNews();
    }
    
    // hashCode(), equals(), toString()
    public String toString() {
    	return "User username = " + username + ", password=" + password + ", name=" + name + ", surname=" + surname
    			+ ", dateOfBirth=" + dateOfBirth;
    }
    
    public int hashCode() {
    	return Objects.hash(dateOfBirth, name, password, surname, username);
    }
    
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (getClass() != obj.getClass()) return false;
    	SimpleUser other = (SimpleUser) obj;
    	return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(name, other.name)
        && Objects.equals(password, other.password) && Objects.equals(surname, other.surname)
        && Objects.equals(username, other.username);
    }
    
    // getters and setters 
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getSurname() {
    	return surname;
    }
    
    public void setSurname(String surname) {
    	this.surname = surname;
    }
    
    public Date getDateOfBirth() {
    	return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
    	this.dateOfBirth = dateOfBirth;
    }
    
    public boolean getLogged() {
    	return this.logged;
    }
    
    public String getFullName() {
    	return name + " " + surname;
    }
}