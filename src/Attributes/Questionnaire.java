package Attributes;

import Actors.*;
import java.util.*;
import java.util.stream.Collectors;

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
	public Double sumV(Vector <Double> vector) {
		Double sum = 0.0;
		for(Double d: vector) {
			sum += d;
		}
		return sum;
	}
	public void printTeachersByRate() {
		HashMap<Teacher, Vector<Double>> stream = rating.entrySet().stream().sorted(Collections.reverseOrder(HashMap.Entry.comparingByValue(new RateComparator()))).collect(Collectors.toMap
				(HashMap.Entry::getKey, HashMap.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println(stream);
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