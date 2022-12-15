package Actors;

import Interfaces.*;
import javafx.util.*;
import java.util.*;
import Attributes.*;
import Enums.*;

public class Student extends User implements CanWriteComment, CanViewTranscript, CanViewMarks, Comparable<Student> {
    private String id;
    private School school;
    private int yearOfStudy;
    private HashMap<Course, Mark> transcript;
    private boolean grant;
    private double scholarship;
    private ScienceDegree scienceDegree;
    private HashMap<Organization, Position> organizations;
    
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
    	this.transcript = new HashMap<Course, Mark>();  
    	this.organizations = new HashMap<Organization, Position>();
    }
    
    {
    	Database.getStudents().add(this);
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

    public void setTranscript(HashMap<Course, Mark> transcript) {
    	this.transcript = transcript;
    }

    public void viewCourses(){
    	Database.getUserActions().add(String.format("User: %s has viewed courses", super.getUsername()));
    	for(HashMap.Entry<Course, Mark> marks : transcript.entrySet()) {
    		System.out.println(marks.getKey());
      }
    }

    public void registerForCourse(Course course, Teacher t){
    	int amountOfCredits = 0;
    	for(HashMap.Entry<Course, Mark> marks : transcript.entrySet()) {
    		if(marks.getValue().getFinalScore() == 0 && marks.getValue().getLetterGrade() != 'F') {
    			amountOfCredits += marks.getKey().getNumberOfCredits();
    		}
    	}
    	if(amountOfCredits+course.getNumberOfCredits() > 30) {
    		System.out.println("Impossible, number of credits greater than 30");
    	}
    	else {
    		if(course.getPrerequisite() != null) {
    			for(HashMap.Entry<Course, Mark> marks : transcript.entrySet()) {
    				if(marks.getKey().equals(course.getPrerequisite())) {
    					if(marks.getValue().getLetterGrade() != 'F' && marks.getValue().getLetterGrade() != 'N') {
    						Database.getUserActions().add(String.format("User: %s has registered for course: %s", super.getUsername(), course.getName()));
    						transcript.put(course, new Mark());
    						break;
    					}
    				}
    			}
    		}
        else {
        	Database.getUserActions().add(String.format("User: %s has registered for course: %s", super.getUsername(), course.getName()));
        	transcript.put(course, new Mark());
        }
      }
    }

    public void rateTeachers(Teacher teacher, Questionnaire q, Double mark){
    	q.getRating().add(new Pair<Teacher, Double>(teacher, mark));
    }

    public HashMap<Course, Mark> getTranscript() {
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
    
    public void makeRequest(Request request, Employee employee) {
    	if(employee instanceof TechSupportWorker) {
    		TechSupportWorker t = (TechSupportWorker)employee;
        if(request.getRequestDescription().length() > 20) {
			Database.getUserActions().add(String.format("User: %s made request to tech support worker", super.getUsername()));
        	t.getRequests().put(request, true);
        	System.out.println("Request has been accepted!");
        	t.getStatusRequest().put(request, false);
//          try {
//            Thread.sleep(5000);
//          }catch(InterruptedException ie) {
//            
//          }
          if(t.requestAccepted(request)) {
        	  System.out.println("Your request has been successfully completed!");
          } else {
        	  System.out.println("Your request has not been completed!");
          }
        } else {
        	t.getRequests().put(request, false);
        	System.out.println("Request has been rejected!");
        }
      }
      else if(employee instanceof Manager) {
		Database.getUserActions().add(String.format("User: %s made request to manager", super.getUsername()));
    	  Manager m = (Manager)employee;
    	  m.getRequests().put(request, false);
    	  for(School s : Database.getSchools()) {
    		  if(s.getManagers().contains(m)) {
    			  if(m.sendRequestToDean(s.getDean(), request)) {
    				  for(HashMap.Entry<Request, Boolean> r : m.getRequests().entrySet()) {
    					  if(r.getKey().equals(request)) {
    						  r.setValue(true);
    					  }
    				  }
    			  }
    		  }
    	  }
      	}
    }

    public void writeComment(String comment, News n) {
		Database.getUserActions().add(String.format("User: %s writed comment to news %s", super.getUsername(), n.getTitle()));
    	n.getComments().add(comment);
    }

    public void viewTranscript() {
		Database.getUserActions().add(String.format("User: %s has viewed transcript", super.getUsername()));
    	for(HashMap.Entry<Course, Mark> marks : transcript.entrySet()) {
    		System.out.println(marks.getKey().getName() + ": " + marks.getValue().getTotal());
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
    	for(HashMap.Entry<Course, Mark> t : transcript.entrySet()) {
    		if(t.getKey().equals(c)) {
    			Database.getUserActions().add(String.format("User: %s viewed mark of course: %s", super.getUsername(), c.getName()));
    			System.out.println(c.getName() + ": " + t.getValue());
    			break;
    		}
        }
    }
    
    public int compareTo(Student s) {
    	return this.getId().compareTo(s.getId());
    }     
}