package Actors;

import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Interfaces.*;
/**TechSupportWorker handles technical requests from employees and students.*/

public class TechSupportWorker extends Employee implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Vector<Request> requests;
	
	public TechSupportWorker(User user) {
		super(user);
	}

	public TechSupportWorker(User user, String id, Date hireDate, double salary, String insuranceNumber) {
		super(user, id, hireDate, salary, insuranceNumber);
		this.requests = new Vector<Request>();
	}
	
	{
		Database.getUsers().add(this);
	}
	
	/**
	 * proccess of accepted requests
	 * */
	public void processRequests(int pos) {
		requests.remove(pos - 1);
	}

	// hashCode(), equals() and toString()
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(requests);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		TechSupportWorker other = (TechSupportWorker) obj;
		return Objects.equals(requests, other.requests);
	}
	
	public String toString() {
		return "TechSupportWorker [requests=" + requests +"]";
	}
	
	// getters and setters 
	public Vector<Request> getRequests() {
		return requests;
	}

	public void setRequests(Vector<Request> requests) {
		this.requests = requests;
	}
}