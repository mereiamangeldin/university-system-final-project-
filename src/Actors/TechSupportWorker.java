package Actors;

import java.util.*;
import Attributes.*;

public class TechSupportWorker extends Employee {

	private static final long serialVersionUID = 1L;
	private Vector<Request> requests;
	
	public TechSupportWorker() {
		super();
	}

	public TechSupportWorker(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
		this.requests = new Vector<Request>();
	}
	
	{
		Database.getTechSupportWorkers().add(this);
	}
	
	public void processRequests(int pos) {
		requests.remove(pos-1);
	}

	public Vector<Request> getRequests() {
		return requests;
	}

	public void setRequests(Vector<Request> requests) {
		this.requests = requests;
	}


	public String toString() {
		return "TechSupportWorker [requests=" + requests +"]";
	}

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
}