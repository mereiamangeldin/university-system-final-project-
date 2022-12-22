package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;
import Interfaces.*;
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
	    		teacher.viewMark(Database.getCourseById(reader.readLine()));
	    	} else if(option.equals("6")) {
	    		Menu.sendMessage(teacher, reader);
	    	} else if(option.equals("7")) {
	    		System.out.println(teacher.getEmail());
	    	} else if(option.equals("8")) {
	    		Menu.makeRequest(teacher, reader);
	    	} else if(option.equals("9")) {
	    		Menu.viewNews(teacher, reader);
	    	} else if(option.equals("2")) {
	    		TeacherMenu.manageCourseFiles(teacher, reader);
	    	}
	    }
	}
	
	public static void putMark(Teacher teacher, BufferedReader reader) throws IOException {
		System.out.println(Database.getTeachersCourse(teacher));
		System.out.println("Enter course id: ");
		String id = reader.readLine();
		Course c = Database.getCourseById(id);
		System.out.println("Enter student id: ");
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
	
	public static void manageCourseFiles(Teacher teacher, BufferedReader reader) throws IOException {
		if(Database.getTeachersCourse(teacher) == null) {
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
