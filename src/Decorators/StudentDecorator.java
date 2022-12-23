package Decorators;
import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Enums.*;
import javafx.util.Pair;
import Actors.*;

public abstract class StudentDecorator extends UserDecorator implements Serializable {
	private static final long serialVersionUID = -3759375878641148730L;
	protected final Student decoratedStudent;
	public StudentDecorator(Student s) {
		super(s);
		this.decoratedStudent = s;
	}
	public String getId() {
    	return decoratedStudent.getId();
    }
	
	public Student getDecoratedStudent() {
		return decoratedStudent;
	}
  
    public void setId(String id) {
    	decoratedStudent.setId(id);
    }

    public School getSchool() {
    	return decoratedStudent.getSchool();
    }

    public void setSchool(School school) {
    	decoratedStudent.setSchool(school);
    }

    public int getYearOfStudy() {
    	return decoratedStudent.getYearOfStudy();
    }

    public void setYearOfStudy(int yearOfStudy) {
    	decoratedStudent.setYearOfStudy(yearOfStudy);
    }

    public Boolean getGrant() {
    	return decoratedStudent.getGrant();
    }

    public void setGrant(Boolean grant) {
    	decoratedStudent.setGrant(grant);
    }

    public double getScholarship() {
    	return decoratedStudent.getScholarship();
    }

    public void setScholarship(double scholarship) {
    	decoratedStudent.setScholarship(scholarship);
    }

    public ScienceDegree getScienceDegree() {
    	return decoratedStudent.getScienceDegree();
    }

    public void setScienceDegree(ScienceDegree scienceDegree) {
    	decoratedStudent.setScienceDegree(scienceDegree);
    }    

    public HashMap<Organization, Position> getOrganizations() {
    	return decoratedStudent.getOrganizations();
    }

    public void setOrganizations(HashMap<Organization, Position> organizations) {
    	decoratedStudent.setOrganizations(organizations);
    }

    public void setTranscript(HashMap<Pair<Course, Teacher>, Mark> transcript) {
    	decoratedStudent.setTranscript(transcript);
    }
    
    public Vector<Teacher> getTeachers(){
    	return decoratedStudent.getTeachers();
    }


    public void registerForCourse(Course course, Teacher t, Manager m){
    	decoratedStudent.registerForCourse(course, t, m);
    }

    public HashMap<Pair<Course, Teacher>, Mark> getTranscript() {
    	return decoratedStudent.getTranscript();
    }  
    
    public String makeBookOrder(Librarian librarian, Order order) throws Exception{
    	return decoratedStudent.makeBookOrder(librarian, order);
    }
    
    public String makeRequest(Request request, Employee employee) {
    	return decoratedStudent.makeRequest(request, employee);
   }

    public void writeComment(String comment, News n) {
    	decoratedStudent.writeComment(comment, n);
    }

    public void viewTranscript() {
    	decoratedStudent.viewTranscript();
    }
    public void viewMark(Course c) {
    	decoratedStudent.viewMark(c);
    }
    
    public int compareTo(Student s) {
    	return decoratedStudent.compareTo(s);
    } 
    public double getGpa() {
    	return decoratedStudent.getGpa();
    }
    public boolean getIsBlocked() {
    	return decoratedStudent.getIsBlocked();
    }
    public void setIsBlocked(boolean isBlocked) {
    	decoratedStudent.setIsBlocked(isBlocked);
    }
    
    public Vector<Course> getCourses(){
    	return decoratedStudent.getCourses();
    }
    
   public void rateTeacher(Teacher teacher, double mark){
	   decoratedStudent.rateTeacher(teacher, mark);
   }

}
