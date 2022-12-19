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
        String menuLibrarian = "\nWelcome, Librarian: " + librarian.getFullName() + """
        		\n. 1. Change password;
        		2. View students.
        		3. View news.
        		4. View orders.
        		5. Make request.
        		0. Logout.
        		""";
        while(librarian.getLogged()) {
        	System.out.println(menuLibrarian);
        	String option = reader.readLine();
        	switch(option) {
        		case "0": 
        			librarian.logout();
        			System.out.println("You logged out.");
        			break;
        		case "1":
        			Menu.changePassword(librarian, reader);
        			break;
        		case "2":
        			librarian.viewStudent();
        			break;
        		case "3":
    				int i = 1; 
    				for(News n : Database.getNews()) {
    					System.out.println(i + ". " + n);
    					i += 1;
    				}
    				System.out.println("""
    						1. Comment news.
    						0. Back. """);
    				option = reader.readLine();
    				switch(option) {
    					case "1":
    						System.out.print("Enter number of news: ");
    						int choice = Integer.parseInt(reader.readLine());
    						System.out.print("Enter comment: ");
    						String comment = reader.readLine();
    						librarian.writeComment(comment, Database.getNews().get(choice - 1));
    						System.out.println("You commented on the news.");
    					case "0":
    						break;
    				}
        	case "4":
        		System.out.println(librarian.getTakers());
        		break;
        	case "5":
        		System.out.print("Who do you want to contact: "
        				+ "1. Tech Support center."
        				+ "2. Dean's office."
        				+ "3. Office of the register.");
        		option = reader.readLine();
        		if(option.equals("1")) {
        			
        		} else if(option.equals("2")) {
        			
        		} else if(option.equals("3")) {
//        			System.out.println(Database.getORManagers());
        			System.out.print("Choose manager id you want to write: ");
        			String id = reader.readLine();
//        			Manager m = Database.getORManagerById(id);
        			System.out.print("Description of the request: ");
        			String text = reader.readLine();
//        			librarian.makeRequest(new Request(text), m);
        		}
        		
        }
	}
}
}
