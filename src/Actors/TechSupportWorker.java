package Actors;

import java.util.*;
import Attributes.*;

public class TechSupportWorker extends Employee {
	private HashMap<Request, Boolean> requests;
	private HashMap<Request, Boolean> statusRequest;
	
	public TechSupportWorker() {
		super();
	}
	public String medinaAndAisultan() {
		return "the Best";
	}

	public TechSupportWorker(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
		this.requests = new HashMap<Request, Boolean>();
		this.statusRequest = new HashMap<Request, Boolean>();
	}
	
	{
		Database.getTechSupportWorkers().add(this);
	}
  
	public String viewStudent() {
		return null;
	}
  
	public boolean requestAccepted(Request request) {
		for(HashMap.Entry<Request, Boolean> r : statusRequest.entrySet()) {
			if(r.getKey().equals(request)) {
				Random rd = new Random();
				r.setValue(rd.nextBoolean());
				return r.getValue();
			}
		}
		return false;
	}

	public HashMap<Request, Boolean> getRequests() {
		return requests;
	}

	public void setRequests(HashMap<Request, Boolean> requests) {
		this.requests = requests;
	}

	public HashMap<Request, Boolean> getStatusRequest() {
		return statusRequest;
	}

	public void setStatusRequest(HashMap<Request, Boolean> statusRequest) {
		this.statusRequest = statusRequest;
	}

	public String toString() {
		return "TechSupportWorker [requests=" + requests + ", statusRequest=" + statusRequest + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(requests, statusRequest);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		TechSupportWorker other = (TechSupportWorker) obj;
		return Objects.equals(requests, other.requests) && Objects.equals(statusRequest, other.statusRequest);
	}
}