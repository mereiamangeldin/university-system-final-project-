package Attributes;

import java.util.Vector;
import Enums.*;

public class Speciality {
	private String id;
    private String name;
    private Vector<Course> necessaryDiscipline;
    private ScienceDegree scienceDegree;
    
    public Speciality(String id, String name, Vector<Course> necessaryDiscipline, ScienceDegree scienceDegree) {
		this.id = id;
		this.name = name;
		this.necessaryDiscipline = necessaryDiscipline;
		this.scienceDegree = scienceDegree;
	}
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector<Course> getNecessaryDiscipline() {
		return necessaryDiscipline;
	}
	
	public void setNecessaryDiscipline(Vector<Course> necessaryDiscipline) {
		this.necessaryDiscipline = necessaryDiscipline;
	}
	
	public ScienceDegree getScienceDegree() {
		return scienceDegree;
	}
	
	public void setScienceDegree(ScienceDegree scienceDegree) {
		this.scienceDegree = scienceDegree;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((necessaryDiscipline == null) ? 0 : necessaryDiscipline.hashCode());
		result = prime * result + ((scienceDegree == null) ? 0 : scienceDegree.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Speciality other = (Speciality) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (necessaryDiscipline == null) {
			if (other.necessaryDiscipline != null)
				return false;
		} else if (!necessaryDiscipline.equals(other.necessaryDiscipline))
			return false;
		if (scienceDegree != other.scienceDegree)
			return false;
		return true;
	}

	public String toString() {
		return "Specialty [id=" + id + ", name=" + name + ", necessaryDiscipline=" + necessaryDiscipline
				+ ", scienceDegree=" + scienceDegree + "]";
	}
}