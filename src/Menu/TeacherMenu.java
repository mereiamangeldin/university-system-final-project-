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
	    String menuTeacher = "\nWelcome, Teacher: " + teacher.getFullName() + """
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
	    	else if(option.equals("2")) {
	    		System.out.println(Database.getStudents());
	    		System.out.println("Sort alphabetically - type 1.\nSort by gpa - type 2.\nBack - type 3.");
	    		option = reader.readLine();
	    		if(option.equals("3")) {
	    			continue;
	    		} else {
	    			teacher.viewStudentBy(Integer.parseInt(option));
	    		}
	    	} else if(option.equals("4")) {
//	    		teacher.putMark(null, option, 0, 0);
	    	} else if(option.equals("5")) {
//	    		teacher.viewMark(null);
	    	} else if(option.equals("6")) {
	    		Menu.sendMessage(teacher, reader);
	    	} else if(option.equals("7")) {
	    		System.out.println(teacher.getEmail());
	    	} else if(option.equals("8")) {
	    		System.out.println("Who do you want to contanct?"
	    				+ "\n1. Dean's office."
	    				+ "\n2. Office of the registration."
	    				+ "\n3. Tech support office."
	    				+ "\n0. Cancel.");
	    		option = reader.readLine();
	    		if(option.equals("1")) {
	    			System.out.print("Which department manager you want to write?");
	    			// 
	    			System.out.println("Choose the id of manager you want to write: ");
	    			String id = reader.readLine();
//	    			Manager m = Database.getManagerById(id);
	    			System.out.print("Text the desctiption of your request: ");
	    			String text = reader.readLine();
//	    			tsw.makeRequest(new Request(text), m);
	    		} else if(option.equals("2")) {
//	    			System.out.println(Database.getORManagers)
	    			System.out.print("Choose the id of manager you want to write: ");
	    			String id = reader.readLine();
//	    			Manager m = Database.getORManagerById(id);
	    			System.out.print("Text the desctiption of your request: ");
	    			String text = reader.readLine();
//	    			tsw.makeRequest(new Request(text), m);
	    		}
	    	} else if(option.equals("9")) {
	    		int i = 1;
	    		for(News n : Database.getNews()) {
	    			System.out.println(i + ". " + n);
	    			i += 1;
	    		}
	    		System.out.println("""
	    				1. Comment news.
	    				0. Back.""");
	    		option = reader.readLine();
	    		if(option.equals("0")) {
	    			break;
	    		}
	    		else if(option.equals("1")) {
	    			System.out.print("Enter number of news: ");
	    			int choice = Integer.parseInt(reader.readLine());
	    			System.out.println("Enter comment: ");
	    			String comment = reader.readLine();
	    			teacher.writeComment(comment, Database.getNews().get(choice - 1));
	    			System.out.println("You commented on the news.");
	    		}
	    	}
	    }
	}
}
