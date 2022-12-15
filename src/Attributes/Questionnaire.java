package Attributes;

import Actors.*;
import java.util.*;
import javafx.util.*;

public class Questionnaire {
	private Vector<Pair<Teacher, Double>> rating;

	public Questionnaire() {
		rating = new Vector<Pair<Teacher, Double>>();
	}
	
	public double getTeacherRate(Teacher teacher) {
		double rate = 0;
		int cnt = 0;
	    for(Pair<Teacher, Double> p : rating) {
	    	if(p.getKey().equals(teacher)) {
	    		rate += p.getValue();
	    		cnt++;
	    	}
	    }
	    return rate / cnt;
	}
	
	public String getBestTeacher() {
		double rate = 0;
		String name = "";
		HashSet <Teacher> teacher = new HashSet<Teacher>();
		for(Pair<Teacher, Double> p : rating) {
	    	teacher.add(p.getKey());
	    }
		for(Teacher t : teacher) {
	    	if(getTeacherRate(t) > rate) {
	    		rate = getTeacherRate(t);
	    		name = t.getName();
	    	}
	    }
		return name + "is the best teacher!";
	}

	public void printTeachersByRate(String type) {
		
	}
	
	public Vector<Pair<Teacher, Double>> getRating() {
		return rating;
	}
	
	public void setRating(Vector<Pair<Teacher, Double>> rating) {
		this.rating = rating;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Questionnaire other = (Questionnaire) obj;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}

	public String toString() {
		return "Questionnaire [rating=" + rating + "]";
	}
}