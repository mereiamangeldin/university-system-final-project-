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
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		Dean d = new Dean("Alibek", "Bisembayev", "alibeksitetop", dateFormat.parse("1986/06/02"), "D3334", dateFormat.parse("2006/02/28"), 800000, "203-139");
//		Dean d2 = new Dean("Assylbek", "Issakhov", "mkmtoptop", dateFormat.parse("1986/12/20"), "D2345", dateFormat.parse("2006/04/12"), 800000, "405-294");
//		School SITE = new School("School of Information Technologies and Engineering", d);
//		School SAM = new School("School of Applied Mathematics", d2);
//		Admin a = new Admin("Kirill", "Ivanov", "admin1", dateFormat.parse("1980/10/31"), "A20103", dateFormat.parse("1980/10/31"), 2000000, "123-456");
//		Student s = new Student("Kairat", "Baimyshev", "kairat_2004", dateFormat.parse("2004/12/01"), "21B030944", SITE, 1, true, 32500, ScienceDegree.BACHELOR);
//		Student s2 = new Student("Dilyara", "Berikova", "dilyara_2003", dateFormat.parse("2003/06/02"), "21B030723", SAM, 2, true, 65000, ScienceDegree.BACHELOR);
//		Student s3 = new Student("Ermekkali", "Berikova", "dilyara_2003", dateFormat.parse("2003/06/02"), "21B030723", SAM, 2, true, 65000, ScienceDegree.BACHELOR);
//		Teacher t = new Teacher("Asel", "Kereeva", "aselkapuper", dateFormat.parse("1976/09/22"), "T3214", dateFormat.parse("1996/05/05"), 360000, "125-999", SITE, TeacherTypes.TUTOR);
//		Teacher t2 = new Teacher("Kemel", "Uteev", "kemel123", dateFormat.parse("1990/08/21"), "T9384", dateFormat.parse("2020/01/25"), 380000, "101-199", SAM, TeacherTypes.PROFESSOR);
//		Course c = new Course("CL001", "Calculus 1", null, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c1 = new Course("LA123", "Linear Algebra", null, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c2 = new Course("CL002", "Calculus 2", c, 3, SAM, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c3 = new Course("PP001", "Programming Principles 1", null, 4, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c4 = new Course("PP002", "Programming Principles 2", c3, 4, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		Course c5 = new Course("OP300", "Object-Oriented Programming", c4, 3, SITE, ScienceDegree.BACHELOR, CourseType.REQUIRED);
//		News n = new News("Announcement about the competition \"A. S. Pushkin's Poetry Evening\".", "We invite all students to take part in the competition. For the record - a.omarova@kbtu.kz");
//		News n2 = new News("On the transfer of the classes of the teacher Kereev A. to the online format", "Classes on the subject \"Oratory\" by Kereeva Asel are transferred from offline to online format due to the health of the teacher.");
//		Manager m = new Manager("Kuralai", "Manasova", "kurochka_122", dateFormat.parse("1989/12/01"), "M1201", dateFormat.parse("1999/08/12"), 500000, "122-122", ManagerType.SITI);
//		Manager m1 = new Manager("Tatyana", "Morgunova", "tanushammm", dateFormat.parse("1960/04/02"), "M1234", dateFormat.parse("1980/04/05"), 600000, "194-135", ManagerType.SAM);
//		Librarian l = new Librarian("Antonina", "Tsoy", "tonya555", dateFormat.parse("1991/02/01"), "L3234", dateFormat.parse("2021/04/05"), 310000, "112-135");
//		TechSupportWorker tsw = new TechSupportWorker("Alikhan", "Karabayev", "alikhense23", dateFormat.parse("1970/06/04"), "TS9234", dateFormat.parse("1985/02/28"), 390000, "130-139");
//		Parent p = new Parent("Tamara", "Beken", "tamarochka004", dateFormat.parse("1984/03/23"), s);
//		System.out.println("Our team is the BEST in the WHOLE WORLD!");
//		
//		Database.serializeUsers();
//		Database.serializeBooks();
//		Database.serializeCourses();
//		Database.serializeNews();
//		Database.serializeSchools();
		
		Database.deserializeUsers();
		Database.deserializeNews();
		Database.deserializeBooks();
		Database.deserializeCourses();
		System.out.println(Database.getCourseById("PP002"));
		System.out.println(Database.getUserByUsername("a_kereeva"));

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
				2. Exit""");
		
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
					8. Admin""");
				input = reader.readLine();
				SimpleUser user = findUser(input, reader);
				if(user != null) {
					System.out.println(user);
					if(user instanceof Student) StudentMenu.menu(user);
					else if(user instanceof Teacher) TeacherMenu.menu(user);
					else if(user instanceof Admin) AdminMenu.menu(user);
					else if(user instanceof Manager) ManagerMenu.menu(user);
					else if(user instanceof Librarian) LibrarianMenu.menu(user);
					else if(user instanceof TechSupportWorker) TechSupportWorkerMenu.menu(user);
					else if(user instanceof Parent) ParentMenu.menu(user);
					else if(user instanceof Dean) DeanMenu.menu(user);
				}
			break;
			} else if(input.equals("2")) {
				System.out.println("Bye bye!");
				break;
			}
		}
	}
	
	public static SimpleUser findUser(String input, BufferedReader reader) throws IOException {
		System.out.print("Enter your username: ");
		String username = reader.readLine();
		System.out.print("Enter your password: ");
		String password = reader.readLine();
		for(SimpleUser u : Database.getUsers()) {
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
	
	public static void changePassword(SimpleUser user, BufferedReader reader) throws IOException {
		System.out.print("Enter your password: ");
		String oldPassword = reader.readLine();
		if(oldPassword.equals(user.getPassword())) {
			System.out.println("Enter new password: ");
			String newPassword = reader.readLine();
			System.out.println("Enter new password again: ");
			String newPassword2 = reader.readLine();
			if(newPassword.equals(newPassword2)) {
				if(newPassword.equals(user.getPassword())) {
					System.out.println("The new password is the same as the present one.");
				} else {
					user.setPassword(newPassword);
				}
			} else {
				System.out.println("New passwords don't match");
			}
		}
	}
	
	public static void sendMessage(Employee e, BufferedReader reader) throws IOException {
		System.out.print("Enter text of the message: ");
		String text = reader.readLine();
		Message m = new Message(new Date(), text);
		System.out.print(Database.getEmployees());
		System.out.println("Enter employee id you want to message to: ");
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
		for(News n : Database.getNews()) {
			System.out.println(newsOrder + ". " + n);
			newsOrder += 1;
		}
		String newsMenu = ("""
				1. Comment news.
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
		String requestMenu = "Who do you want to contact?\n1. Technical Support Center.\n2. Dean's office.\n3.Office of the register.\n.0.Back.";
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
				employee.makeRequest(new Request(employee.getId(), RequestType.EmployeeRequest, text), Database.getTechSupportWorkerById(id));
			}
			if(option.equals("2")) {
				i = 1;
				for(School s : Database.getSchools()) {
					System.out.println(i + ". " + s.getName());
					i += 1;
				}
				System.out.print("What school do you want to apply to? (enter number): ");
				System.out.println(Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getManagers());
				System.out.print(String.format("Enter the id of the manager of %s", Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1).getName()));
				id = reader.readLine();
				System.out.print("Text the description of your request: ");
				text = reader.readLine();
				employee.makeRequest(new Request(employee.getId(), RequestType.EmployeeRequest, text), Database.getManagerById(id));
			}
			if(option.equals("3")) {
				System.out.println(Database.getORManagers());
				System.out.print("Enter the id of the manager of office of the register: ");
				id = reader.readLine();
				System.out.println("Text the description of your request: ");
				text = reader.readLine();
				employee.makeRequest(new Request(employee.getId(), RequestType.EmployeeRequest, text), Database.getManagerById(id));
			}		
		}	
	}
	
	public static void viewStudent(Employee employee, BufferedReader reader) throws IOException {
		System.out.println(Database.getStudents());
		System.out.println("Sort alphabetically - type 1.\nSort by gpa - type 2.\nBack - 3.");
		String option = reader.readLine();
		if(option.equals("3")) {
			return;
		} else if(option.equals("1") || option.equals("2")) {
			System.out.println(employee.viewStudentBy(Integer.parseInt(option)));
		}
	}
}

