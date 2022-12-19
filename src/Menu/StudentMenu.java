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
			switch(option) {
				case "0":
					student.logout();
					System.out.println("You logged out.");
					break;	
				case "1":
					Menu.changePassword(student, reader);	
					break;
				case "2":
					student.viewCourses();
					break;
				case "3":
					System.out.println(Database.getBooks());
					System.out.print("ID of the book you want to order: ");
					String id = reader.readLine();
//					Book b = Database.getBookById(id);
					System.out.println(Database.getLibrarians());
					System.out.println("Choose the library staff (Enter ID): ");
//					Librarian l = Database.getLibrarianById(id);
//					student.makeBookOrder(l, new Order(student, b);
					break;
				case "4":
					System.out.println("Who do you want to contact?"
							+ "\n1. Dean's office"
							+ "\n2. Office of the registration"
							+ "\n3. Tech support office");
					option = reader.readLine();
					switch(option) {
					case "1":
						for(Manager m : student.getSchool().getManagers()) {
							System.out.println(m.getId() + " " + m.getFullName());
						}
						System.out.print("Choose the id of manager you want to write: ");
						id = reader.readLine();
						for(Manager m : student.getSchool().getManagers()){
							if(m.getId().equals(id)) {
								System.out.print("Text the description of your request: ");
								String text = reader.readLine();
								student.makeRequest(new Request(text), m);
								break;
							}
						}
						break;
					case "2":
						for(Manager m : Database.getManagers()) {
							if(m.getType().equals(ManagerType.OR)) {
								System.out.println(m.getId() + " " + m.getFullName());
							}
						}
						System.out.print("Choose the id of OR manager you want to write");
						id = reader.readLine();
						for(Manager m : Database.getManagers()) {
							if(m.getId().equals(id)) {
								System.out.print("Text the desctiption of your request: ");
								String text = reader.readLine();
								student.makeRequest(new Request(text), m);
								break;
							}
						}
						break;
					case "3":
						for(TechSupportWorker t : Database.getTechSupportWorkers()) {
							System.out.println(t.getId() + " " + t.getFullName());
						}
						System.out.print("Choose the id of staff of the tech support you want to write: ");
						id = reader.readLine();
						for(TechSupportWorker t : Database.getTechSupportWorkers()) {
							if(t.getId().equals(id)) {
								System.out.print("Text the description of your request: ");
								String text = reader.readLine();
								student.makeRequest(new Request(text), t);
								break;
							}
						}
						break;
					}
				case "5":
    				int i = 1; 
    				for(News n : Database.getNews()) {
    					System.out.println(i + ". " + n);
    					i += 1;
    				}
    				System.out.println("""
    						1. Comment news.
    						0. Back. """);
    				option = reader.readLine();
    				switch(option) {
    					case "1":
    						System.out.print("Enter number of news: ");
    						int choice = Integer.parseInt(reader.readLine());
    						System.out.print("Enter comment: ");
    						String comment = reader.readLine();
    						student.writeComment(comment, Database.getNews().get(choice - 1));
    						System.out.println("You commented on the news.");
    					case "0":
    						break;
    				}
    				break;
				case "6":
					
					student.viewTranscript();
					break;
				case "7":
					student.viewCourses();
					System.out.print("Enter course id: ");
					id = reader.readLine();
//					Course c = student.getCourseById(id);
//					student.viewMark(c);
					break;
				case "8":
//					student.registerForCourse(null, null);
					break;
				case "9":
//					System.out.println(student.getTeachers());
					System.out.print("Teacher id: ");
					id = reader.readLine();
//					Teacher t = Database.getTeacherById(id);
					System.out.print("Rate the teacher on a 10-point scale: ");
					double mark = Double.parseDouble(reader.readLine());
//					student.rateTeachers(t, q, mark); - как передать Questionnaire ? 
					System.out.println("Thank you for your feedback!");
					break;
		}
	}
}
}
