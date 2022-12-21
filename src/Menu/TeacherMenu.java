package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;

public class TeacherMenu {
	public static void menu(User user) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    Teacher teacher = (Teacher)user;
	    String menuTeacher = "Welcome, : " + teacher.getFullName() + """
	    		\n1. Change password.
	    		2. Manage course and files.
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
	    		continue;
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
}
