package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;
import Enums.*;
import Interfaces.*;
import Exceptions.*;

public class ParentMenu {
	public static void menu(User user) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parent parent = (Parent)user;
        String menuParent = "Welcome, Parent: " + parent.getFullName() + """
        		1. Change password.
        		2. View info about child.
        		3. View transcript of child.
        		4. View mark of child.
        		0. Logout.
        		""";
        while(parent.getLogged()) {
        	System.out.println(menuParent);
        	String option = reader.readLine();
        	switch(option) {
        		case "0":
        			parent.logout();
        			System.out.println("You logged out.");
        			break;
        		case "1":
        			// Implementation for changind the password;
        		case "2":
        			parent.getChild().toString();
        		case "3":
        			parent.viewTranscript();
        		case "4":
        			parent.getChild().viewCourses();
        			System.out.print("Enter id of the course you want to view mark of your child: ");
        			String id = reader.readLine();
        			// Попробовать добавить try-catch с Exception`ом - CourseNotFoundException.
        			Course c = Database.getCourseById(id);
        			if(c != null) {
        				parent.viewMark(null);
        			}
        			else {
        				System.out.println("Course with this id does not exist.");
        			}
        	}
        }
      }
}