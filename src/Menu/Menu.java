package Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import Actors.*;
import Attributes.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;
import javafx.util.Pair;

public class Menu {
	public static void main(String[] args) throws IOException, ParseException {	
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		Dean d = new Dean(new SimpleUser("Alibek", "Bisembayev", "sitetop", dateFormat.parse("1986/06/02")), "D1", dateFormat.parse("2006/02/28"), 800000, "203-139");
//		Dean d2 = new Dean(new SimpleUser("Assylbek", "Issakhov", "mkmtop", dateFormat.parse("1986/12/20")), "D2", dateFormat.parse("2006/04/12"), 800000, "405-294");
//		Dean d3 = new Dean(new SimpleUser("Martin", "Kennes", "lsetop", dateFormat.parse("1980/11/11")), "D3", dateFormat.parse("2001/01/09"), 900000, "139-293");
//		Dean d4 = new Dean(new SimpleUser("Marat", "Farad", "bstop", dateFormat.parse("1985/02/11")), "D4", dateFormat.parse("2004/03/01"), 1000000, "353-224");
//		School SITE = new School("School of Information Technologies and Engineering", d, "SITE");
//		School SAM = new School("School of Applied Mathematics", d2, "SAM");
//		School ISE = new School("International School of Economucs", d3, "ISE");
//		School BS = new School("Business School", d4, "BS");
//		Admin a = new Admin(new SimpleUser("Kirill", "Ivanov", "admin1", dateFormat.parse("1980/10/31")), "A20103", dateFormat.parse("1980/10/31"), 2000000, "123-456");
//		Student s = new Student(new SimpleUser("Karashash", "Serikova", "karashash", dateFormat.parse("2004/12/01")), "21B030944", SITE, 1, true, 32500, ScienceDegree.BACHELOR);
//		WithStudentResearcher sr = new WithStudentResearcher(s);
//		Student s2 = new Student(new SimpleUser("Dinara", "Berikova", "dinara", dateFormat.parse("2003/06/02")), "21B030723", SAM, 2, true, 65000, ScienceDegree.BACHELOR);
//		Student s3 = new Student(new SimpleUser("Ermek", "Berikova", "ermek", dateFormat.parse("2003/06/02")), "21B030723", SAM, 2, true, 65000, ScienceDegree.BACHELOR);
//		Teacher t = new Teacher(new SimpleUser("Asel", "Kereeva", "aselkapuper", dateFormat.parse("1976/09/22")), "T3214", dateFormat.parse("1996/05/05"), 360000, "125-999", SITE, TeacherTypes.TUTOR);
//		Teacher t2 = new Teacher(new SimpleUser("Kemel", "Uteev", "kemek", dateFormat.parse("1990/08/21")), "T9384", dateFormat.parse("2020/01/25"), 380000, "101-199", SAM, TeacherTypes.PROFESSOR);
//		Teacher t3 = new Teacher(new SimpleUser("Danial", "Salamatov", "d_salamatov", dateFormat.parse("1980/11/21")), "T1234", dateFormat.parse("2002/06/13"), 450000, "111-890", SITE, TeacherTypes.LECTURER);
//		Teacher t4 = new Teacher(new SimpleUser("Adema", "Aldash", "adema_dema", dateFormat.parse("1990/02/12")), "T0909", dateFormat.parse("2020/03/15"), 250000, "154-904", SAM, TeacherTypes.TUTOR);
//		Teacher t5 = new Teacher(new SimpleUser("Uldana", "Ustemirova", "uldana_98", dateFormat.parse("1998/08/01")), "T1256", dateFormat.parse("2019/12/22"), 470000, "167-790", BS, TeacherTypes.PROFESSOR);
//		Teacher t7 = new Teacher(new SimpleUser("Gaziz", "Idrisov", "gazi_mazi00", dateFormat.parse("1979/12/29")), "T7894", dateFormat.parse("2005/12/09"), 550000, "190-210", SITE, TeacherTypes.TUTOR);
//		Course c = new Course("CL1", "Calculus 1", null, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c1 = new Course("LA1", "Linear Algebra", null, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c2 = new Course("CL2", "Calculus 2", c, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c3 = new Course("PP1", "Programming Principles 1", null, 4, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c4 = new Course("PP2", "Programming Principles 2", c3, 4, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c5 = new Course("OOP1", "Object-Oriented Programming", c4, 3, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		News n = new News("Announcement about the competition \"A. S. Pushkin's Poetry Evening\".", "We invite all students to take part in the competition. For the record - a.omarova@kbtu.kz");
//		News n2 = new News("On the transfer of the classes of the teacher Kereev A. to the online format", "Classes on the subject \"Oratory\" by Kereeva Asel are transferred from offline to online format due to the health of the teacher.");
//		Manager m = new Manager(new SimpleUser("Assylai", "Aman", "assylai", dateFormat.parse("1989/12/01")), "M1", dateFormat.parse("1999/08/12"), 500000, "122-122", ManagerType.SITE);
//		Manager m1 = new Manager(new SimpleUser("Tatyana", "Morgunova", "tanya", dateFormat.parse("1960/04/02")), "M2", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.SAM);
//		Manager m3 = new Manager(new SimpleUser("Assel", "Koka", "assel", dateFormat.parse("1983/04/02")), "M3", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.BS);
//		Manager m4 = new Manager(new SimpleUser("Dameli", "Li", "dameli", dateFormat.parse("1983/04/02")), "M4", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.OR);
//		Manager m6 = new Manager(new SimpleUser("Nataliya", "Sneg", "nata", dateFormat.parse("1983/04/02")), "M5", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.SITE);
//		Manager m7 = new Manager(new SimpleUser("Juliya", "Merezhko", "juliya", dateFormat.parse("1983/04/02")), "M6", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.ISE);
//		Manager m8 = new Manager(new SimpleUser("Fariza", "Nurjan", "fariza", dateFormat.parse("1983/04/02")), "M7", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.OR);
//		Manager m9 = new Manager(new SimpleUser("Maksat", "Kani", "maksat", dateFormat.parse("1983/04/02")), "M8", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.ISE);
//		Manager m10 = new Manager(new SimpleUser("Lyazzat", "R", "lyazzat", dateFormat.parse("1960/04/02")), "M9", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.SAM);
//		Manager m11 = new Manager(new SimpleUser("Meruert", "N", "meru", dateFormat.parse("1960/04/02")), "M10", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.SITE);
//		Librarian l = new Librarian(new SimpleUser("Antonina", "Tsoy", "antonina", dateFormat.parse("1991/02/01")), "L3234", dateFormat.parse("2021/04/05"), 310000, "112-135");
//		TechSupportWorker tsw = new TechSupportWorker(new SimpleUser("Alikhan", "Karabayev", "alikhan", dateFormat.parse("1970/06/04")), "TS9234", dateFormat.parse("1985/02/28"), 390000, "130-139");
//		Parent p = new Parent(new SimpleUser("Tamara", "Beken", "tamara", dateFormat.parse("1984/03/23")), s);
//		Book b1 = new Book("Algorithms and Data Structures in Java", 100, "Michael Goodrich", 1997);
//		Book b2 = new Book("Schaum's Outline of Linear Algebra, Sixth Edition", 50, "Seymour Lipschutz", 2017);
//		Book b3 = new Book("Business Kazakh Language", 75, "Gulzhan Utebayeva", 2016);
//		Book b4 = new Book("Object-Oriented Programming and Design", 100, "Pakizar Shamoi", 2014);
//		Book b5 = new Book("Discrete Structures and Its Apllications", 150, "Kenneth Rosen", 1995);
//		Book b6 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", 20, "Robert C. Martin", 2004);
//		Book b7 = new Book("Introduction to Algorithms", 35, "Thomas H. Cormen", 2007);
//		Book b8 = new Book("Structure and Interpretation of Computer Programs (SICP)", 10, "Harold Abelson", 2010);
//		Book b9 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", 30, "Erich Gamma", 2001);
//		Book b10 = new Book("Mathematical Statisctics with Applications", 60, "Dennis Wackerly", 2002);
//		Book b11 = new Book("Practical problems in molecular physics and thermodynamics", 100, "Vladimir Voronkov", 2013);
//
//		Database.serializeAll();

		Database.loadAttributes();
		
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
					else if(user instanceof WithStudentResearcher) StudentResearcherMenu.menu(user);
					else if(user instanceof Teacher) TeacherMenu.menu(user);
					else if(user instanceof WithTeacherResearcher) TeacherMenu.menu(((WithTeacherResearcher)user).getDecoratedTeacher());
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