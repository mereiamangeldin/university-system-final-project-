package Actors;

import Enums.*;
import Interfaces.*;
import javafx.util.Pair;
import Attributes.*;
import java.io.*;
import java.util.*;

public class Manager extends Employee implements CanViewMarks {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManagerType type;
	private Vector<Request> requests;
	private BufferedReader reader = null;
	{
		requests = new Vector<Request>();
	}
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
		Database.getUserActions().add(String.format("User: %s viewed mark of course: ", super.getUsername(), c.getName()));
		for(Student s: Database.getStudents()) {
			s.viewMark(c);
		}
	}
	public void processRequests(int pos) {
		if(pos > this.requests.size()) return;
		Request r = requests.elementAt(pos-1);
		if(r.getTitle().equals(requestType.employeeRequest)) {
			for(School s : Database.getSchools()) {
	    		  if(s.getManagers().contains(this)) {
	    			  if(this.sendRequestToDean(s.getDean(), r)) {
	    				  requests.remove(pos-1);
	    				  return;
	    			  }
	    		  }
	    	  }
		}
		else {
			String studentID = r.getUserID();
			int delimeter = r.getDescription().indexOf(' ');
			String courseID = r.getDescription().substring(0, delimeter);
			String teacherID = r.getDescription().substring(delimeter+1, r.getDescription().length());
			for(Course c : Database.getCourses()) {
				if(c.getId().equals(courseID)) {
					for(Teacher t : Database.getTeachers()) {
						if(t.getId().equals(teacherID)) {
							for(Student s : Database.getStudents()) {
								if(s.getId().equals(studentID)) {
									int amountOfCredits = 0;
							    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
							    		if(marks.getValue().getFinalScore() == 0 && marks.getValue().getLetterGrade() != 'F') {
							    			amountOfCredits += marks.getKey().getKey().getNumberOfCredits();
							    		}
							    	}
							    	if(amountOfCredits+c.getNumberOfCredits() > 30) return;
							    		if(c.getPrerequisite() != null) {
							    			for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
							    				if(marks.getKey().getKey().equals(c.getPrerequisite())) {
							    					if(marks.getValue().getLetterGrade() != 'F' && marks.getValue().getLetterGrade() != 'N') {
							    						Database.getUserActions().add(String.format("User: %s has registered for course: %s", super.getUsername(), c.getName()));
							    						s.getTranscript().put(new Pair<Course, Teacher>(c, t), new Mark());
							    						break;
							    					}
							    				}
							    			}
							    		}
								        else {
								        	Database.getUserActions().add(String.format("User: %s has registered for course: %s", super.getUsername(), c.getName()));
								        	s.getTranscript().put(new Pair<Course, Teacher>(c, t), new Mark());
								        }
								}
							}
							break;
						}
					}
					break;
				}
			}
		}
		requests.remove(pos-1);
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
		Database.getUserActions().add(String.format("User: %s removed news: ", super.getUsername(), news.getTitle()));
		for(News n : Database.getNews()) {
			if(n.equals(news)) {
				Database.getNews().remove(n);
				System.out.println("The news was deleted successfully");
			}
		}
	}
  
	public boolean assignCourseToTeacher(String courseID, String teacherID) throws IOException {
		for(Course c : Database.getCourses()) {
			if(courseID.equals(courseID)) {
		        for(Teacher t : Database.getTeachers()) {
		        	if(teacherID.equals(teacherID)) {
		        			c.getTeachers().add(t);
		        			return true;
		        	}
		        }
		        return false;
			}
		}
		return false;
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
	    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
	    		if(marks.getKey().getKey().equals(course) && marks.getValue().getLetterGrade() != 'F' && marks.getValue().getLetterGrade() != 'N') {
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

	  public Vector<Request> getRequests() {
	    return requests;
	  }

	  public void setRequests(Vector<Request> requests) {
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