package Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

public class LibrarianMenu {
	public static void menu(User user) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Librarian librarian = (Librarian)user;
        String menuLibrarian = "\nWelcome: " + librarian.getFullName() + """
        		\n1. Change password;
        		2. View students.
        		3. View news.
        		4. View orders.
        		5. Make request.
        		6. Send messages.
        		7. Read messages.
        		8. Save changes.
        		0. Logout.
        		""";
        while(librarian.getLogged()) {
        	System.out.println(menuLibrarian);
        	String option = reader.readLine();
        	if(option.equals("0")) {
        		librarian.logout();
        		System.out.println("You logged out.");
				Database.serializeAll();
				break;
        	}
        	else if(option.equals("1")) {
        		Menu.changePassword(librarian, reader);
        	}
        	else if(option.equals("2")) {
        		Menu.viewStudent(librarian, reader);
        	}
        	else if(option.equals("3")) {
        		Menu.viewNews(librarian, reader);
        	}
        	else if(option.equals("4")) {
        		for(Order o : librarian.getTakers()) {
        			System.out.println(o);
        		}
        	}
        	else if(option.equals("5")) {
        		Menu.makeRequest(librarian, reader);
        	}
        	else if(option.equals("6")) {
        		Menu.sendMessage(librarian, reader);
        	}
        	else if(option.equals("7")) {
        		Menu.readMessages(librarian, reader);
        	} 
        	else if(option.equals("8")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
        	}
        }	
	}
}
