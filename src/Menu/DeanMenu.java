package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Interfaces.*;
import Attributes.Database;

public class DeanMenu {
	public static void menu(User user) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Dean dean = (Dean)user;
        String menuDean = "\nWelcome: " + dean.getFullName() + """
        		\n1. Change password.
        		2. View students.
        		3. Send message.
        		4. Read messages.
        		5. View news.
        		6. Make requests.
        		7. Save changes.
        		0. Logout.
        		""";
        while(dean.getLogged()) {
        	System.out.println(menuDean);
        	String option = reader.readLine();
        	if(option.equals("0")) {
        		dean.logout();
        		System.out.println("You logged out.");
        		Database.serializeAll();
        		break;
        	} else if(option.equals("1")) {
        		Menu.changePassword(dean, reader);
        	} else if(option.equals("2")) {
        		Menu.viewStudent(dean, reader);
        	} else if(option.equals("3")) {
        		Menu.sendMessage(dean, reader);
        	} else if(option.equals("4")) {
        		Menu.readMessages(dean, reader);
        	} else if(option.equals("5")) {
        		Menu.viewNews(dean, reader);
        	} else if(option.equals("6")) {
        		Menu.makeRequest(dean, reader);
        	} else if(option.equals("7")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
        	}
        }
	}
}