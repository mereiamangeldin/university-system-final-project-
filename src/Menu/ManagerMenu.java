package Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import Actors.*;
import Attributes.*;

public class ManagerMenu {
	public static void menu(SimpleUser user) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Manager manager = (Manager)user;
		String menuManager = "\nWelcome: " + manager.getFullName() + """
				\n1. Change password.
				2. View news.
				3. Manage news.
				4. Send message.
				5. Read message.
				6. Manage courses (view marks)
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
				Menu.viewNews(manager, reader);
			} else if(option.equals("3")) {
				ManagerMenu.manageNews(manager, reader);
			} else if(option.equals("4")) {
				Menu.sendMessage(manager, reader);
			} else if(option.equals("5")) {
				System.out.println(manager.getEmail());
			} else if(option.equals("6")) {
				ManagerMenu.manageCourses(manager, reader);
			} else if(option.equals("7")) {
				ManagerMenu.viewTeachers(manager, reader);
			} else if(option.equals("8")) {
				ManagerMenu.processRequests(manager, reader);
			}
		}
	}
	
	public static void manageNews(Manager manager, BufferedReader reader) throws IOException {
		int i = 1; 
		for(News n : Database.getNews()) {
			System.out.println(i + ". " + n);
			i += 1;
		}
		System.out.println("1. Add news.\n2. Delete news.\n3. Cancel.)");
		String option = reader.readLine();
		if(option.equals("1")) {
			manager.addNews();
		} else if(option.equals("2")) {
			System.out.println("Enter number of news: ");
			int choice = Integer.parseInt(reader.readLine());
			manager.removeNews(Database.getNews().get(choice - 1));
		} else if(option.equals("3")) {
			return;
		}
	}
	
	public static void manageCourses(Manager manager, BufferedReader reader) throws IOException {
		System.out.println(Database.getCourses());
		System.out.println("1. Add course for registration.\n2. Assign course to teacher.\n3. Create a course report.\n4. Back");
		String option = reader.readLine();
		if(option.equals("1")) {
			manager.addCoursesForRegistration();
		} else if(option.equals("2")) {
			System.out.println(Database.getCourses());
			System.out.print("Enter course id: ");
			String courseId = reader.readLine();
			System.out.println(Database.getTeachers());
			System.out.println("Enter teacher id: ");
			String teacherId = reader.readLine();
			if(manager.assignCourseToTeacher(courseId, teacherId)) {
				System.out.println("The course has been successfully assigned to a teacher");
			} else {
				System.out.println("Failed to assign course to teacher");
			}
		} else if(option.equals("3")) {
			System.out.println(Database.getCourses());
			System.out.print("Enter course id: ");
			manager.createReport(Database.getCourseById(reader.readLine()));
			
		} else if(option.equals("4")) {
			return;
		}
	}
	
	public static void viewTeachers(Manager manager, BufferedReader reader) throws IOException {
		System.out.println(Database.getTeachers());
		System.out.println("1. Sort alphabetically.\n2. Sort by rate.\n0. Back");
		String option = reader.readLine();
		if(option.equals("1")) {
			manager.viewTeachersAlphabetically();
		} else if(option.equals("2")) {
			manager.viewTeacherByRate();
		} else if(option.equals("0")) {
			return;
		}
	}
	
	public static void processRequests(Manager manager, BufferedReader reader) throws NumberFormatException, IOException {
		int i = 1;
		for(Request r : manager.getRequests()) {
			System.out.println(i + ". " + r.getUserID() + " ," + r.getDescription());
			i += 1;
		}
		System.out.println("1. Process request.\n2. Back");
		String option = reader.readLine();
		if(option.equals("1")) {
			System.out.print("Enter number of request: ");
			manager.processRequests(Integer.parseInt(reader.readLine()) - 1);
		} else if(option.equals("0")) {
			return;
		}
	}
}