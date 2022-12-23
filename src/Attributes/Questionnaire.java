package Attributes;

import Actors.*;
import javafx.util.*;
import java.util.*;

/**Activities related to the assessment of teachers*/

public final class Questionnaire {
	private static HashMap<Teacher, Vector<Double>> rating;

	public Questionnaire() {
		rating = new HashMap<Teacher,Vector<Double>>();
	}
	
	private static Questionnaire instance = new Questionnaire();
	/**
	 * returns instance of Questionnaire
	 * */
	public static Questionnaire getInstance() {
		return instance;
	}
	/**
	 * to get rate of specific teacher
	 * */
	public static double getTeacherRate(Teacher teacher) {
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
	/**
	 * returns teacher with the highest rate
	 * */
	public static String getBestTeacher() {
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
	/**
	 * returns all the teachers with rates from max to min rate
	 * */
	public static Vector<Pair<Teacher, Double>> getTeachersRate() {
	    Vector<Pair<Teacher, Double>> sortedByRate = new Vector<Pair<Teacher, Double>>();
	    for(HashMap.Entry<Teacher, Vector<Double>> r: rating.entrySet()) {
	      sortedByRate.add(new Pair<Teacher, Double>(r.getKey(), getTeacherRate(r.getKey())));
	    }
	    Collections.sort(sortedByRate, new RateComparator());
	    return sortedByRate;
	  }
	/**
	 * returns all the teachers with their rating
	 * */
	public static HashMap<Teacher, Vector<Double>> getRating() {
		return rating;
	}
}