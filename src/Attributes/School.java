package Attributes;

import Actors.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class School implements Serializable {
	private String name;
    private Vector<Speciality> specialities;
    private Vector<Manager> managers;
    private Dean dean;
    private String shortName;
    
    public School(String name, Dean d, String shortName) {
		this.name = name;
		this.dean = d;
		this.specialities = new Vector<Speciality>();
		this.managers = new Vector<Manager>();
		this.shortName = shortName;
	}
    
    {
    	Database.getSchools().add(this);
    }
    
    public String getShortName() {
    	return shortName;
    }
    
    public void setShortName(String shortName) {
    	this.shortName = shortName;
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector<Speciality> getSpecialities() {
		return specialities;
	}
	
	public void setSpecialities(Vector<Speciality> specialities) {
		this.specialities = specialities;
	}
	
	public Vector<Manager> getManagers() {
		return managers;
	}
	
	public void setManagers(Vector<Manager> managers) {
		this.managers = managers;
	}
	
	public Dean getDean() {
		return dean;
	}
	
	public void setDean(Dean d) {
		this.dean = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dean, managers, name, specialities);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		return Objects.equals(dean, other.dean) && Objects.equals(managers, other.managers)
				&& Objects.equals(name, other.name) && Objects.equals(specialities, other.specialities);
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", specialities=" + specialities + ", managers=" + managers + ", dean=" + dean
				+ "]";
	}
}