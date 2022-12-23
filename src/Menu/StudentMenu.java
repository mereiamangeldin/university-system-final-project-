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
	public static void menu(User user) throws Exception {
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
				10. Researcher page.
				11. Save changes.
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
				if(student.getCourses().size() == 0) {
					System.out.println("You don't have courses");
				} else {
					for(Course c : student.getCourses()) {
						System.out.println(c);
					}
				}
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
			else if(option.equals("10")) {
				StudentMenu.researchPage(student, reader);
			}
			else if(option.equals("11")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
			}
		}
	}
	
	public static void researchPage(User user, BufferedReader reader) throws IOException {
		String option = "";
		if(!(user instanceof WithStudentResearcher)) {
			Student s = (Student)user;
			System.out.println("1. Become researcher.\n2. Cancel.");
			option = reader.readLine();
			if(option.equals("1")) {
				WithStudentResearcher ws = new WithStudentResearcher(s);
				Database.getUsers().remove(s);
				Database.getUsers().add(ws);
				System.out.println("YOU BECAME RESEARCHER!");
			} else if(option.equals("2")) {
				return;
			}
		} else {
			WithStudentResearcher ws = (WithStudentResearcher)user;
			System.out.println("1. Show h-index.\n2. Add research paper.\n3. Add research project");
			option = reader.readLine();
			if(option.equals("1")) {
				System.out.println("Your h-index: " + ws.getHindex());
			} else {
				String title, description;
				System.out.print("Enter research title: ");
				title = reader.readLine();
				System.out.print("Text description of research: ");
				description = reader.readLine();
				System.out.print("Enter date of publishing in format yyyy/MM/dd: ");
		    	String date = reader.readLine();
		    	Date d;
		    	try {
		        	d = new SimpleDateFormat("yyyy/MM/dd").parse(date);
		    	} catch(ParseException p) {
		    		System.out.println("Incorrect date. Pleasy retry again.");
		    		return;
		    	}
		    	if(option.equals("2")) {
					ws.addResearchPaper(new ResearchPaper(title, description, d));
		    	} else if(option.equals("3")) {
		    		ResearchProject rp = new ResearchProject(title, description, d);
		    		System.out.print("Did you citate someone in your project? If yes, put 1, otherwise 0: ");
		    		option = reader.readLine();
		    		if(option.equals("1")) {
		    			for(User u : Database.getUsers()) {
		    				if(u instanceof WithStudentResearcher) {
		    					System.out.println(u.getFullName() + ", ID: " + ((WithStudentResearcher)u).getId());
		    				} else if(u instanceof WithTeacherResearcher) {
		    					System.out.println(u.getFullName() + ", ID: " + ((WithTeacherResearcher)u).getId());
		    				}
		    			}
		    			
		    			System.out.print("Enter ID of researcher which project you citated: ");
		    			String id = reader.readLine();
		    			int i = 1;
		    			for(User u : Database.getUsers()) {
		    				if(u instanceof WithStudentResearcher && ((WithStudentResearcher)u).getId().equals(id)) {
		    					WithStudentResearcher _ws = (WithStudentResearcher)u;
		    					for(ResearchProject p : _ws.getResearchProjects()) {
		    						System.out.println(i + ". " + p);
		    						i++;
		    					}
			    				System.out.print("Number of project: ");
			    				int numberOfProject = Integer.parseInt(reader.readLine());
			    				if(_ws.getResearchProjects().size() <= numberOfProject) {
			    					rp.getCitations().add(_ws.getResearchProjects().get(numberOfProject - 1));
			    				} else {
			    					System.out.println("Number is out of range");
			    				}
		    					break;
		    				} 
		    				if(u instanceof WithTeacherResearcher && ((WithTeacherResearcher)u).getId().equals(id)) {
		    					WithTeacherResearcher _wt = (WithTeacherResearcher)u;
		    					for(ResearchProject p : _wt.getResearchProjects()) {
		    						System.out.println(i + ". " + p);
		    						i++;
		    					}
			    				System.out.print("Number of project: ");
			    				int numberOfProject = Integer.parseInt(reader.readLine());
			    				if(_wt.getResearchProjects().size() <= numberOfProject) {
			    					rp.getCitations().add(_wt.getResearchProjects().get(numberOfProject - 1));
			    				} else {
			    					System.out.println("Number is out of range");
			    				}
		    					break;
		    				}		
		    			}
		    			ws.addResearchProject(rp);
		    		}
		    	}
			}
		}
	}
	
	public static void makeRequest(Student student, BufferedReader reader) throws IOException {
		String requestMenu = "Who do you want to contact?\n1. Technical Support Center.\n2. Dean's office.\n3. Office of the register.\n0. Back.";
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
				System.out.println(student.makeRequest(new Request(student.getId(), RequestType.SimpleRequest, text), Database.getTechSupportWorkerById(id)));
			}
			if(option.equals("2")) {
				i = 1;
				for(School s : Database.getSchools()) {
					System.out.println(i + ". " + s.getName());
					i += 1;
				}
				System.out.print("What school do you want to apply to? (enter number): ");
				for(Manager m : Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getManagers()) {
					System.out.println(m);
				}
				System.out.print("Enter the id of the manager of: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				System.out.println(student.makeRequest(new Request(student.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
			}
			if(option.equals("3")) {
				for(Manager m : Database.getORManagers()) {
					System.out.println(m);
				}
				System.out.print("Enter the id of the manager of office of the register: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				System.out.println(student.makeRequest(new Request(student.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
			}		
		}
	}
		
	public static void viewNews(Student student, BufferedReader reader) throws NumberFormatException, IOException {
		int newsOrder = 1; 
		System.out.println("NEWS");
		for(News n : Database.getNews()) {
			System.out.println(newsOrder + ". " + n.getTitle() + "\n" + n.getText() + "\nComments: ");
			for(String s : n.getComments()) {
				System.out.println(s);
			}
			newsOrder += 1;
		}
		String newsMenu = ("""
				\n1. Comment news.
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
				System.out.println("You commented on the news.");
			} else if(option.equals("0")) {
				return;
			}
		}
	}
		
		public static void bookOrdering(Student student, BufferedReader reader) throws Exception {
			for(Book b : Database.getBooks()) {
				System.out.println(b);
			}
			System.out.print("Id of the book you want to order: ");
			int id = Integer.parseInt(reader.readLine());
			Book b = Database.getBookById(id);
			for(Librarian l : Database.getLibrarians()) {
				System.out.println(l);
			}
			System.out.print("Choose the library staff (Enter ID): ");
			String i = reader.readLine();
			Librarian l = Database.getLibrarianById(i);
			if(b != null) {
				if(l != null) {
					System.out.println(student.makeBookOrder(l, new Order(student, b)));
				} else {
					System.out.println("Librarian does not found");
				}
			} else {
				System.out.println("Book is not found");
			}
		}
		
		public static void viewMark(Student student, BufferedReader reader) throws IOException {
			for(Course c : student.getCourses()) {
				System.out.println(c);
			}
			System.out.print("Enter id of the course: ");
			String id = reader.readLine();
			Course c = Database.getCourseById(id);
			if(c != null) {
				c = Database.getCourseById(id);
				System.out.println(student.viewMark(c));
			} else {
				System.out.println("Course not found");
			}
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
			for(Teacher t : c.getTeachers()) {
				System.out.println(t);
			}
			System.out.print("Enter teacher id: ");
			id = reader.readLine();
			Teacher t = Database.getTeacherById(id);
			for(Manager m : Database.getORManagers()) {
				System.out.println(m);
			}
			System.out.print("Enter manager id: ");
			id = reader.readLine();
			Manager m = Database.getManagerById(id);
			if(c != null) {
				if(t != null) {
					if(m != null) {
						student.registerForCourse(c, t, m);
						System.out.println("Your request for registration has been sended");
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
		
		public static void rateTeacher(Student student, BufferedReader reader) throws Exception {
			System.out.println(student.getTeachers());
			System.out.print("Teacher id: ");
			String id = reader.readLine();
			Teacher t = Database.getTeacherById(id);
			System.out.print("Rate the teacher on a 10-point scale: ");
			double mark = Double.parseDouble(reader.readLine());
			if(mark < 0) {
				throw new Exception("Not valid rate");
			}
			student.rateTeacher(t, mark);
			System.out.println("Thank you for your feedback!");
		}
}