package Attributes;

import Actors.*;
import java.util.HashMap;

public class Questionnaire {
	private HashMap<Teacher, Double> rating;

	public Questionnaire() {
		rating = new HashMap<Teacher, Double>();
	}
	
	public double getTeacherRate(Teacher teacher) {
	    return 0;
	}
	
	public String getBestTeacher() {
	    return null;
	}

	public void printTeachersByRate(String type) {
	}
	
	public HashMap<Teacher, Double> getRating() {
		return rating;
	}
	
	public void setRating(HashMap<Teacher, Double> rating) {
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