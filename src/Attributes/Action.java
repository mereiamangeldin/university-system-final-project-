package Attributes;

import Actors.*;
import java.util.*;

public class Action {
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
	
}