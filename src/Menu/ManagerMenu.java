package Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import Actors.*;
import Attributes.*;
import Interfaces.*;
import Enums.CourseType;
import Enums.ScienceDegree;
import javafx.util.Pair;


public class ManagerMenu {
	public static void menu(User user) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Manager manager = (Manager)user;
		String menuManager = "\nWelcome: " + manager.getFullName() + """
				\n1. Change password.
				2. View news.
				3. Manage news.
				4. Send message.
				5. Read message.
				6. Manage courses
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
				Database.serializeAll();
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
			} else if(option.equals("9")) {
				Menu.makeRequest(manager, reader);
			}
		}
	}
	
	public static void manageNews(Manager manager, BufferedReader reader) throws IOException {
		int i = 1; 
		for(News n : Database.getNews()) {
			System.out.println(i + ". " + n);
			i += 1;
		}
		while(true) {
			System.out.println("\n1. Add news.\n2. Delete news.\n3. Cancel.");
			String option = reader.readLine();
			if(option.equals("1")) {
				System.out.print("Write title: ");
				String title = reader.readLine();
				System.out.print("Write text of the news:");
				String text = reader.readLine();
				System.out.println(manager.addNews(title, text));
			} 	else if(option.equals("2")) {
				i = 1; 
				for(News n : Database.getNews()) {
					System.out.println(i + ". " + n);
					i += 1;
				}
				System.out.print("Enter number of news: ");
				int choice = Integer.parseInt(reader.readLine());
				System.out.println(manager.removeNews(Database.getNews().get(choice - 1)));
			} else if(option.equals("3")) {
				return;
			}
		}
	}
	
	public static void manageCourses(Manager manager, BufferedReader reader) throws IOException {
		for(Course c : Database.getCourses()) {
			System.out.println(String.format(("Course id: %s, name: %s, number of credits: %s, school: %s, type: %s"), c.getId(), c.getName(), c.getNumberOfCredits(), c.getSchool().getName(), c.getType()));
		}
		System.out.println("1. Add course for registration.\n2. Assign course to teacher.\n3. Create a course report.\n4. Back");
		String option = reader.readLine();
		if(option.equals("1")) {
			System.out.print("Enter id of the course: ");
			String id = reader.readLine();
			System.out.print("Enter name of the course: ");
			String name = reader.readLine();
			int i = 1;
			for(Course c : Database.getCourses()) {
				System.out.println(i + ". " + c.getId() + " " + c.getName());
				i += 1;
			}
			System.out.print("Enter course prerequisite(number) if exists. (If not - put 0): ");
			option = reader.readLine();
			Course prerequisite = null;
			if(!option.equals("0")) {
				prerequisite = Database.getCourses().get(Integer.parseInt(reader.readLine()) - 1);
			} 
			System.out.print("Enter the number of course credits: ");
			int numberOfCredits = Integer.parseInt(reader.readLine());
			i = 1;
			for(School s : Database.getSchools()) {
				System.out.print("\n" + i + ". " + s.getName());
				i += 1;
			}
			System.out.print("\nEnter the school of the course (number): ");
			School s = Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1);
			System.out.print("Science degrees:\n1. Bachelor\n2. Master\n3. PhD\nChoose correct: ");
			String input = reader.readLine();
			ScienceDegree scienceDegree = null;
			switch(input) {
				case "1" -> scienceDegree = ScienceDegree.BACHELOR;
				case "2" -> scienceDegree = ScienceDegree.MASTER;
				case "3" -> scienceDegree = ScienceDegree.PHD;
			}
			System.out.println("Choose the course type:\n1. Required\n2. Minor\n3. Major\n4. Free");
			CourseType c = null;
			switch(input) {
				case "1" -> c = CourseType.REQUIRED;
				case "2" -> c = CourseType.MINOR;
				case "3" -> c = CourseType.MAJOR;
				case "4" -> c = CourseType.FREE;
			}
			input = reader.readLine();
			Course newCourse = new Course(id, name, prerequisite, numberOfCredits, s, scienceDegree, c);
			System.out.println(manager.addCoursesForRegistration(newCourse));
			
		} else if(option.equals("2")) {
			for(Course c : Database.getCourses()) {
				System.out.println(String.format(("Course id: %s, name: %s, number of credits: %s, school: %s, type: %s"), c.getId(), c.getName(), c.getNumberOfCredits(), c.getSchool().getName(), c.getType()));
			}
			System.out.print("Enter course id: ");
			String courseId = reader.readLine();
			Course course = Database.getCourseById(courseId);
			for(Teacher t : Database.getTeachers()){
				System.out.println(String.format(("Teacher id: %s, name: %s, type: %s, school: %s"), t.getId(), t.getFullName(), t.getType(), t.getSchool().getName()));
			}
			System.out.print("Enter teacher id: ");
			String teacherId = reader.readLine();
			Teacher teacher = Database.getTeacherById(teacherId);
			if(course != null) {
				if(teacher != null) {
					if(manager.assignCourseToTeacher(course, teacher)) {
						course.getTeachers().add(teacher);
						System.out.println(course.getTeachers());
						System.out.println("The course has been successfully assigned to a teacher");
					} else {
						System.out.println("Failed to assign course to teacher");
					}
				} else {
					System.out.println("Teacher not found");
				}
			} else {
				System.out.println("Course not found");
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
			System.out.println(manager.viewTeachersAlphabetically());
		} else if(option.equals("2")) {
			System.out.println(manager.viewTeacherByRate());
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
			manager.processRequests(Integer.parseInt(reader.readLine()));
		} else if(option.equals("0")) {
			return;
		}
	}
}