package Actors;

import Interfaces.*;
import javafx.util.Pair;

import java.util.*;
import Attributes.*;
import Enums.*;

public class Teacher extends Employee implements CanViewMarks, Comparable<Teacher> {
	private static final long serialVersionUID = 1L;
	private School school;
	private TeacherTypes type;
    
    public Teacher() {
    	super();
    }

    public Teacher(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber, School school, TeacherTypes type) {
    	super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
    	this.school = school;
      	this.type = type;
    }    
    
    {
    	Database.getTeachers().add(this);
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
 
    public void putMark(Course course, String studentId, int type, double point) {
    	Pair<Course, Teacher> p = new Pair<Course, Teacher>(course, this);
    	for(Student s: Database.getStudents()) {
    		if(s.getTranscript().containsKey(p) && s.getId().equals(studentId)) {
    			if(type == 1) {
    				s.getTranscript().get(p).setFirstAttestation(point);
    			}
	            else {
	            	s.getTranscript().get(p).setSecondAttestation(point);
	            }
    			break;
    		}
    	}
    }

   public void viewMark(Course c) {
	   Pair<Course, Teacher> p = new Pair<Course, Teacher>(c, this);
	   for(Student s: Database.getStudents()) {
		   if(s.getTranscript().containsKey(p)) {
			   s.viewMark(c);
		   }
	   }
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