package Actors;
import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Enums.RequestType;
import Interfaces.*;
import Decorators.*;

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
    	// Запрос в центр технической поддержки 
    	if(employee instanceof TechSupportWorker) { 
    		if(request.getDescription().length() > 20 && request.getTitle().equals(RequestType.EmployeeRequest)) {
    			TechSupportWorker t = (TechSupportWorker)employee;
    			t.getRequests().add(request);
    			Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s made request to Tech support worker %s", getFullName(), t.getFullName())));
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
    		Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s made request to manager %s", getFullName(), getFullName())));
    		return "Your request has been sended to manager";
    	}
    	return "";
    }

  	public void writeComment(String comment, News n) {
  		n.getComments().add(comment);
  	}
  	
  	// ПРОВЕРИТЬ
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
    	return "Employee [id=" + id + ", hireDate=" + hireDate + ", salary=" + salary + ", insuranceNumber="
    			+ insuranceNumber + ", email=" + email + "]";
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