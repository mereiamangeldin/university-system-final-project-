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

public class TeacherMenu {
	public static void menu(User user) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    Teacher teacher = (Teacher)user;
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
	    		Menu.viewStudent(teacher, reader);
	    	} 
	    	else if(option.equals("4")) {
	    		TeacherMenu.putMark(teacher, reader);
	    	} else if(option.equals("5")) {
	    		System.out.println(Database.getTeachersCourse(teacher));
	    		System.out.print("Enter course id: ");
	    		Course c = Database.getCourseById(reader.readLine());
	    		if(c != null) {
		    		System.out.println(teacher.viewMark(Database.getCourseById(reader.readLine())));
	    		}  else {
	    			System.out.println("Course not found!");
	    		}
	    	} else if(option.equals("6")) {
	    		Menu.sendMessage(teacher, reader);
	    	} else if(option.equals("7")) {
	    		Menu.readMessages(teacher, reader);
	    	} else if(option.equals("8")) {
	    		Menu.makeRequest(teacher, reader);
	    	} else if(option.equals("9")) {
	    		Menu.viewNews(teacher, reader);
	    	} else if(option.equals("2")) {
	    		TeacherMenu.manageCourseFiles(teacher, reader);
	    	} else if(option.equals("10")) {
	    		TeacherMenu.researcherPage(teacher, reader);
	    	} else if(option.equals("11")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
	    	}
	    }
	}
	
	public static void researcherPage(User user, BufferedReader reader) throws IOException {
		String option = "";
		if(!(user instanceof WithTeacherResearcher)) {
			Teacher t = (Teacher)user;
			System.out.println("1. Become researcher.\n2. Cancel");
			option = reader.readLine();
			if(option.equals("1")) {
				WithTeacherResearcher wt = new WithTeacherResearcher(t);
				Database.getUsers().remove(t);
				Database.getUsers().add(wt);
				System.out.println("YOU ARE RESEARCHER NOW!");
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
	
	public static void putMark(Teacher teacher, BufferedReader reader) throws IOException {
		for(Course c : Database.getTeachersCourse(teacher)) {
			System.out.println(c);
		}
		System.out.print("Enter course id: ");
		String id = reader.readLine();
		Course c = Database.getCourseById(id);
		Pair<Course, Teacher> p = new Pair<Course, Teacher>(c, teacher);
		for(Student s : Database.getStudents()) { 
	    	for(HashMap.Entry<Pair<Course, Teacher>, Mark> marks : s.getTranscript().entrySet()) {
	    		if(marks.getKey().getKey().equals(c) && marks.getKey().getValue().equals(teacher)) {
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
				teacher.putMark(c, s, Integer.parseInt(option), Double.parseDouble(reader.readLine()));
			}			
		}
	}
	
	public static void manageCourseFiles(Teacher teacher, BufferedReader reader) throws IOException {
		if(Database.getTeachersCourse(teacher).size() == 0) {
			System.out.println("You don't have courses.");
			return;
		}
		for(Course c : Database.getTeachersCourse(teacher)) {
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
