package Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import Actors.*;
import Attributes.*;

public class ManagerMenu {
	public static void menu(User user) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Manager manager = (Manager)user;
		String menuManager = "\nWelcome, Manager: " + manager.getFullName() + """
				\n1. Change password.
				2. View news.
				3. Manage news.
				4. Send message.
				5. Read message.
				6. View courses.
				7. View teachers.
				8. View requests.
				9. Make request.
				0. Logout.""";
		while(manager.getLogged()) {
			System.out.println(menuManager);
			String option = reader.readLine();
			if(option.equals("0")) {
				manager.logout();
				System.out.println("You logged out");
				break;
			} else if(option.equals("1")) {
				Menu.changePassword(manager, reader);
			} else if(option.equals("2")) {
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
						manager.writeComment(comment, Database.getNews().get(choice - 1));
						System.out.println("You commented on the news.");
					case "0":
						break;
			}
			} else if(option.equals("3")) {
				int i = 1; 
				for(News n : Database.getNews()) {
					System.out.println(i + ". " + n);
					i += 1;
				}
				System.out.println("1. Add news.\n2. Delete news.\n3. Cancel.)");
				option = reader.readLine();
				if(option.equals("1")) {
					manager.addNews();
				} else if(option.equals("2")) {
					System.out.println("Enter number of news: ");
					int choice = Integer.parseInt(reader.readLine());
					manager.removeNews(Database.getNews().get(choice - 1));
				} else if(option.equals("3")) {
					continue;
				}
			}
			else if(option.equals("4")) {
				Menu.sendMessage(manager, reader);
			} else if(option.equals("5")) {
				System.out.println(manager.getEmail());
			} else if(option.equals("6")) {
//				System.out.println(Database.getCourses());
				System.out.println("1. Assign course to teacher.\2. View marks.\n3. Add course for registration.");
				option = reader.readLine();
//				if(option.equals("1")) {
//					manager.assignCourseToTeacher();
//				} else if(option.equals("2")) {
//					manager.viewMark(null);
//				} else if(option.equals("3")){
//					manager.addCoursesForRegistration();
//				}	
			} else if(option.equals("7")) {
				System.out.println(Database.getTeachers());
				System.out.println("Sort alphabetically - type 1.\nSort by rate - type 2.\nBack - type 3.");
				option = reader.readLine();
				if(option.equals("3")) {
					continue;
				} else if(option.equals("2") || option.equals("1")) {
//					manager.viewTeachersBy(Integer.parseInt(option));
				}
			} else if(option.equals("8")) {
				System.out.println(manager.getRequests());
				// Также пронумеровать запросы и одобрять/отклонять
			} else if(option.equals("9")) {
//				manager.makeRequest(null, manager)
				// Прописать логику 
				
				// Если в тех центр
				System.out.print("Choose the id of tech support worker you want to write: ");
				String id = reader.readLine();
//				TechSupportWorker tsw = Database.getTechSupportWorkerById(id);
				System.out.print("Description of the request: ");
				String text = reader.readLine();
//				manager.makeRequest(new Request(text), text);
			}
		}
	}
}
