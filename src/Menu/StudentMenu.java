package Menu;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import Actors.*;
import Attributes.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;

public class StudentMenu {
	public static void menu(User user) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Student student = (Student)user;
		String menuStudent = "\nWelcome, Student: " + student.getFullName() + """
				\n1. Change password.
				2. View courses.
				3. Make book order.
				4. Make request.
				5. View news.
				6. View transcript.
				7. View mark.
				8. Register for course.
				9. Rate teachers.
				0. Logout.""";
		while(student.getLogged()) {
			System.out.println(menuStudent);
			String option = reader.readLine();
			if(option.equals("0")) {
				student.logout();
				System.out.println("You logged out.");
				Database.serializeAll();
				break;
			}
			else if(option.equals("1")) {
				Menu.changePassword(student, reader);
			} 
			else if(option.equals("2")) {
				System.out.println(student.getCourses());
			}
			else if(option.equals("3")) {
				StudentMenu.bookOrdering(student, reader);
			}
			else if(option.equals("4")) {
				StudentMenu.makeRequest(student, reader);
			} 
			else if(option.equals("5")) {
				StudentMenu.viewNews(student, reader);
			}
			else if(option.equals("6")) {
				student.viewTranscript();
			}
			else if(option.equals("7")) {
				StudentMenu.viewMark(student, reader);
			}
			else if(option.equals("8")) {
				StudentMenu.registeringForCourse(student, reader);
			}
			else if(option.equals("9")) {
				StudentMenu.rateTeacher(student, reader);
			}
		}
	}
					
		public static void makeRequest(Student student, BufferedReader reader) throws IOException {
			String requestMenu = "Who do you want to contact?\n1. Technical Support Center.\n2. Dean's office.\n3.Office of the register.\n.0.Back.";
			String option;
			while(true) {
				System.out.println(requestMenu);
				option = reader.readLine();
				if(option.equals("0")) {
					break;
				}
				String id, text;
				int i = 1;
				if(option.equals("1")) {
					for(TechSupportWorker t : Database.getTechSupportWorkers()) {
						System.out.println(i + ". " + t.getId() + " " + t.getFullName());
						i += 1;
					}
					System.out.print("Enter the id of the employee you want to write a request to: ");
					id = reader.readLine();
					System.out.print("Text the description of your request: ");
					text = reader.readLine();
					student.makeRequest(new Request(student.getId(), RequestType.EmployeeRequest, text), Database.getTechSupportWorkerById(id));
				}
				if(option.equals("2")) {
					i = 1;
					for(School s : Database.getSchools()) {
						System.out.println(i + ". " + s.getName());
						i += 1;
					}
					System.out.print("What school do you want to apply to? (enter number): ");
					System.out.println(Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getManagers());
					System.out.print(String.format("Enter the id of the manager of %s", Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getName()));
					id = reader.readLine();
					System.out.print("Text the description of your request: ");
					text = reader.readLine();
					student.makeRequest(new Request(student.getId(), RequestType.EmployeeRequest, text), Database.getManagerById(id));
				}
				if(option.equals("3")) {
					System.out.println(Database.getORManagers());
					System.out.print("Enter the id of the manager of office of the register: ");
					id = reader.readLine();
					System.out.println("Text the description of your request: ");
					text = reader.readLine();
					student.makeRequest(new Request(student.getId(), RequestType.EmployeeRequest, text), Database.getManagerById(id));
				}		
			}
		}
		
		public static void viewNews(Student student, BufferedReader reader) throws IOException {
			int newsOrder = 1; 
			for(News n : Database.getNews()) {
				System.out.println(newsOrder + ". " + n);
				newsOrder += 1;
			}
			String newsMenu = ("""
					1. Comment news.
					0. Back. """);
			while(true) {
				System.out.println(newsMenu);
				String option = reader.readLine();
				if(option.equals("1")) {
					System.out.print("Enter number of news: ");
					newsOrder = Integer.parseInt(reader.readLine());
					System.out.print("Enter comment: ");
					String comment = reader.readLine();
					student.writeComment(comment, Database.getNews().get(newsOrder - 1));
					System.out.println(Database.getNews().get(newsOrder - 1));
					System.out.println("You commented on the news.");
				} else if(option.equals("0")) {
					return;
				}
			}
		}
		
		public static void bookOrdering(Student student, BufferedReader reader) throws IOException {
			for(Book b : Database.getBooks()) {
				System.out.println(b);
			}
			System.out.print("Id of the book you want to order: ");
			int id = Integer.parseInt(reader.readLine());
			Book b = Database.getBookById(id);
			System.out.println(Database.getLibrarians());
			System.out.print("Choose the library staff (Enter ID): ");
			String i = reader.readLine();
			Librarian l = Database.getLibrarianById(i);
			if(b != null) {
				if(l != null) {
					student.makeBookOrder(l, new Order(student, b));	
				} else {
					System.out.println("Librarian does not found");
				}
			} else {
				System.out.println("Book is not found");
			}
		}
		
		public static void viewMark(Student student, BufferedReader reader) throws IOException {
			System.out.println(Database.getCourses());
			System.out.println("Enter id of the course: ");
			String id = reader.readLine();
			Course c = Database.getCourseById(id);
			student.viewMark(c);
		}
		
		public static void registeringForCourse(Student student, BufferedReader reader) throws IOException {
			for(Course c : Database.getCourses()) {
				System.out.println(String.format(("Course id: %s, name: %s, number of credits: %s, school: %s, type: %s"), c.getId(), c.getName(), c.getNumberOfCredits(), c.getSchool().getName(), c.getType()));
			}
			System.out.print("Enter id of the course: ");
			String id = reader.readLine();
			Course c = Database.getCourseById(id);
			if(c == null) {
				System.out.println("Course not found");
				return;
			}
			System.out.println(c.getTeachers());
			System.out.print("Enter teacher id: ");
			id = reader.readLine();
			Teacher t = Database.getTeacherById(id);
			System.out.println(Database.getORManagers());
			System.out.println("Enter manager id: ");
			id = reader.readLine();
			Manager m = Database.getManagerById(id);
			if(c != null) {
				if(t != null) {
					if(m != null) {
						student.registerForCourse(c, t, m);
					} else {
						System.out.println("Manager does not found");
					}
				} else {
					System.out.println("Teacher does not found");

				}
			} else {
				System.out.println("Course does not found");
			}
		}
		
		public static void rateTeacher(Student student, BufferedReader reader) throws IOException {
			System.out.println(student.getTeachers());
			System.out.print("Teacher id: ");
			String id = reader.readLine();
			Teacher t = Database.getTeacherById(id);
			System.out.print("Rate the teacher on a 10-point scale: ");
			double mark = Double.parseDouble(reader.readLine());
			student.rateTeacher(t, mark);
			System.out.println("Thank you for your feedback!");
		}
}