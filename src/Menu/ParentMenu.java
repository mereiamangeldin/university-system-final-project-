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
        	if(option.equals("0")) {
        		parent.logout();
        		System.out.println("You logged out.");
        		break;
        	} else if(option.equals("1")){
        		Menu.changePassword(parent, reader);
        	} else if(option.equals("2")) {
        		System.out.println(parent.getChild());
        	} else if(option.equals("3")) {
        		System.out.println(parent.getChild().getTranscript());
        	} else if(option.equals("4")) {
        		System.out.println(parent.getChild().getCourses());
        		System.out.print("Enter course id: ");
        		String id = reader.readLine();
        		Course c = Database.getCourseById(id);
        		parent.viewMark(c);
        	}
        }
      }
}