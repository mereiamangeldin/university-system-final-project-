package Actors;

import java.io.Serializable;
import java.util.*;
import Attributes.*;
import Interfaces.*;
import Decorators.*;
import javafx.util.Pair;

/**
 *  Parent can view his/her child marks, transcript.*/
public class Parent extends UserDecorator implements CanViewTranscript, CanViewMarks, Serializable {
	private static final long serialVersionUID = 1L;
	private Student child;

	public Parent(User user) {
		super(user);
	}
	
	{
		Database.getUsers().add(this);
	}
  
	public Parent(User user, Student child) {
		super(user);
		this.child = child;
	}    
	/**
	 * allows to see his/her child's transcript
	 * */
    public void viewTranscript() {
    	child.viewTranscript();
    	Database.getUserActions().add(new Action(this, new Date(), String.format("Parent %s viewed the transcript of the child", getFullName())));
    }
    /**
     * allows to see his/her child's marks
     * */
    public String viewMark(Course c) {
    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> t : getChild().getTranscript().entrySet()) {
    		if(t.getKey().getKey().equals(c)) {
    	    	Database.getUserActions().add(new Action(this, new Date(), String.format("Parent %s viewed the mark of the child %s for the course %s", getFullName(), child.getFullName(), c.getName())));
    			return c.getName() + ": " + t.getValue();
    		}
        }
    	return null;
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