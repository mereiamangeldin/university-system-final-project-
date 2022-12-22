package Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Actors.*;
import Attributes.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;

public class Menu {
	public static void main(String[] args) throws IOException, ParseException {	
		Database.loadAttributes();
		
//		System.out.println(Database.getUsers());
//		System.out.println(Database.getBooks());
		
		InputStreamReader myStream = new InputStreamReader(System.in);        
		BufferedReader reader = new BufferedReader(myStream);		
	
		System.out.println("""
				╔╗╔╗╔╗╔═══╗╔╗──╔══╗╔══╗╔╗──╔╗╔═══╗───╔════╗╔══╗───╔════╗╔╗╔╗╔═══╗───╔╗╔╗╔╗─╔╗╔══╗╔╗╔╗╔═══╗╔═══╗╔══╗╔══╗╔════╗╔╗╔╗───╔══╗╔╗╔╗╔══╗╔════╗╔═══╗╔╗──╔╗
				║║║║║║║╔══╝║║──║╔═╝║╔╗║║║──║║║╔══╝───╚═╗╔═╝║╔╗║───╚═╗╔═╝║║║║║╔══╝───║║║║║╚═╝║╚╗╔╝║║║║║╔══╝║╔═╗║║╔═╝╚╗╔╝╚═╗╔═╝║║║║───║╔═╝║║║║║╔═╝╚═╗╔═╝║╔══╝║║──║║
				║║║║║║║╚══╗║║──║║──║║║║║╚╗╔╝║║╚══╗─────║║──║║║║─────║║──║╚╝║║╚══╗───║║║║║╔╗─║─║║─║║║║║╚══╗║╚═╝║║╚═╗─║║───║║──║╚╝║───║╚═╗║╚╝║║╚═╗──║║──║╚══╗║╚╗╔╝║
				║║║║║║║╔══╝║║──║║──║║║║║╔╗╔╗║║╔══╝─────║║──║║║║─────║║──║╔╗║║╔══╝───║║║║║║╚╗║─║║─║╚╝║║╔══╝║╔╗╔╝╚═╗║─║║───║║──╚═╗║───╚═╗║╚═╗║╚═╗║──║║──║╔══╝║╔╗╔╗║
				║╚╝╚╝║║╚══╗║╚═╗║╚═╗║╚╝║║║╚╝║║║╚══╗─────║║──║╚╝║─────║║──║║║║║╚══╗───║╚╝║║║─║║╔╝╚╗╚╗╔╝║╚══╗║║║║─╔═╝║╔╝╚╗──║║───╔╝║───╔═╝║─╔╝║╔═╝║──║║──║╚══╗║║╚╝║║
				╚═╝╚═╝╚═══╝╚══╝╚══╝╚══╝╚╝──╚╝╚═══╝─────╚╝──╚══╝─────╚╝──╚╝╚╝╚═══╝───╚══╝╚╝─╚╝╚══╝─╚╝─╚═══╝╚╝╚╝─╚══╝╚══╝──╚╝───╚═╝───╚══╝─╚═╝╚══╝──╚╝──╚═══╝╚╝──╚╝
				1. Login
				0. Exit""");
		
		while(true) {
			String input = reader.readLine();
			if(input.equals("1")) {
				System.out.println("""
					Choose your role:
					1. Teacher
					2. Student
					3. Manager
					4. Librarian
					5. Tech support worker
					6. Dean
					7. Parent 
					8. Admin
					0. Cancel""");
				input = reader.readLine();
				if(input.equals("0")) {
					break;
				}
				User user = findUser(input, reader);

				if(user != null) {
					if(user instanceof Student) StudentMenu.menu(user);
					else if(user instanceof Teacher) TeacherMenu.menu(user);
					else if(user instanceof Admin) AdminMenu.menu(user);
					else if(user instanceof Manager) ManagerMenu.menu(user);
					else if(user instanceof Librarian) LibrarianMenu.menu(user);
					else if(user instanceof TechSupportWorker) TechSupportWorkerMenu.menu(user);
					else if(user instanceof Parent) ParentMenu.menu(user);
					else if(user instanceof Dean) DeanMenu.menu(user);
				} 
			} else if(input.equals("0")) {
				System.out.println("Have a nice day!");
				break;
			}

		}
	}
	
	public static User findUser(String input, BufferedReader reader) throws IOException {
		System.out.print("Enter your username: ");
		String username = reader.readLine();
		System.out.print("Enter your password: ");
		String password = reader.readLine();
		for(User u : Database.getUsers()) {
			if(u.getUsername().equals(username)) {
				if(u.login(password)) {
					return u;
				}
				else {
					System.out.println("Password is incorrect."); 
					return null;
				}
			}
		}
		System.out.println("User not found.");
		return null;
	}
	
	public static boolean checkLogin(SimpleUser u, String username, String password) {
		if(u.getUsername().equals(username)) {
			if(u.login(password)) {
				return true;
			}
			else {
				System.out.println("Password is incorrect.");
			}
		}
		return false;
	}
	
	public static void changePassword(User user, BufferedReader reader) throws IOException {
		String option;
		while(true) {
			System.out.print("Enter your current password: ");
			String oldPassword = reader.readLine();
			if(oldPassword.equals(user.getPassword())) {
				System.out.print("Enter new password: ");
				String newPassword = reader.readLine();
				System.out.print("Enter new password again: ");
				String newPassword2 = reader.readLine();
				if(newPassword.equals(newPassword2)) {
					if(newPassword.equals(user.getPassword())) {
						System.out.println("The new password is the same as the present one.");
					} else {
						user.setPassword(newPassword);
						System.out.println("Password was changed succesfully.");
						Database.getUserActions().add(new Action(user, new Date(), String.format("User: %s changed password", user.getFullName())));
					}

				} else {
					System.out.println("New passwords don't match");
				}
			} else {
				System.out.println("Not correct current password.\n1. Try again\n2. Return to main menu.");
				option = reader.readLine();
				if(option.equals("1")) {
					continue;
				} else if(option.equals("2")){
						return;
				}		
			}
			System.out.println("1. Change again\n0. Return to main menu.");
			option = reader.readLine();
			if(option.equals("0")) {
				break;
			}
		}	
	}
	
