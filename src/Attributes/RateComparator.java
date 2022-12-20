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
}
