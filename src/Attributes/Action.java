package Attributes;
import java.io.Serializable;
import java.util.*;
import Interfaces.*;

public class Action implements Serializable {
	private User user;
	private Date date;
	private String description;
	
	public Action(User user, Date date, String description) {
		this.user = user;
		this.date = date;
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int hashCode() {
		return Objects.hash(date, description, user);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Action other = (Action) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(user, other.user);
	}

	public String toString() {
    	String answer = String.format("Action of user: %s, date: %s, description: %s", this.getUser().getFullName(), this.getDate(), this.getDescription());
    	return answer;
	}
}