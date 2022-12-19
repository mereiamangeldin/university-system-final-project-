package Attributes;

import Actors.*;
import javafx.util.*;
import java.util.*;

public class Questionnaire {
	private HashMap<Teacher, Vector<Double>> rating;

	public Questionnaire() {
		rating = new HashMap<Teacher,Vector<Double>>();
	}
	
	public double getTeacherRate(Teacher teacher) {
		double rate = 0;
		int cnt = 0;
	    for(HashMap.Entry<Teacher, Vector<Double>> p : rating.entrySet()) {
	    	if(p.getKey().equals(teacher)) {
	    		for(Double d: p.getValue()) {
	    			rate += d;
	    			cnt++;
	    		}
	    	}
	    }
	    return rate / cnt;
	}
	public String getBestTeacher() {
		double rate = 0;
		String name = "";
		for(HashMap.Entry<Teacher, Vector<Double>> p : rating.entrySet()) {
	    	if(getTeacherRate(p.getKey()) > rate) {
	    		rate = getTeacherRate(p.getKey());
	    		name = p.getKey().getName();
	    	}
	    }
		return name + "is the best teacher!";
	}
	public Vector<Pair<Teacher, Double>> printTeachersByRate() {
		Vector<Pair<Teacher, Double>> sortedByRate = new Vector<Pair<Teacher, Double>>();
		for(HashMap.Entry<Teacher, Vector<Double>> r: rating.entrySet()) {
			sortedByRate.add(new Pair<Teacher, Double>(r.getKey(), getTeacherRate(r.getKey())));
		}
		Collections.sort(sortedByRate, new RateComparator());
		return sortedByRate;
	}
	
	public HashMap<Teacher, Vector<Double>> getRating() {
		return rating;
	}
	
	public void setRating(HashMap<Teacher, Vector<Double>> rating) {
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