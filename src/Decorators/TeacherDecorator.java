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
	    
<<<<<<< HEAD
	    public void viewCourses() {
	    	decoratedTeacher.viewCourses();
	    }

	 
=======
>>>>>>> b7178203b650ab3f3307e9a8447e3bf6e5784264
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