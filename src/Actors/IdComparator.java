package Actors;

import java.util.Comparator;

public class IdComparator implements Comparator<Student>{
	public int compare(Student e1, Student e2) {
		return e1.getId().compareTo(e2.getId());
	}
}