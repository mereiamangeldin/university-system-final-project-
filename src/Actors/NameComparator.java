package Actors;
import java.util.Comparator;

public class NameComparator implements Comparator<User>{
	public int compare(User e1, User e2) {
		int byName = e1.getName().compareTo(e2.getName()); // by name 
		if(byName != 0) {
			return byName;
		}
		return e1.getSurname().compareTo(e2.getSurname()); // then by surname
	}
}