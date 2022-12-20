package Attributes;

import java.util.*;
import Enums.*;

public class Request {
	private String userID;
	private RequestType title;
	private String description;
    private Date dateOfRequest;
    
    public Request(String userID, RequestType title, String requestDescription) {
    	this.userID = userID;
    	this.title = title;
		this.description = requestDescription;
		this.dateOfRequest = new Date();
	}
    
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateOfRequest() {
		return dateOfRequest;
	}
	
	public void setDateOfRequest(Date dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfRequest == null) ? 0 : dateOfRequest.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Request other = (Request) obj;
		if (dateOfRequest == null) {
			if (other.dateOfRequest != null)
				return false;
		} else if (!dateOfRequest.equals(other.dateOfRequest))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	public String toString() {
		return "Request [Description=" + description + ", dateOfRequest=" + dateOfRequest + "]";
	}

	public RequestType getTitle() {
		return title;
	}

	public void setTitle(RequestType title) {
		this.title = title;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}