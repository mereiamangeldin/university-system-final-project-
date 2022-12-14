package Actors;

import java.util.*;
import Attributes.*;
import Interfaces.*;

public class Parent extends User implements CanViewTranscript, CanViewMarks {
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
    }

    public void viewMark(Course c) {
    	child.viewMark(c);
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