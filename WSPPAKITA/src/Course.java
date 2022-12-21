import java.io.Serializable;
public class Course implements Serializable {
	public String name;
	public Course(String name) {
		super();
		this.name = name;
	}
	public String toString(){
		return name;
	}
}
