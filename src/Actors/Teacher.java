package Actors;

import Interfaces.*;
import java.util.*;
import Attributes.*;
import Enums.*;

public class Teacher extends Employee implements CanViewMarks, Comparable<Teacher> {
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
    	for(Student s: Database.getStudents()) {
    		if(s.getTranscript().containsKey(course) && s.getId().equals(studentId)) {
    			if(type == 1) {
    				s.getTranscript().get(course).setFirstAttestation(point);
    			}
	            else {
	            	s.getTranscript().get(course).setSecondAttestation(point);
	            }
    			break;
    		}
    	}
    }

   public void viewMark(Course c) {
	   for(Student s: Database.getStudents()) {
		   if(s.getTranscript().containsKey(c)) {
			   for(HashMap.Entry<Course, Mark> t: s.getTranscript().entrySet()) {
				   if(t.getKey().getTeachers().contains(this)) {
					   s.viewMark(c);
				   }
			   }
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
	   return this.getId().compareTo(t.getId());
   }
}