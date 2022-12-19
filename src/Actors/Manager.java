package Actors;

import Enums.*;
import Interfaces.*;
import Attributes.*;
import java.io.*;
import java.util.*;

public class Manager extends Employee implements CanViewMarks {
	private ManagerType type;
	private HashMap<Request, Boolean> requests;
	private BufferedReader reader = null;
  
	public Manager() {
		super();
	}

	public Manager(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber, ManagerType type) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
		this.type = type;
	}
  
	{
		Database.getManagers().add(this);
	}

	public void viewMark(Course c) {
		for(Student s: Database.getStudents()) {
			s.viewMark(c);
		}
	}
  
	public void addNews() throws IOException {
		System.out.print("Write title: ");
		String title = reader.readLine();
		System.out.print("Write text of the news:");
		String text = reader.readLine();
		Database.getNews().add(new News(title, text));
		System.out.println("The news was added successfully.");
	}
  
	public void removeNews(News news) {
		for(News n : Database.getNews()) {
			if(n.equals(news)) {
				Database.getNews().remove(n);
				System.out.println("The news was deleted successfully");
			}
		}
	}
  
	public boolean assignCourseToTeacher() throws IOException {
		System.out.print("Enter course id here: ");
		String courseID = reader.readLine();
		for(Course c : Database.getCourses()) {
			if(courseID.equals(c.getId())) {
				String teacherID = reader.readLine();
        for(Teacher t : Database.getTeachers()) {
        	if(teacherID.equals(t.getId())) {
        		if(c.getClass().equals(t.getClass())) {
        			c.getTeachers().add(t);
        			return true;
        		}
        	}
        }
        System.out.println("Teacher not found!");
        return false;
			}
		}
		System.out.println("Course not found!");
		return false;
	}
  
	public void viewEmployeesRequests(Request requests) {
		System.out.println(requests);
	}
  
	public boolean approveStudentsRegistration(Student s, Course c) {
		return true;
	}
  
//    private String id;
//    private String name;
//    private Vector<Teacher> teachers;
//    private Course prerequisite;
//    private int numberOfCredits;
//    private School school;
//    private ScienceDegree scienceDegree;
//    private Vector<File> files;
//    private CourseType type;
    
	public void addCoursesForRegistration() throws IOException {
		System.out.print("Enter name of the course: ");
		String name = reader.readLine();
		System.out.println("Enter id of the course: ");
		String id = reader.readLine();
		System.out.println("Enter prerequisite of the course: ");
		int numberOfCredits = Integer.parseInt(reader.readLine());
		System.out.println("Choose the school of the course: ");
		int i = 1;
		for(School s : Database.getSchools()) {
			System.out.println(i + "." + s.getName());
		}
		System.out.println("Choose the science degree of the course:\n1. Bachelor\n2. Master\n3.PhD");
		String input = reader.readLine();
		ScienceDegree s = null;
		switch(input) {
			case "1" -> s = ScienceDegree.BACHELOR;
			case "2" -> s = ScienceDegree.MASTER;
			case "3" -> s = ScienceDegree.PHD;
		}
		System.out.println("Choose the course type: ");
		input = reader.readLine();
		CourseType c = null;
		switch(input) {
			case "1" -> c = CourseType.REQUIRED;
			case "2" -> c = CourseType.MINOR;
			case "3" -> c = CourseType.MAJOR;
			case "4" -> c = CourseType.FREE;
		}
    
//    Database.getCourses().add(new Course(id, name, ???, numberOfCredits, ???, s, c));
//    String input = reader.readLine();
//    School s = "";
//     public Course(String id, String name, Course prerequisite, int numberOfCredits,
//          School school, ScienceDegree scienceDegree, CourseType type) {
//    switch(input) {
//      case "1" -> s = School.
//    }
  }
  
	public void createReport(Course course) {
		double mx = 101, mn = -1, total = 0, n = 0;
	    for(Student s : Database.getStudents()) {
	    	for(HashMap.Entry<Course, Mark> marks : s.getTranscript().entrySet()) {
	    		if(marks.getKey().equals(course) && marks.getValue().getLetterGrade() != 'F' && marks.getValue().getLetterGrade() != 'N') {
	    			total += marks.getValue().getTotal();
	    			n += 1;
	    			if(mx < marks.getValue().getTotal()) mx = marks.getValue().getTotal();
	    				if(mn > marks.getValue().getTotal()) mn = marks.getValue().getTotal();
	        }
	      }
	    }
	    System.out.println(course.getName()+ ":");
	    System.out.println("Number of students who have passed: " + n);
	    System.out.println("Minimum grade: " + mn);
	    System.out.println("Maximum grade: " + mx);
	    System.out.println("Average grade: " + total/n);
	  }
	  
//	  public void viewTeachersBy(Questionnaire q, String type) {
//	    if(type.equals("1")) {
//	      Collections.sort(Database.getTeachers());
//	    }
//	    if(type.equals("2")) {
//	      Collections.sort(Database.getTeachers(), new NameComparator());
//	    }
//	  }
	  
	  public boolean sendRequestToDean(Dean d, Request r) {
	    return d.signRequest(r);
	  }
	  

	  public ManagerType getType() {
	    return type;
	  }

	  public void setType(ManagerType type) {
	    this.type = type;
	  }

	  public HashMap<Request, Boolean> getRequests() {
	    return requests;
	  }

	  public void setRequests(HashMap<Request, Boolean> requests) {
	    this.requests = requests;
	  }

	  public String toString() {
	    return "Manager type = " + type + ", requests=" + requests;
	  }

	  public int hashCode() {
	    return Objects.hash(super.hashCode(), this.type, this.requests);
	  }

	  public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!super.equals(obj)) return false;
	    if (getClass() != obj.getClass()) return false;
	    Manager other = (Manager) obj;
	    return Objects.equals(requests, other.requests) && Objects.equals(type, other.type);
	  }
}