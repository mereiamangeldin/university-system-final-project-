package Actors;

import Interfaces.*;
import javafx.util.Pair;
import Decorators.*;
import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Enums.*;

/**Student one of the main roles, can do all the staff with his courses, view news, rate teachers and etc.*/

public class Student extends UserDecorator implements CanWriteComment, CanMakeRequest, CanViewTranscript, CanViewMarks, Comparable<Student>, Serializable {
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
    
    public Student(User user) {
    	super(user);
    }

    public Student(User user, String id, School school, int yearOfStudy, boolean grant,  double scholarship, ScienceDegree scienceDegree) {
    	super(user);
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
    
//	{
//		Database.getUsers().add(this);
//	}
    
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

    public boolean getGrant() {
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
    
    /**
     * returns only those courses he have studied or studying in that moment
     * */
    public Vector<Course> getCourses(){
    	Vector<Course> v = new Vector<Course>();
	    for(HashMap.Entry<Pair<Course, Teacher>, Mark> t : this.transcript.entrySet()) {
	    	v.add(t.getKey().getKey());
	    }
    	return v;
    }
    
    public void setIsBlocked(boolean isBlocked) {
    	this.isBlocked = isBlocked;
    }

    /**
     * allows to register for specific course
     * */
    public void registerForCourse(Course course, Teacher t, Manager m){
    	this.makeRequest(new Request(this.getId(), RequestType.CourseRegistration, course.getId() + " " + t.getId()), m);
    	Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s sended request for registration to the course %s:", getFullName(), course.getName())));
    }
    
    /**
     * allows to rate specific teacher
     * */
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
    /**
     * makes book order to Librarian
     * @throws Exception 
     * */
    public String makeBookOrder(Librarian librarian, Order order) throws Exception{
    	String answer = librarian.orderBook(order);
        if(answer.equals("Accepted")) {
			Database.getUserActions().add(new Action(this, new Date(), String.format("User: %s made order for book: %s", getUsername(), order.getBook().getName())));
        	return "The order has been done succesfully! "
        			+ "You can take the order in 5 minutes. Please don't forget to return the book until the deadline!";
        } else if(answer.equals("Books over")) {
        	return "Unfortunately, all the copies of the book you ordering have been taken.";
        }
        	return "Unfortunately, we don't have this book in our library.";

    }
    /**
     * makes request to TechSupport worker for technical issues or Manager for academic issues*/ 
    public String makeRequest(Request request, Employee employee) {
    	if(employee instanceof TechSupportWorker) {
    		if(request.getDescription().length() > 10 && request.getTitle().equals(RequestType.SimpleRequest)) {
    			TechSupportWorker t = (TechSupportWorker)employee;
    			t.getRequests().add(request);
    			Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s made request to Tech support worker", getUsername())));
    			return "Your request has been sended to Tech Support worker";
        } else {
        	return "Your request is rejected: description size is less than 20 and sended by employee";	
        }
      }
      else if(employee instanceof Manager) {
    	  Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s made request to manager", getUsername())));
    	  ((Manager) employee).getRequests().add(request);
    	  return "Your request has been sended to manager";
      }
    		return "Request can be sended only to manager or tech support worker";
   }
    /**
     * to write comment under the news
     * */
    public void writeComment(String comment, News n) {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s writed comment to news %s", getUsername(), n.getTitle())));
    	n.getComments().add(comment);
    }
    /**
     * to view his own transcript
     * */
    public void viewTranscript() {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s has viewed transcript", getUsername())));
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : transcript.entrySet()) {
    		System.out.println("Student: " + marks.getKey().getKey().getName() + ", mark: " + marks.getValue().getTotal() + " points, " + "letter: " + marks.getValue().getLetterGrade() + ", teacher: " + marks.getKey().getValue());
        }
    }
    
    public String toString() {
    	String answer = String.format("Student: %s, id: %s, username: %s, school: %s, year of study: %s, science degree: %S", this.getFullName(), this.getId(), this.getUsername(), school.getShortName(), this.getYearOfStudy(), this.getScienceDegree().name());
    	return answer;
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
    /**
     * to view mark of specified course
     * */
    public String viewMark(Course c) {
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> t : transcript.entrySet()) {
    		if(t.getKey().getKey().equals(c)) {
    			Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s viewed mark of course: %s", getFullName(), c.getName())));
    			return c.getName() + ": " + t.getValue();
    		}
        }
    	return null;
    }
    
    public int compareTo(Student s) {
    	int resultByName = this.getName().compareTo(s.getName());
    	if(resultByName != 0) return resultByName;
    	int resultBySurname = this.getSurname().compareTo(s.getSurname());
    	return resultBySurname;
    } 
    public double getGpa() {
    	double total = 0;
    	int cnt = 0;
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> x : transcript.entrySet()) {
    		if(!x.getValue().getLetterGrade().equals("N")) {
    			total += x.getValue().getGpa();
    			cnt++;
    		}
    	}
    	return total/cnt;
    }
}