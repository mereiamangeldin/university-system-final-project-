package Attributes;

import java.io.Serializable;
import java.util.Objects;

public class Organization implements Serializable {
	private String name;
    private int yearOfCreation;
    
    public Organization(String name, int yearOfCreation) {
		this.name = name;
		this.yearOfCreation = yearOfCreation;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYearOfCreation() {
		return yearOfCreation;
	}
	
	public void setYearOfCreation(int yearOfCreation) {
		this.yearOfCreation = yearOfCreation;
	}
	
	public int hashCode() {
		return Objects.hash(name, yearOfCreation);
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Organization other = (Organization) obj;
		return Objects.equals(name, other.name) && yearOfCreation == other.yearOfCreation;
	}

	public String toString() {
		return "Organization [name=" + name + ", yearOfCreation=" + yearOfCreation + "]";
	}
}