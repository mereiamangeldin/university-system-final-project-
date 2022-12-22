package Actors;
import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Enums.RequestType;
import Interfaces.*;
import Decorators.*;

/**All employees can view students, can send messages within each other, also they can make request to Managers or TechSupportWorkers.*/

public abstract class Employee extends UserDecorator implements CanMakeRequest, CanWriteComment, Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
    private Date hireDate;
    private double salary;
    private String insuranceNumber;
    private LinkedHashMap <Employee, Message> email;
    
    public Employee(User user) {super(user);}
    
    public Employee(User user, String id, Date hireDate, double salary, String insuranceNumber) {
        super(user);
        this.id = id;
        this.hireDate = hireDate;
        this.salary = salary;
        this.insuranceNumber = insuranceNumber;
        this.email = new LinkedHashMap<Employee, Message>();
    }
    
    /**
     * returns all the students of university*/
    public Vector<Student> viewStudent() {
    	return Database.getStudents();
    }
    
    /**
     * to send message to another employee*/
  	public void sendMessage(Message message, Employee employee) {
  		employee.getEmail().put(this, message);
  	}
  	
  	/**
  	 * to make request to Manager or TechSupportWorker
  	 * */
    public String makeRequest(Request request, Employee employee) {
    	// Запрос в центр технической поддержки 
    	if(employee instanceof TechSupportWorker) { 
    		if(request.getDescription().length() > 10 && request.getTitle().equals(RequestType.SimpleRequest)) {
    			TechSupportWorker t = (TechSupportWorker)employee;
    			t.getRequests().add(request);
    			Database.getUserActions().add(new Action(this, new Date(), String.format("Employee: %s made request to Tech support worker %s", getFullName(), t.getFullName())));
    			return "Your request has been sended to tech support worker";
    		} 
    		else {
        		return "Your request is rejected: description size is less than 20 and sended by employee";	
        	}
    	}
    	// Запрос менеджеру 
    	else if(employee instanceof Manager) {
    		Manager m = (Manager)employee;
    		m.getRequests().add(request);
    		Database.getUserActions().add(new Action(this, new Date(), String.format("Employee: %s made request to manager %s", getFullName(), getFullName())));
    		return "Your request has been sended to manager";
    	}
    	return "";
    }
    
    /**
     * to write a comment under the news
     * */
  	public void writeComment(String comment, News n) {
  		n.getComments().add(comment);
  	}
  	
  	/**
  	 * returns all student in requested order alphabetically or by gpa
  	 * */
  	public Vector<Student> viewStudentBy(int viewBy) {
  		Vector<Student> v = Database.getStudents();
  		if(viewBy == 1){ // Alphabetically
  			Collections.sort(v);
  		}
  		if(viewBy == 2) { // By GPA 
  			Collections.sort(v, new GpaComparator());
  		}
  		return v;
  	}

  	// hashCode(), equals() and toString()
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
  	
    public String toString() {
    	String answer = String.format("Employee: %s, ID: %s, username: %s", this.getFullName(), this.getId(), this.getUsername());
    	return answer;
    }
  	
  	// getters and setters
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
}