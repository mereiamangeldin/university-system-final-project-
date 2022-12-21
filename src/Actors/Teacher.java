package Actors;

import Interfaces.*;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Enums.*;

public class Teacher extends Employee implements CanViewMarks, Comparable<Teacher>, Serializable {
	private static final long serialVersionUID = 1L;
	private School school;
	private TeacherTypes type;
    
    public Teacher(User user) {
    	super(user);
    }

    public Teacher(User user, String id, Date hireDate, double salary, String insuranceNumber, School school, TeacherTypes type) {
    	super(user, id, hireDate, salary, insuranceNumber);
    	this.school = school;
      	this.type = type;
    }    
    
    {
		Database.getUsers().add(this);
    }
    
    public School getSchool() {
    	return school;
    }

    public void setSchool(School school) {
    	this.school = school;
    }

    public TeacherTypes getType() {
    	return type;
    }

    public void setType(TeacherTypes type) {
    	this.type = type;
    }
    
    public void viewCourses() {
    	System.out.println(Database.getCourses());
    }

    public void viewCourses(String id) {
    	for (Course c : Database.getCourses()) {
    		if(c.getId().equals(id)) System.out.println(c);
    	}
    }

    public void addCourse(Course course) {
    	if(!Database.getCourses().contains(course)) {
    		Database.getCourses().add(course);
        }
    }

    public void deleteCourse(Course course) {
    	if(Database.getCourses().contains(course)) {
    		Database.getCourses().remove(course);
    	}
    }
 
    public void putMark(Course course, Student s, int type, double point) {
    	Pair<Course, Teacher> p = new Pair<Course, Teacher>(course, this);
    	if(s.getTranscript().containsKey(p)) {
    		if(type == 1) {
    			s.getTranscript().get(p).setFirstAttestation(point);
    		} else if(type == 2) {
    			s.getTranscript().get(p).setSecondAttestation(point);
    		} else if(type == 3) {
    			s.getTranscript().get(p).setFinalScore(point);
    		}
    	}
    }

   public String viewMark(Course c) {
	   Pair<Course, Teacher> p = new Pair<Course, Teacher>(c, this);
	   for(Student s: Database.getStudents()) {
		    for(HashMap.Entry<Pair<Course, Teacher>, Mark> t : s.getTranscript().entrySet()) {
		    	if(t.getKey().equals(p)) {
		    		Database.getUserActions().add(new Action(this, new Date(), String.format("Teacher: %s viewed mark of course: %s", getFullName(), c.getName())));
		    		return s.getFullName() + ": " + t.getValue();
		    	}
		    }
	   }
	   return null;
	}

   public String toString() {
	   return this.getFullName() + " " + this.school.getName() + " " + this.type;
   }

   public int hashCode() {
	   final int prime = 31;
	   int result = super.hashCode();
	   result = prime * result + Objects.hash(school, type);
	   return result;
   }

   public boolean equals(Object obj) {
	   if (this == obj) return true;
	   if (!super.equals(obj)) return false;
	   if (getClass() != obj.getClass()) return false;
	   Teacher other = (Teacher) obj;
	   return Objects.equals(school, other.school) && type == other.type;
    } 
   
   public int compareTo(Teacher t) {
   		int resultByName = this.getName().compareTo(t.getName());
   		if(resultByName != 0) return resultByName;
   		int resultBySurname = this.getSurname().compareTo(t.getSurname());
   		return resultBySurname;   
   	}
}