	public static void sendMessage(Employee e, BufferedReader reader) throws IOException {
		System.out.print("Enter text of the message: ");
		String text = reader.readLine();
		Message m = new Message(new Date(), text);
		for(Employee ee : Database.getEmployees()) {
			System.out.println(String.format(("Id: %s, name: %s"), ee.getId(), ee.getFullName()));
		}
		System.out.print("\nEnter employee id you want to message to: ");
		String id = reader.readLine();
		Employee employee = Database.getEmployeeById(id);
		if(employee != null) {
			e.sendMessage(m, employee);
			System.out.println("Message sent.");
		} 
		else {
			System.out.println("Employee not found.");
		}
	}
	
	public static void viewNews(Employee employee, BufferedReader reader) throws NumberFormatException, IOException {
		int newsOrder = 1; 
		System.out.println("NEWS");
		for(News n : Database.getNews()) {
			System.out.println(newsOrder + ". " + n.getTitle() + "\n" + n.getText() + "\nComments: ");
			for(String s : n.getComments()) {
				System.out.println(s);
			}
			newsOrder += 1;
		} 
		String newsMenu = ("""
				\n1. Comment news.
				0. Back. """);
		while(true) {
			System.out.println(newsMenu);
			String option = reader.readLine();
			if(option.equals("1")) {
				System.out.print("Enter number of news: ");
				newsOrder = Integer.parseInt(reader.readLine());
				System.out.print("Enter comment: ");
				String comment = reader.readLine();
				employee.writeComment(comment, Database.getNews().get(newsOrder - 1));
				System.out.println("You commented on the news.");
			} else if(option.equals("0")) {
				return;
			}
		}
	}
	
	public static void makeRequest(Employee employee, BufferedReader reader) throws IOException {
		String requestMenu = "Who do you want to contact?\n1. Technical Support Center.\n2. Dean's office.\n3. Office of the register.\n0. Back.";
		String option;
		while(true) {
			System.out.println(requestMenu);
			option = reader.readLine();
			if(option.equals("0")) {
				break;
			}
			String id, text;
			int i = 1;
			if(option.equals("1")) {
				if(employee instanceof TechSupportWorker) {
					System.out.println("You do not have permission for this action.");
					continue;
				}
				for(TechSupportWorker t : Database.getTechSupportWorkers()) {
					System.out.println(i + ". " + t.getId() + " " + t.getFullName());
					i += 1;
				}
				System.out.print("Enter the id of the employee you want to write a request to: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				TechSupportWorker t = Database.getTechSupportWorkerById(id);
				if(t != null) {
					System.out.println(employee.makeRequest(new Request(employee.getId(), RequestType.SimpleRequest, text), Database.getTechSupportWorkerById(id)));
				} else {
					System.out.println("Tech support worker does not found.");
				}
			}
			if(option.equals("2")) {
				i = 1;
				for(School s : Database.getSchools()) {
					System.out.println(i + ". " + s.getName());
					i += 1;
				}
				System.out.print("What school do you want to apply to? (enter number): ");
				for(Manager m : Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getManagers()) {
					System.out.println(m);
				}
				
				System.out.print("Enter the id of the manager you want to write: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				Manager m = Database.getManagerById(id);
				System.out.println(text.length());
				if(m != null) {
					System.out.println(employee.makeRequest(new Request(employee.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
				} else {
					System.out.println("Manager does not found.");
				}
			}
			if(option.equals("3")) {
				for(Manager m : Database.getORManagers()) {
					System.out.println(m);
				}
				System.out.print("Enter the id of the manager of office of the register: ");
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				Manager m = Database.getManagerById(id);
				if(m != null) {
					System.out.println(employee.makeRequest(new Request(employee.getId(), RequestType.SimpleRequest, text), Database.getManagerById(id)));
				} else {
					System.out.println("Manager does not found.");
				}			
			}		
		}	
	}
	
	public static void viewStudent(Employee employee, BufferedReader reader) throws IOException {
		for(Student s : Database.getStudents()) {
			System.out.println(String.format("Id: %s, %s, year of study: %s, school: %s ", s.getId(), s.getFullName(), s.getYearOfStudy(), s.getSchool().getName()));
		}
		while(true) {
			System.out.println("1. Sort alphabetically.\n2. Sort by gpa.\n3. Back");
			String option = reader.readLine();
			if(option.equals("3")) {
				break;
			} else if(option.equals("1") || option.equals("2")) {
				for(Student s : employee.viewStudentBy(Integer.parseInt(option))) {
					System.out.println(String.format("Id: %s, %s, year of study: %s, school: %s ", s.getId(), s.getFullName(), s.getYearOfStudy(), s.getSchool().getName()));
				}
			}
		}
	}
	
	public static void readMessages(Employee employee, BufferedReader reader) {
		if(employee.getEmail().size() == 0) {
			System.out.println("You don't have messages.");
		} else {
			for(Employee e : employee.getEmail().keySet()) {
				Message m = employee.getEmail().get(e);
				System.out.println("Message from: " + e.getFullName() + ", text: " + m.getText() + ", date: " + m.getDate());
			}
		}
	}
}