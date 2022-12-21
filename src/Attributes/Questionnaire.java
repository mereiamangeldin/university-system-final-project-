package Attributes;

import Actors.*;
import javafx.util.*;
import java.util.*;

public final class Questionnaire {
	private static HashMap<Teacher, Vector<Double>> rating;

	public Questionnaire() {
		rating = new HashMap<Teacher,Vector<Double>>();
	}
	
	private static Questionnaire instance = new Questionnaire();
	
	public static Questionnaire getInstance() {
		return instance;
	}
	
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
	
	public static Vector<Pair<Teacher, Double>> getTeachersRate() {
		Vector<Pair<Teacher, Double>> sortedByRate = new Vector<Pair<Teacher, Double>>();
		for(HashMap.Entry<Teacher, Vector<Double>> r: rating.entrySet()) {
			sortedByRate.add(new Pair<Teacher, Double>(r.getKey(), getTeacherRate(r.getKey())));
		}
		Collections.sort(sortedByRate, new RateComparator());
		return sortedByRate;
	}
	
	public static HashMap<Teacher, Vector<Double>> getRating() {
		return rating;
	}
}