package Decorators;
import Actors.*;
import Attributes.*;
import Enums.*;

public class TeacherDecorator extends UserDecorator {
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

	    public TeacherTypes getType() {
	    	return decoratedTeacher.getType();
	    }

	    public void setType(TeacherTypes type) {
	    	decoratedTeacher.setType(type);
	    }
	    
	    public void viewCourses() {
	    	decoratedTeacher.viewCourses();
	    }

	    public void viewCourses(String id) {
	    	decoratedTeacher.viewCourses(id);
	    }

	    public void addCourse(Course course) {
	    	decoratedTeacher.addCourse(course);
	    }

	    public void deleteCourse(Course course) {
	    	decoratedTeacher.deleteCourse(course);
	    }
	 
	    public void putMark(Course course, Student student, int type, double point) {
	    	decoratedTeacher.putMark(course, student, type, point);
	    }

	   public void viewMark(Course c) {
		   decoratedTeacher.viewMark(c);
	   }

	   
	   public int compareTo(Teacher t) {
	   		return decoratedTeacher.compareTo(t);
	   	}
}	