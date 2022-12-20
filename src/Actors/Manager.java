package Actors;

import Enums.*;
import Interfaces.*;
import javafx.util.Pair;
import Attributes.*;
import java.io.*;
import java.util.*;

public class Manager extends Employee implements CanViewMarks {
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

	// Видит оценки студентов определенного курса
	public void viewMark(Course c) {
		for(Student s: Database.getStudents()) {
	    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
				if(marks.getKey().getKey().equals(c)) {
					System.out.println(s.getId()  + " " + s.getFullName() + " " + marks.getValue().getTotal());
				}
	    	}
		}
		Database.getUserActions().add(String.format("Manager: %s viewed mark of course: ", getUsername(), c.getName()));
	}
	
	// Обработка запроса менеджера под определенным индексом в векторе запросов 
	public void processRequests(int pos) {
		if(pos > this.requests.size()) return;
		Request r = requests.elementAt(pos - 1);
		if(r.getTitle().equals(RequestType.EmployeeRequest)) { // Запрос, поступивший от работников 
			for(School s : Database.getSchools()) {
	    		  if(s.getManagers().contains(this)) {
	    			  if(this.sendRequestToDean(s.getDean(), r)) {
	    				  requests.remove(pos - 1);
	    				  return;
	    			  }
	    		  }
	    	  }
		}
		else { // Запрос на регистрацию на курс от студента 
			String studentID = r.getUserID(); 
			int delimeter = r.getDescription().indexOf(' ');
			String courseID = r.getDescription().substring(0, delimeter);
			String teacherID = r.getDescription().substring(delimeter + 1, r.getDescription().length());
			for(Course c : Database.getCourses()) {
				if(c.getId().equals(courseID)) { // Находит курс 
					for(Teacher t : c.getTeachers()) {
						if(t.getId().equals(teacherID)) { // Находит тичера 
							for(Student s : Database.getStudents()) {
								if(s.getId().equals(studentID)) { // Находит студента 
									int amountOfCredits = 0;
									// Тут высчитываем количество кредитов, сколько у студента сейчас 
							    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
							    		if(marks.getValue().getFinalScore() == 0 && marks.getValue().getLetterGrade() != 'F') {
							    			amountOfCredits += marks.getKey().getKey().getNumberOfCredits();
							    		}
							    	}
							    	if(amountOfCredits + c.getNumberOfCredits() > 21) return;
							    		if(c.getPrerequisite() != null) { // Если у курса есть пререквизит, то проверяем, прошел ли он этот пререквизит (не на ритейк)
							    			for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
							    				if(marks.getKey().getKey().equals(c.getPrerequisite())) {
							    					if(marks.getValue().getLetterGrade() != 'F' && marks.getValue().getLetterGrade() != 'N') {
							    						Database.getUserActions().add(String.format("Student: %s has registered for course: %s", s.getUsername(), c.getName()));
							    						s.getTranscript().put(new Pair<Course, Teacher>(c, t), new Mark());
							    						break;
							    					}
							    				}
							    			}
							    		}
								        else { // Если у курса нет пререквизита, то регистрируем студента на курс
								        	Database.getUserActions().add(String.format("Student: %s has registered for course: %s", s.getUsername(), c.getName()));
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
		requests.remove(pos - 1);
	}
	
	// Managing news part (Adding and removing)
	public void addNews() throws IOException {
		System.out.print("Write title: ");
		String title = reader.readLine();
		System.out.print("Write text of the news:");
		String text = reader.readLine();
		Database.getNews().add(new News(title, text));
		System.out.println("The news was added successfully.");
		Database.getUserActions().add(String.format("Manager: %s added news: ", getUsername()));
	}
  
	public void removeNews(News news) {
		Database.getUserActions().add(String.format("Manager: %s removed news: ", getUsername(), news.getTitle()));
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
		        			// ДОБАВИТЬ ПРОВЕРКУ РАВНЫ ЛИ ИХ ШКОЛЫ? 
		        			c.getTeachers().add(t);
		        			return true;
		        	}
		        }
		        return false;
			}
		}
		return false;
	}

	public void addCoursesForRegistration() throws IOException {
		System.out.print("Enter id of the course: ");
		String id = reader.readLine();
		System.out.print("Enter name of the course: ");
		String name = reader.readLine();
		int i = 1;
		for(Course c : Database.getCourses()) {
			System.out.println(i + ". " + c.getId() + " " + c.getName());
			i += 1;
		}
		System.out.print("Enter course prerequisite (number): ");
		Course prerequisite = Database.getCourses().get(Integer.parseInt(reader.readLine()) - 1);
		System.out.print("Enter the number of course credits:");
		int numberOfCredits = Integer.parseInt(reader.readLine());
		System.out.print("Enter the school of the course (number): ");
		i = 1;
		for(School s : Database.getSchools()) {
			System.out.println(i + ". " + s.getName());
			i += 1;
		}
		School s = Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1);
		System.out.println("Choose the science degree of the course:\n1. Bachelor\n2. Master\n3.PhD");
		String input = reader.readLine();
		ScienceDegree scienceDegree = null;
		switch(input) {
			case "1" -> scienceDegree = ScienceDegree.BACHELOR;
			case "2" -> scienceDegree = ScienceDegree.MASTER;
			case "3" -> scienceDegree = ScienceDegree.PHD;
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
		Course newCourse = new Course(id, name, prerequisite, numberOfCredits, s, scienceDegree, c);
		Database.getUserActions().add(String.format("Manager %s added new course ($s) for registration", getFullName(), newCourse.getName()));
  }

	// Выводим количество студентов, прошедших/изучающих (?) курс, максимальную, минимальную и среднюю оценку
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
	    System.out.println(course.getName() + ":");
	    System.out.println("Number of students who have passed: " + n);
	    System.out.println("Minimum grade: " + mn);
	    System.out.println("Maximum grade: " + mx);
	    System.out.println("Average grade: " + total / n);
	 }
	  
	  public Vector<Teacher> viewTeachersAlphabetically() {
		  Vector<Teacher> v = Database.getTeachers();
		  Collections.sort(v);
		  return v;
	  }
	  
//	  public Vector<Pair<Teacher, Double>> viewTeacherByRate(){
//		  Vector<Pair<Teacher, Double>> v = Questionnaire.getTeacherRate();
//		  return v;
//	  }
	  
	  public boolean sendRequestToDean(Dean d, Request r) {
	    return d.signRequest(r);
	  }
	  
	  // getters and setters
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

	  // hashCode(), toString() and equals()
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