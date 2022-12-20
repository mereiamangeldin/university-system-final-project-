package Actors;

import java.util.*;
import Attributes.*;
import Interfaces.*;

public class Parent extends User implements CanViewTranscript, CanViewMarks {
	private static final long serialVersionUID = 1L;
	private Student child;

	public Parent() {
		super();
	}
  
	public Parent(String name, String surname, String password, Date dateOfBirth, Student child) {
		super(name, surname, password, dateOfBirth);
		this.child = child;
	}    
  
    public void viewTranscript() {
    	child.viewTranscript();
    	Database.getUserActions().add(String.format("Parent %s viewed the transcript of the child", getFullName()));
    }

    public void viewMark(Course c) {
    	child.viewMark(c);
    	Database.getUserActions().add(String.format("Parent %s viewed the mark of the child %s for the course %s", getFullName(), child.getFullName(), c.getName()));
    }
    
    public Student getChild() {
    	return child;
    }
    
    public void setChild(Student child) {
    	this.child = child;
    }
    
    public String toString() {
    	return "Parent [child=" + child + "]";
    }
    
    public int hashCode() {
    	final int prime = 31;
    	int result = super.hashCode();
    	result = prime * result + Objects.hash(child);
    	return result;
    }
    
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (!super.equals(obj)) return false;
    	if (getClass() != obj.getClass()) return false;
    	Parent other = (Parent) obj;
    	return Objects.equals(child, other.child);
    } 
}