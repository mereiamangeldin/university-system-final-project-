import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
public class Student implements Serializable{
	int id;
	String name;
	HashMap<Course,Mark> courses;
	{
		courses = new HashMap<Course,Mark>();
	}
	public Student() {
		
	}
	
	public Student(String name) {
		this.id = Data.nextId();
		this.name = name;
	}
	public boolean addCourse(Course c){
		//check prereq, credits, faculty
        courses.put(c, new Mark());
        return true;
	}
	public String toString(){
		return name+ ", id is "+id+", registered courses:  "+(courses.size()==0?"No courses yet ":courses);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return this.id == other.id;
	}
}
