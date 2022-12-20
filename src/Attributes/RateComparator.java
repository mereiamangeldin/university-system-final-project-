package Attributes;

import java.util.*;
import Actors.*;
import javafx.util.*;

public class RateComparator implements Comparator<Pair<Teacher, Double>>{
	public int compare(Pair<Teacher, Double> a, Pair<Teacher, Double> b) {
		if(a.getValue().compareTo(b.getValue()) == 1) {
			return -1;
		} else if(a.getValue().compareTo(b.getValue()) == -1) {
			return 1;
		}
		return 0;
	}
	
//	
//	public int compare(Teacher t1, Teacher t2) {
//		double d1 = Questionnaire.getTeacherRate(t1);
//		double d2 = Questionnaire.getTeacherRate(t2);
//		if(d1 > d2) return 1;
//		if(d1 < d2) return -1;
//		return 0;
//	}
//}

//public class RateComparator implements Comparator<Teacher>{
//
//	public int compare(Teacher o1, Teacher o2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
}
