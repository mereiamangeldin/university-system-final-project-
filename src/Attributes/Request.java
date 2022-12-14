package Attributes;

import java.util.*;

public class Request {
	private String requestDescription;
    private Date dateOfRequest;
    
    public Request(String requestDescription, Date dateOfRequest) {
		this.requestDescription = requestDescription;
		this.dateOfRequest = dateOfRequest;
	}
    
	public String getRequestDescription() {
		return requestDescription;
	}
	
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
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
		result = prime * result + ((requestDescription == null) ? 0 : requestDescription.hashCode());
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
		if (requestDescription == null) {
			if (other.requestDescription != null)
				return false;
		} else if (!requestDescription.equals(other.requestDescription))
			return false;
		return true;
	}

	public String toString() {
		return "Request [requestDescription=" + requestDescription + ", dateOfRequest=" + dateOfRequest + "]";
	}
}

