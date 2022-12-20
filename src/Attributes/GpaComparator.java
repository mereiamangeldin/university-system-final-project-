package Attributes;

import java.util.Comparator;
import Actors.Student;

public class GpaComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return 0;
	}

//	public int compare(Student s1, Student s2) {
//		if(s1.getGpa() > s2.getGpa()) return 1;
//		if(s1.getGpa() < s2.getGpa()) return -1;
//		return 0;
//	}
}
