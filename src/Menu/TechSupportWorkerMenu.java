package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;
import Interfaces.*;

public class TechSupportWorkerMenu {
	public static void menu(User user) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		TechSupportWorker tsw = (TechSupportWorker)user;
		String menuTechSupportWorker = "\nWelcome: " + tsw.getFullName() + """
				\n1. Change password.
				2. View students.
				3. Send message.
				4. Read messages.
				5. View news.
				6. View requests.
				7. Make request.
				8. Save changes.
				0. Logout.
				""";
		while(tsw.getLogged()) {
			System.out.println(menuTechSupportWorker);
			String option = reader.readLine();
			if(option.equals("0")) {
				tsw.logout();
				System.out.println("You logged out");
				Database.serializeAll();
				break;
			} else if(option.equals("1")) {
				Menu.changePassword(tsw, reader);
			} else if(option.equals("2")) {
				Menu.viewStudent(tsw, reader);
			} else if(option.equals("3")) {
				Menu.sendMessage(tsw, reader);
			} else if(option.equals("4")) {
				Menu.readMessages(tsw, reader);
			} else if(option.equals("5")) {
				Menu.viewNews(tsw, reader);
			} else if(option.equals("6")) {
				TechSupportWorkerMenu.processRequest(tsw, reader);
			} else if(option.equals("7")) {
				Menu.makeRequest(tsw, reader);
			} else if(option.equals("8")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
			}
		}
	}
	
	public static void processRequest(TechSupportWorker t, BufferedReader reader) throws IOException {
		String option;
		int i = 1;
		for(Request r : t.getRequests()) {
			System.out.println(i + ". " + "Request from: " + r.getUserID() + ", description: " + r.getDescription() + ", date: " + r.getDateOfRequest());
		}
		while(true) {
			System.out.println("Choose option:\nProcess request - 1\nBack - 2.");
			option = reader.readLine();
			if(option.equals("1")) {
				System.out.print("Choose the request. Enter number of request (by ordering order): ");
				int choice = Integer.parseInt(reader.readLine());
				System.out.println("Request: " + t.getRequests().get(choice - 1).getDescription() + "\nAccept request - 1.\nReject request - 2.\nBack - 0.");
				option = reader.readLine();
				if(option.equals("0")) {
					break;
				} else if(option.equals("1") || option.equals("2")) {
					t.processRequests(choice);
				}
				} else if(option.equals("2")) {
					break;
				}
		}
	}
}