package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import Actors.*;
import Attributes.*;
import Interfaces.*;
import javafx.util.Pair;
import Enums.*;

public class TeacherResearcherMenu {
	public static void menu(User user) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    WithTeacherResearcher teacher = (WithTeacherResearcher)user;
	    String menuTeacher = "\nWelcome, " + teacher.getFullName() + "!" + """
	    		\n1. Change password. 
	    		2. Manage course files.
	    		3. View students. 
	    		4. Put mark.
	    		5. View marks;
	    		6. Send message.
	    		7. Read messages.
	    		8. Make request.
	    		9. View news.
	    		10. Researcher page.
	    		11. Save changes.
	    		0. Logout.
	    		"""; 
	    while(teacher.getLogged()) {
	    	System.out.println(menuTeacher);
	    	String option = reader.readLine();
	    	if(option.equals("0")) {
	    		teacher.logout();
	    		System.out.println("You logget out.");
				Database.serializeAll();
				break;
	    	}
	    	else if(option.equals("1")) {
	    		Menu.changePassword(teacher, reader);
	    	}
	    	else if(option.equals("3")) {
	    		TeacherResearcherMenu.viewStudent(teacher, reader);
	    	} 
	    	else if(option.equals("4")) {
	    		TeacherResearcherMenu.putMark(teacher, reader);
	    	} else if(option.equals("5")) {
	    		System.out.println(Database.getTeachersCourse(teacher.getDecoratedTeacher()));
	    		System.out.print("Enter course id: ");
	    		teacher.viewMark(Database.getCourseById(reader.readLine()));
	    	} else if(option.equals("6")) {
	    		TeacherResearcherMenu.sendMessage(teacher, reader);
	    	} else if(option.equals("7")) {
	    		TeacherResearcherMenu.readMessages(teacher, reader);
	    	} else if(option.equals("8")) {
	    		TeacherResearcherMenu.makeRequest(teacher, reader);
	    	} else if(option.equals("9")) {
	    		TeacherResearcherMenu.viewNews(teacher, reader);
	    	} else if(option.equals("2")) {
	    		TeacherResearcherMenu.manageCourseFiles(teacher, reader);
	    	} else if(option.equals("10")) {
	    		TeacherResearcherMenu.researcherPage(teacher, reader);
	    	} else if(option.equals("11")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
	    	}
	    }
	}
	
	public static void sendMessage(WithTeacherResearcher wt, BufferedReader reader) throws IOException {
		System.out.print("Enter text of the message: ");
		String text = reader.readLine();
		Message m = new Message(new Date(), text);
		for(Employee ee : Database.getEmployees()) {
			System.out.println(String.format(("Id: %s, name: %s"), ee.getId(), ee.getFullName()));
		}
		System.out.print("\nEnter employee id you want to message to: ");
		String id = reader.readLine();
		Employee employee = Database.getEmployeeById(id);
		if(employee != null) {
			wt.sendMessage(m, employee);
			System.out.println("Message sent.");
		} 
		else {
			System.out.println("Employee not found.");
		}
	}
	
	public static void viewNews(WithTeacherResearcher wt, BufferedReader reader) throws NumberFormatException, IOException {
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
				wt.writeComment(comment, Database.getNews().get(newsOrder - 1));
				System.out.println("You commented on the news.");
			} else if(option.equals("0")) {
				return;
			}
		}
	}
	
	public static void makeRequest(WithTeacherResearcher wt, BufferedReader reader) throws IOException {
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
				TechSupportWorker t = Database.getTechSupportWorkerById(id);
				if(t != null) {
					System.out.println(wt.makeRequest(new Request(wt.getId(), RequestType.SimpleRequest, text), Database.getTechSupportWorkerById(id)));
				} else {
					System.out.println("Tech support worker does not found.");
				}
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
				
				System.out.print("Enter the id of the manager you want to write: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				Manager m = Database.getManagerById(id);
				System.out.println(text.length());
				if(m != null) {
					System.out.println(wt.makeRequest(new Request(wt.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
				} else {
					System.out.println("Manager does not found.");
				}
			}
			if(option.equals("3")) {
				for(Manager m : Database.getORManagers()) {
					System.out.println(m);
				}
				System.out.print("Enter the id of the manager of office of the register: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				Manager m = Database.getManagerById(id);
				if(m != null) {
					System.out.println(wt.makeRequest(new Request(wt.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
				} else {
					System.out.println("Manager does not found.");
				}			
			}		
		}	
	}
	
	public static void viewStudent(WithTeacherResearcher wt, BufferedReader reader) throws IOException {
		for(Student s : Database.getStudents()) {
			System.out.println(String.format("Id: %s, %s, year of study: %s, school: %s ", s.getId(), s.getFullName(), s.getYearOfStudy(), s.getSchool().getName()));
		}
		while(true) {
			System.out.println("1. Sort alphabetically.\n2. Sort by gpa.\n3. Back");
			String option = reader.readLine();
			if(option.equals("3")) {
				break;
			} else if(option.equals("1") || option.equals("2")) {
				for(Student s : wt.viewStudentBy(Integer.parseInt(option))) {
					System.out.println(String.format("Id: %s, %s, year of study: %s, school: %s ", s.getId(), s.getFullName(), s.getYearOfStudy(), s.getSchool().getName()));
				}
			}
		}
	}
	
	public static void readMessages(WithTeacherResearcher wt, BufferedReader reader) {
		if(wt.getEmail().size() == 0) {
			System.out.println("You don't have messages.");
		} else {
			for(Employee e : wt.getEmail().keySet()) {
				Message m = wt.getEmail().get(e);
				System.out.println("Message from: " + e.getFullName() + ", text: " + m.getText() + ", date: " + m.getDate());
			}
		}
	}
	
	public static void researcherPage(User user, BufferedReader reader) throws IOException {
		String option = "";
		if(!(user instanceof WithTeacherResearcher)) {
			Teacher s = (Teacher)user;
			System.out.println("1. Become researcher.\n2. Cancel");
			option = reader.readLine();
			if(option.equals("1")) {
				WithTeacherResearcher wt = new WithTeacherResearcher(s);
				Database.getUsers().remove(wt);
				Database.getUsers().add(wt);
			} else if(option.equals("2")) {
				return;
			}
		} else {
			WithTeacherResearcher wt = (WithTeacherResearcher)user;
			System.out.println("1. Show h-index.\n2. Add research paper.\n3. Add research project");
			option = reader.readLine();
			if(option.equals("1")) {
				System.out.println("Your h-index: " + wt.getHindex());
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
					wt.addResearchPaper(new ResearchPaper(title, description, d));
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
		    			wt.addResearchProject(rp);
		    		}
		    	}
			}
		}
	}
	
	public static void putMark(WithTeacherResearcher teacher, BufferedReader reader) throws IOException {
		for(Course c : Database.getTeachersCourse(teacher.getDecoratedTeacher())) {
			System.out.println(c);
		}
		System.out.print("Enter course id: ");
		String id = reader.readLine();
		Course c = Database.getCourseById(id);
		for(Student s : Database.getStudents()) { 
	    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
	    		if(marks.getKey().getKey().equals(c) && marks.getKey().getValue().equals(teacher.getDecoratedTeacher())) {
	    			System.out.println(s);
	    		}
	        }
		}
		System.out.print("Enter student id: ");
		id = reader.readLine();
		Student s = Database.getStudentById(id);
		System.out.println("1. Put first attestation.\n2. Put second attestation.\n3. Put score for final.\n0.Cancel");
		String option = reader.readLine();
		if(option.equals("0")) {
			return;
		} else {
			System.out.print("Enter mark: ");
			if(option.equals("1")) {
				teacher.putMark(c, s, Integer.parseInt(option), Double.parseDouble(reader.readLine()));
			} else if(option.equals("2")) {
				teacher.putMark(c, s, Integer.parseInt(option), Double.parseDouble(reader.readLine()));
			} else if(option.equals("3")) {
				teacher.putMark(c, s, Integer.parseInt(option), 0);
		}			
		}
	}
	
	public static void manageCourseFiles(WithTeacherResearcher teacher, BufferedReader reader) throws IOException {
		if(Database.getTeachersCourse(teacher.getDecoratedTeacher()).size() == 0) {
			System.out.println("You don't have courses.");
			return;
		}
		for(Course c : Database.getTeachersCourse(teacher.getDecoratedTeacher())) {
			System.out.println(String.format(("Course id: %s, name %s, school : %s, number of credits: %s"), c.getId(), c.getName(), c.getSchool(), c.getNumberOfCredits()));
		}
		System.out.println("\n1. Add file to course.\n2. Delete file from course.\n0.Return to main menu.");
		String option = reader.readLine();
		if(option.equals("1")) {
			System.out.print("Enter course id: ");
			String id = reader.readLine();
			Course c = Database.getCourseById(id);
			if(c != null) {
    			System.out.print("Enter file name: ");
    			String fileName = reader.readLine();
    			System.out.print("Enter type of file:\n1. PDF\n2. DOCX\n3. JPEG\n4.PPTX\n0. Cancel");
    			option = reader.readLine();
    			TypeOfFile type = null;
    			switch(option) {
    				case "1":
    					type = TypeOfFile.PDF;
    				case "2":
    					type = TypeOfFile.DOCX;
    				case "3":
    					type = TypeOfFile.JPEG;
    				case "4":
    					type = TypeOfFile.PPTX;
    				case "0":
    					break;
    			}
    			System.out.print("Enter content of file: ");
    			String content = reader.readLine();
    			File file = new File(fileName, type, content);
    			System.out.println(teacher.addFileToCouse(c, file));
			} else {
				System.out.println("Course not found");
			}
		} else if(option.equals("2")) {
			System.out.print("Enter course id: ");
			String id = reader.readLine();
			Course c = Database.getCourseById(id);
			if(c != null) {
				System.out.println(c.getFiles());
				System.out.print("Enter id of the file: ");
				int idd = Integer.parseInt(reader.readLine());
				File f = c.getCourseFile(idd);
				if(f != null) {
					System.out.println(teacher.deleteFileFromCourse(c, f));
				}
			}
		} else if(option.equals("0")) {
			return;
		}
	}
}
