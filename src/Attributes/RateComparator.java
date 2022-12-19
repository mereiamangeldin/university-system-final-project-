package Attributes;

import java.util.*;
public class RateComparator implements Comparator<Vector<Double>>{
	public int compare(Vector<Double> a, Vector<Double> b) {
		Double sumA = 0.0, sumB = 0.0;
		for(Double d: a) {
			sumA += d;
		}
		for(Double d: b) {
			sumB += d;
		}
		return sumA.compareTo(sumB);
	}
}
