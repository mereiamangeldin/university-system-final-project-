package Actors;

import Enums.*;
import Interfaces.*;
import javafx.util.Pair;
import Attributes.*;
import java.io.*;
import java.util.*;

public class Manager extends Employee implements CanViewMarks, Serializable {
	private static final long serialVersionUID = 1L;
	private ManagerType type;
	private Vector<Request> requests;
	private BufferedReader reader = null;
	
	public Manager() {
		super();
	}

	public Manager(String name, String surname, String password, Date dateOfBirth, String id, Date hireDate, double salary, String insuranceNumber, ManagerType type) {
		super(name, surname, password, dateOfBirth, id, hireDate, salary, insuranceNumber);
		this.type = type;
		requests = new Vector<Request>();
		if(this.type.equals(ManagerType.SITE)) {
			Database.getSchoolByName("SITE").getManagers().add(this);
		} else if(this.type.equals(ManagerType.BS)) {
			Database.getSchoolByName("BS").getManagers().add(this);
		} else if(this.type.equals(ManagerType.ISE)) {
			Database.getSchoolByName("ISE").getManagers().add(this);
		} else if(this.type.equals(ManagerType.SAM)) {
			Database.getSchoolByName("SAM").getManagers().add(this);
		}
	}
	
	{
		Database.getUsers().add(this);
	}
	
	// Видит оценки студентов определенного курса
	public String viewMark(Course c) {
		for(Student s: Database.getStudents()) {
	    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
				if(marks.getKey().getKey().equals(c)) {
					System.out.println(s.getId()  + " " + s.getFullName() + " " + marks.getValue().getTotal());
					Database.getUserActions().add(new Action(this, new Date(), String.format("Manager: %s viewed mark of course: ", getUsername(), c.getName())));
					return s.getId()  + " " + s.getFullName() + " " + marks.getValue().getTotal();
				}
	    	}
		}
		return null;
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
							    						Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s has registered for course: %s", s.getUsername(), c.getName())));
							    						s.getTranscript().put(new Pair<Course, Teacher>(c, t), new Mark());
							    						break;
							    					}
							    				}
							    			}
							    		}
								        else { // Если у курса нет пререквизита, то регистрируем студента на курс
								        	Database.getUserActions().add(new Action(this, new Date(), String.format("Student: %s has registered for course: %s", s.getUsername(), c.getName())));
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
	public String addNews(String title, String text) throws IOException {
		Database.getNews().add(new News(title, text));
		Database.getUserActions().add(new Action(this, new Date(), String.format("Manager: %s added news: ", getUsername())));
		return "The news was added successfully.";
	}
  
	public String removeNews(News news) {
		Database.getUserActions().add(new Action(this, new Date(), String.format("Manager: %s removed news: ", getUsername(), news.getTitle())));
		for(News n : Database.getNews()) {
			if(n.equals(news)) {
				Database.getNews().remove(n);
				return "The news was deleted successfully";
			}
		}
		return "News was not found";
	}
  
	public boolean assignCourseToTeacher(Course course, Teacher teacher) throws IOException {
		if(course.getSchool().equals(teacher.getSchool())){
			if(course.getTeachers().contains(teacher)) {
				System.out.println("V vectore est uje");
				return false;
			} else {
				return true;
			}
		} else {
			System.out.println("Schools are different.");
			return false;
		}
	}

	public String addCoursesForRegistration(Course newCourse) throws IOException {
		boolean found = false;
		for(Course c : Database.getCourses()) {
			if(c.equals(newCourse)) {
				found = true;
				break;
			}
		}
		if(!found) {
			Database.getCourses().add(newCourse);
			Database.getUserActions().add(new Action(this, new Date(), String.format("Manager %s added new course ($s) for registration", getFullName(), newCourse.getName())));
			return "Course is successfully added";
		}
		return "Course is already exist";
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
	    Database.getUserActions().add(new Action(this, new Date(), String.format("Manager %s created report for course %s", getFullName(), course.getName())));
	 }
	  
	  public Vector<Teacher> viewTeachersAlphabetically() {
		  Vector<Teacher> v = Database.getTeachers();
		  Collections.sort(v);
		  return v;
	  }
	  
	  public Vector<Pair<Teacher, Double>> viewTeacherByRate(){
		  Vector<Pair<Teacher, Double>> v = Questionnaire.getTeachersRate();
		  return v;
	  }
	  
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