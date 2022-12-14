package Actors;
import java.util.Date;
import Attributes.*;

public class Admin extends Employee {
	
	public Admin() {};
	
	public Admin(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
	}
	
	{
		Database.getAdmins().add(this);
	}
	
    public void addUser(User u) {
    	if(u instanceof Teacher) {
    		Database.getTeachers().add((Teacher)u);
    	}
    	
    	if(u instanceof Manager) {
    		Database.getManagers().add((Manager)u);
    	}
    	
    	if(u instanceof Student) {
    		Database.getStudents().add((Student)u);
    	}
    	
    	if(u instanceof Dean) {
    		Database.getDeans().add((Dean)u);
    	}
    	
    	if(u instanceof Librarian) {
    		Database.getLibrarians().add((Librarian)u);
    	}
    	
    	if(u instanceof Admin) {
    		Database.getAdmins().add((Admin)u);
    	}
    	
    	if(u instanceof TechSupportWorker) {
    		Database.getTechSupportWorkers().add((TechSupportWorker)u);
    	}
    }
    
    public void removeUser(User u) {
    	if(u instanceof Teacher) {
    		Database.getTeachers().remove((Teacher)u);
	  	}
    	
	  	if(u instanceof Manager) {
	  		Database.getManagers().remove((Manager)u);
	  	}
	  	
	  	if(u instanceof Student) {
	  		Database.getStudents().remove((Student)u);
	  	}
	  	
	  	if(u instanceof Dean) {
	  		Database.getDeans().remove((Dean)u);
	  	}
	  	
	  	if(u instanceof Librarian) {
	  		Database.getLibrarians().remove((Librarian)u);
	  	}
	  	
	  	if(u instanceof Admin) {
	  		Database.getAdmins().remove((Admin)u);
	  	}
	  	
	  	if(u instanceof TechSupportWorker) {
	  		Database.getTechSupportWorkers().remove((TechSupportWorker)u);
	  	}
    }

    public void updateUser() {
    	
    }

    public void seeUserActions(User u) {
    	
    }

}