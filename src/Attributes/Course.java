package Attributes;

import Actors.*;

import java.io.Serializable;
import java.util.Vector;
import Enums.*;

public class Course implements Serializable {
	private String id;
    private String name;
    private Course prerequisite;
    private int numberOfCredits;
    private School school;
    private ScienceDegree scienceDegree;
    private Vector<File> files;
    private CourseType type;
    private Vector<Teacher> teachers;
    
    public Course(String id, String name, Course prerequisite, int numberOfCredits, School school, ScienceDegree scienceDegree, CourseType type) {
    	this.id = id;
		this.name = name;
		this.prerequisite = prerequisite;
		this.numberOfCredits = numberOfCredits;
		this.school = school;
		this.scienceDegree = scienceDegree;
		this.type = type;
		this.files = new Vector<File>();
		this.teachers = new Vector<Teacher>();
	}
    
    {
    	Database.getCourses().add(this);
    }
        
    public Vector<Course> getCoursesOfTeacher(Teacher t) {
    	return Database.getTeachersCourse(t);
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
	
	public Course getPrerequisite() {
		return prerequisite;
	}
	
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
	}
	
	public int getNumberOfCredits() {
		return numberOfCredits;
	}
	
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	
	public School getSchool() {
		return school;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	public ScienceDegree getScienceDegree() {
		return scienceDegree;
	}
	
	public void setScienceDegree(ScienceDegree scienceDegree) {
		this.scienceDegree = scienceDegree;
	}
	
	public Vector<File> getFiles() {
		return files;
	}
	
	public void setFiles(Vector<File> files) {
		this.files = files;
	}
	
	public CourseType getType() {
		return type;
	}
	
	public void setType(CourseType type) {
		this.type = type;
	}
	
	public File getCourseFile(int id) {
		for(File f : files) {
			if(f.getId() == id) {
				return f;
			}
		}
		return null;
	}

	public String toString() {
    	String answer = String.format("Course: %s, id: %s, number of credits: %s, school: %s, science degree: %s, type: %s",
				this.getName(), this.getId(), this.getNumberOfCredits(), this.getSchool().getShortName(), this.getScienceDegree().name(), this.getType().name());
    	return answer;
	}

	public Vector<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Vector<Teacher> teachers) {
		this.teachers = teachers;
	}
}