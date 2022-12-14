package Attributes;

import Actors.*;
import java.util.Vector;

public class School {
	private String name;
    private Vector<Speciality> specialities;
    private Vector<Manager> managers;
    private Dean dean;
    
    public School(String name, Dean d) {
		this.name = name;
		this.dean = d;
		this.specialities = new Vector<Speciality>();
		this.managers = new Vector<Manager>();
	}
    
    {
    	Database.getSchools().add(this);
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
}