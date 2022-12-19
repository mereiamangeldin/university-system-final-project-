package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Actors.*;
import Attributes.*;

public class TechSupportWorkerMenu {
	public static void menu(User user) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		TechSupportWorker tsw = (TechSupportWorker)user;
		String menuTechSupportWorker = "\nWelcome, Tech Support Worker: " + tsw.getFullName() + """
				\n1. Change password.
				2. View students.
				3. Send message.
				4. Read messages.
				5. View news.
				6. View requests.
				7. Make request.
				0. Logout.
				""";
		while(tsw.getLogged()) {
			System.out.println(menuTechSupportWorker);
			String option = reader.readLine();
			if(option.equals("0")) {
				tsw.logout();
				System.out.println("You loggout out");
				continue;
			} else if(option.equals("1")) {
				System.out.println(tsw.viewStudent());
				System.out.println("Sort alphabetically - type 1.\nSort by gpa - type 2.\nBack - 3.");
				option = reader.readLine();
				if(option.equals("3")) {
					continue;
				} else if(option.equals("1") || option.equals("2")) {
					tsw.viewStudentBy(Integer.parseInt(option));
				}
			} else if(option.equals("3")) {
				Menu.sendMessage(tsw, reader);
			} else if(option.equals("4")) {
				System.out.println(tsw.getEmail());
			} else if(option.equals("5")) {
				int i = 1;
				for(News n : Database.getNews()) {
					System.out.println(i + ". " + n);
					i += 1;
				}
				System.out.println("""
						1. Comment news.
						0. Back.""");
				option = reader.readLine();
				if(option.equals("1")) {
					System.out.print("Enter number of news: ");
					int choice = Integer.parseInt(reader.readLine());
					System.out.print("Enter comment: ");
					String comment = reader.readLine();
					tsw.writeComment(comment , Database.getNews().get(choice - 1));
					System.out.println("You commmented on the news.");
				}
			} else if(option.equals("6")) {
				System.out.println(tsw.getRequests());
			} else if(option.equals("7")) {
				System.out.println(Database.getManagers());
				System.out.print("Enter id of the manager you want to send a reqest: ");
				String id = reader.readLine();
				for(Manager m : Database.getManagers()) {
					if(m.getId().equals(id)) {
						System.out.print("Enter text of your request: ");
						String text = reader.readLine();
						tsw.makeRequest(new Request(text), m);
						break;
					}
				}
			}
		}
	}
}
