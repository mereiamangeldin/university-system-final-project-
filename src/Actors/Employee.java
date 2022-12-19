package Actors;

import java.util.*;
import Attributes.*;
import Enums.requestType;
import Interfaces.*;

public abstract class Employee extends User implements CanMakeRequest, CanWriteComment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private Date hireDate;
    private double salary;
    private String insuranceNumber;
    private LinkedHashMap <Employee, Message> email;
    
    public Employee() {}
    
    public Employee(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
        super(name, surname, password, dateOfBirth);
        this.id = id;
        this.hireDate = hireDate;
        this.salary = salary;
        this.insuranceNumber = insuranceNumber;
        this.email = new LinkedHashMap<Employee, Message>();
    }
    
    public String getId() {
    	return id;
    }

    public void setId(String id) {
    	this.id = id;
    }

    public Date getHireDate() {
    	return hireDate;
    }

    public void setHireDate(Date hireDate) {
    	this.hireDate = hireDate;
    }

    public double getSalary() {
    	return salary;
    }

    public void setSalary(double salary) {
    	this.salary = salary;
    }

    public String getInsuranceNumber() {
    	return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
    	this.insuranceNumber = insuranceNumber;
    }

    public  LinkedHashMap <Employee, Message> getEmail() {
    	return email;
    }

    public void setEmail(LinkedHashMap <Employee, Message> email) {
    	this.email = email;
    }
  
    public String toString() {
    	return "Employee [id=" + id + ", hireDate=" + hireDate + ", salary=" + salary + ", insuranceNumber="
    			+ insuranceNumber + ", email=" + email + "]";
    }
  
    public String viewStudent() {
    	for (Student d : Database.getStudents()) {
    		return d.toString();
    	}
    	return "\n";
    }

  	public void sendMessage(Message message, Employee employee) {
  		employee.getEmail().put(this, message);
  	}

    public String makeRequest(Request request, Employee employee) {
    	if(employee instanceof TechSupportWorker) {
        if(request.getDescription().length() > 20 && request.getTitle().equals(requestType.employeeRequest)) {
        	TechSupportWorker t = (TechSupportWorker)employee;
        	t.getRequests().add(request);
    		Database.getUserActions().add(String.format("User: %s made request to Tech support worker", super.getUsername()));
        	return "Your request has been sended to Tech Support worker";
        }else {
        	return "Your request is rejected: description size is less than 20 and sended by employee";	
        }
      }
      else if(employee instanceof Manager) {
		Database.getUserActions().add(String.format("User: %s made request to manager", super.getUsername()));
    	return "Your request has been sended to manager";
      }
    return "Request can be sended only to manager or tech support worker";
   }

  	public void writeComment(String comment, News n) {
  		n.getComments().add(comment);
  	}

  	public void viewStudentBy(int viewBy) {
  		if(viewBy == 1){
  			Collections.sort(Database.getStudents());
  			System.out.println(Database.getStudents());
  		}
  		
  		if(viewBy == 2) {
  			Collections.sort(Database.getStudents(), new NameComparator());
  			System.out.println(Database.getStudents());
  		}
  	}

  	public int hashCode() {
  		return Objects.hash(email, hireDate, id, insuranceNumber, salary);
  	}

  	public boolean equals(Object obj) {
  		if (this == obj) return true;
  		if (obj == null) return false;
  		if (getClass() != obj.getClass()) return false;
  		Employee other = (Employee) obj;
  		return Objects.equals(email, other.email) && Objects.equals(hireDate, other.hireDate)
  				&& Objects.equals(id, other.id) && Objects.equals(insuranceNumber, other.insuranceNumber)
  				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
  	}   
}