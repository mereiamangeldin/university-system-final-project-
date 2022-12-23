package Decorators;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import Actors.*;
import Attributes.*;
import Enums.*;

public class TeacherDecorator extends UserDecorator implements Serializable {
	private static final long serialVersionUID = 5210711934689423250L;
	protected final Teacher decoratedTeacher;
	public TeacherDecorator(Teacher t) {
		super(t);
		this.decoratedTeacher = t;
	}
	 public School getSchool() {
	    	return decoratedTeacher.getSchool();
	    }

	    public void setSchool(School school) {
	    	decoratedTeacher.setSchool(school);
	    }
	    
	    public Teacher getDecoratedTeacher() {
	    	return decoratedTeacher;
	    }

	    public TeacherTypes getType() {
	    	return decoratedTeacher.getType();
	    }

	    public void setType(TeacherTypes type) {
	    	decoratedTeacher.setType(type);
	    }
	    
	    public Vector<Course> getCourses(){
	    	return decoratedTeacher.getCourses();
	    }

	    public void putMark(Course course, Student student, int type, double point) {
	    	decoratedTeacher.putMark(course, student, type, point);
	    }

	   public void viewMark(Course c) {
		   decoratedTeacher.viewMark(c);
	   }
	   
	    public String addFileToCouse(Course course, File file) {
	    	return decoratedTeacher.addFileToCouse(course, file);
	    }
	    
	    public String deleteFileFromCourse(Course course, File file){
	    	return decoratedTeacher.deleteFileFromCourse(course, file);
	    }

	   
	   public int compareTo(Teacher t) {
	   		return decoratedTeacher.compareTo(t);
	   	}
	   
	   public String getId() {
		   return decoratedTeacher.getId();
	   }
	   
	  	public void sendMessage(Message message, Employee employee) {
	  		decoratedTeacher.sendMessage(message, employee);
	  	}
	  	
	  	public void writeComment(String comment, News n) {
	  		decoratedTeacher.writeComment(comment, n);
	  	}
	  	
	    public String makeRequest(Request request, Employee employee) {
	    	return decoratedTeacher.makeRequest(request, employee);
	    }
	    
	  	public Vector<Student> viewStudentBy(int viewBy) {
	  		return decoratedTeacher.viewStudentBy(viewBy);
	  	}
	  	
	    public  LinkedHashMap <Employee, Message> getEmail() {
	    	return decoratedTeacher.getEmail();
	    }
}	