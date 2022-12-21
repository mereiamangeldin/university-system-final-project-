package Actors;

import Interfaces.*;
import javafx.util.Pair;

import java.util.*;
import Attributes.*;
import Enums.*;

public class Student extends User implements CanWriteComment, CanMakeRequest, CanViewTranscript, CanViewMarks, Comparable<Student> {
	private static final long serialVersionUID = 1L;
	private String id;
    private School school;
    private int yearOfStudy;
    private HashMap<Pair<Course, Teacher>, Mark> transcript;
    private boolean grant;
    private double scholarship;
    private ScienceDegree scienceDegree;
    private HashMap<Organization, Position> organizations;
    private boolean isBlocked;
    
    public Student() {
    	super();
    }

    public Student(String name, String surname, String password, Date dateOfBirth, String id, School school, int yearOfStudy, boolean grant,  double scholarship, ScienceDegree scienceDegree) {
    	super(name, surname, password, dateOfBirth);
    	this.id = id;
    	this.school = school;
    	this.yearOfStudy = yearOfStudy;
    	this.grant = grant;
    	this.scholarship = scholarship;
    	this.scienceDegree = scienceDegree;
    	this.transcript = new HashMap<Pair<Course, Teacher>, Mark>();  
    	this.organizations = new HashMap<Organization, Position>();
    	this.isBlocked = false;
    }
    
    {
		Database.getUsers().add(this);
    }
    
    public String getId() {
    	return id;
    }
  
    public void setId(String id) {
    	this.id = id;
    }

    public School getSchool() {
    	return school;
    }

    public void setSchool(School school) {
    	this.school = school;
    }

    public int getYearOfStudy() {
    	return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
    	this.yearOfStudy = yearOfStudy;
    }

    public Boolean getGrant() {
    	return grant;
    }

    public void setGrant(Boolean grant) {
    	this.grant = grant;
    }

    public double getScholarship() {
    	return scholarship;
    }

    public void setScholarship(double scholarship) {
    	this.scholarship = scholarship;
    }

    public ScienceDegree getScienceDegree() {
    	return scienceDegree;
    }

    public void setScienceDegree(ScienceDegree scienceDegree) {
    	this.scienceDegree = scienceDegree;
    }    

    public HashMap<Organization, Position> getOrganizations() {
    	return organizations;
    }

    public void setOrganizations(HashMap<Organization, Position> organizations) {
    	this.organizations = organizations;
    }

    public void setTranscript(HashMap<Pair<Course, Teacher>, Mark> transcript) {
    	this.transcript = transcript;
    }
    
    public Vector<Teacher> getTeachers(){
    	Vector<Teacher> teachers = new Vector<Teacher>();
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> x : transcript.entrySet()) {
    		teachers.add(x.getKey().getValue());
    	}
    	return teachers;
    }
    
    public boolean getIsBlocked() {
    	return isBlocked;
    }
    
    public void setIsBlocked(boolean isBlocked) {
    	this.isBlocked = isBlocked;
    }
    
    public void viewCourses(){
    	Database.getUserActions().add(String.format("User: %s has viewed courses", super.getUsername()));
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : transcript.entrySet()) {
    		System.out.println(marks.getKey().getKey());
    	}
    }

    public void registerForCourse(Course course, Teacher t, Manager m){
    	this.makeRequest(new Request(this.getId(), RequestType.CourseRegistration, course.getId() + " " + t.getId()), m);
    }

    public void rateTeacher(Teacher teacher, double mark){
    	if(Questionnaire.getRating().containsKey(teacher)) {
    		Questionnaire.getRating().get(teacher).add(mark);
    	} else {
    		Questionnaire.getRating().put(teacher, new Vector<Double>());
    		Questionnaire.getRating().get(teacher).add(mark);
    	}
    }

    public HashMap<Pair<Course, Teacher>, Mark> getTranscript() {
    	return transcript;
    }  
    
    public void makeBookOrder(Librarian librarian, Order order){
    	String answer = librarian.orderBook(order);
        if(answer.equals("Accepted")) {
			Database.getUserActions().add(String.format("User: %s made order for book: %s", super.getUsername(), order.getBook().getName()));
        	System.out.println("The order has been done succesfully! "
        			+ "You can take the order in 5 minutes. Please don't forget to return the book until the deadline!");
        } else if(answer.equals("Books over")) {
        	System.out.println("Unfortunately, all the copies of the book you ordering have been taken.");
        } else if(answer.equals("Not accepted")) {
        	System.out.println("Unfortunately, we don't have this book in our library.");
        }
    }
    
    public String makeRequest(Request request, Employee employee) {
    	if(employee instanceof TechSupportWorker) {
        if(request.getDescription().length() > 20 && request.getTitle().equals(RequestType.EmployeeRequest)) {
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
		Database.getUserActions().add(String.format("User: %s writed comment to news %s", super.getUsername(), n.getTitle()));
    	n.getComments().add(comment);
    }

    public void viewTranscript() {
		Database.getUserActions().add(String.format("User: %s has viewed transcript", super.getUsername()));
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : transcript.entrySet()) {
    		System.out.println(marks.getKey().getKey().getName() + ": " + marks.getValue().getTotal());
        }
    }
    
    public String toString() {
        return "Student [id=" + id + ", school=" + school + ", yearOfStudy=" + yearOfStudy + ", transcript="
            + transcript + ", grant=" + grant + ", scholarship=" + scholarship + ", scienceDegree=" + scienceDegree
                + ", organizations=" + organizations + "]";
    }

    public int hashCode() {
    	final int prime = 31;
        int result = super.hashCode();
        result = prime * result
            + Objects.hash(grant, id, organizations, scholarship, school, scienceDegree, transcript, yearOfStudy);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return Objects.equals(grant, other.grant) && Objects.equals(id, other.id)
            && Objects.equals(organizations, other.organizations)
            && Double.doubleToLongBits(scholarship) == Double.doubleToLongBits(other.scholarship)
            && Objects.equals(school, other.school) && scienceDegree == other.scienceDegree
            && Objects.equals(transcript, other.transcript) && yearOfStudy == other.yearOfStudy;
      }

    public void viewMark(Course c) {
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> t : transcript.entrySet()) {
    		if(t.getKey().getKey().equals(c)) {
    			Database.getUserActions().add(String.format("User: %s viewed mark of course: %s", getFullName(), c.getName()));
    			System.out.println(c.getName() + ": " + t.getValue());
    			break;
    		}
        }
    }
    
    public int compareTo(Student s) {
    	int resultByName = this.getName().compareTo(s.getName());
    	if(resultByName != 0) return resultByName;
    	int resultBySurname = this.getSurname().compareTo(s.getSurname());
    	return resultBySurname;
    }     
}