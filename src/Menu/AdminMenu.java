package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import Actors.*;
import Attributes.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;
import javafx.util.Pair;

public class AdminMenu {
    
    public static void menu(User user) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	Admin admin = (Admin)user;
    	String menuAdmin = "\nWelcome, : " + admin.getFullName() + """
    			\n1. Manage users.
    			2. See log files about user actions.
    			3. View students.
    			4. View all users.
    			5. Send message.
    			6. Read messages.
    			7. View news.
    			8. Change password.
    			9. Make request.
    			10. Save changes.
    			0. Logout.""";
    	while(admin.getLogged()) {
        	System.out.println(menuAdmin);
        	String option = reader.readLine();
        	if(option.equals("0")) {
        		admin.logout();
        		System.out.println("You logged out.");
        		Database.serializeAll();
        		break;
        	}
        	else if(option.equals("1")) {
        		AdminMenu.manageUsersMenu(admin, reader);
        	}
        	else if(option.equals("2")) {
        		for(Action a : admin.seeUsersActions()) {
        			System.out.println(a);
        		}
        	}
        	else if(option.equals("3")) {
        		Menu.viewStudent(admin, reader);
        	}
        	else if(option.equals("4")) {
        		for(User u : Database.getUsers()) {
        			System.out.println(u);
        		}
        	}
        	else if(option.equals("5")) {
        		Menu.sendMessage(admin, reader);
        	}
        	else if(option.equals("6")) {
        		Menu.readMessages(admin, reader);
        	}
        	else if(option.equals("7")) {
        		Menu.viewNews(admin, reader);
        	} 
        	else if(option.equals("8")) {
        		Menu.changePassword(admin, reader);
        	}
        	else if(option.equals("9")) {
        		Menu.makeRequest(admin, reader);
        	} else if(option.equals("10")) {
        		Database.serializeAll();
        		System.out.println("Your changes ase saved");
        	}
    	}
    }
    	
	public static void manageUsersMenu(Admin admin, BufferedReader reader) throws IOException, ParseException {
		String adminManageUser = """
				1. Create new user.
				2. Remove user. 
				0. Return to main menu.""";
		while(true) {
			System.out.println(adminManageUser);
			String option = reader.readLine();
			if(option.equals("0")) {
				break;
			}
			else if(option.equals("1")) {
				String userType = """
					1. Student
					2. Teacher
					3. Manager
					4. Librarian
					5. Tech support worker
					6. Parent
					7. Admin
					0. Return""";
				System.out.println(userType);
				option = reader.readLine();
				if(option.equals("0")) {
					break;
				} 
				else {
					AdminMenu.addUser(option, reader);
					break;
				}
			}
			else if(option.equals("2"))
        		for(User u : Database.getUsers()) {
        			System.out.println(u);
        		}
				System.out.print("Enter the username of the user you want to delete: ");
				option = reader.readLine();

				User u = Database.getUserByUsername(option);
				System.out.println(admin.removeUser(u));

			}
	}
    
    public static void addUser(String userType, BufferedReader r) throws IOException, ParseException {
    	String name, surname, password, date;
    	Date d;
    	boolean isStudentResearcher = false;
    	boolean isTeacherResearcher = false;
    	User user = null;
    	WithTeacherResearcher wt = null;
    	WithStudentResearcher ws = null;
        BufferedReader reader = r;
        
        // General date 
    	System.out.print("Name: ");
    	name = reader.readLine();
    	
    	System.out.print("Last name: ");
    	surname = reader.readLine();
    	
    	System.out.print("Create password: ");
    	password = reader.readLine();
    	
    	System.out.print("Date of Birth in format yyyy/MM/dd: ");
    	date = reader.readLine();
    	try {
        	d = new SimpleDateFormat("yyyy/MM/dd").parse(date);
    	} catch(ParseException p) {
    		System.out.println("Incorrect date. Pleasy retry again.");
    		return;
    	}
    	
    	// Student date 
    	if(userType.equals("1")) { 
    		System.out.print("ID: ");
    		String id = reader.readLine();
    		int i = 1;
    		for(School s : Database.getSchools()) {
    			System.out.println(i + ". " + s.getName());
		    	i++;
		    }
    		System.out.print("Enter the number of student's school: ");
    		int choice = Integer.parseInt(reader.readLine());
    		School s = Database.getSchools().get(choice - 1);
		    System.out.print("Year of study: ");
		    int yearOfStudy = Integer.parseInt(reader.readLine());
		    System.out.println("""
		    		1. On a grant
		    		2. Not on grant""");
		    String isOnGrant = reader.readLine();
		    boolean grant = false;
		    if(isOnGrant.equals("1")) {
		    	grant = true;
		    } else if(isOnGrant.equals("2")) {
		    	grant = false;
		    }
		    System.out.print("Amount of scholarship if exists. (If no - put 0): ");
		    double scholarship = Double.parseDouble(reader.readLine());
		    System.out.println("Science degree:\n1. BACHELOR\n2. MASTER\n3. PHD");
		    String sd = reader.readLine();
		    ScienceDegree scienceD = null;
		    if(sd.equals("1")) {
		    	scienceD = ScienceDegree.BACHELOR;
		    } else if(sd.equals("2")) {
		    	scienceD = ScienceDegree.MASTER;
		    } else if(sd.equals("3")) {
		    	scienceD = ScienceDegree.PHD;
		    }
			System.out.println("Is student doing research?\n1. Yes\n2. No");
		    String option = reader.readLine();
		    if(option.equals("1")) {
			    user = new Student(new SimpleUser(name, surname, password, d), id, s, yearOfStudy, grant, scholarship, scienceD);
			    ws = new WithStudentResearcher(user);
			    isStudentResearcher = true;
		    } else if(option.equals("2")) {
			    user = new Student(new SimpleUser(name, surname, password, d), id, s, yearOfStudy, grant, scholarship, scienceD);
		    }
    	}
    	// Parent date 
		else if(userType.equals("6")) {
			for(Student st : Database.getStudents()) {
    			System.out.println(st);
    		}
    		System.out.println("Enter parent's child ID in the list of students:");
    		String idS = reader.readLine();
    		Student child = Database.getStudentById(idS);
    		if(child != null) {
        	user = new Parent(new SimpleUser(name, surname, password, d), child);
    		} 
		}
    	
    	// Continue entering employee date 
    	else {
    		System.out.print("ID: ");
    		String id = reader.readLine();
    		System.out.print("Hire date in format yyyy/MM/dd: ");
    		String hd = reader.readLine();
    		Date hireDate;
        	try {
        		hireDate = new SimpleDateFormat("yyyy/MM/dd").parse(hd);
        	} catch(ParseException p) {
        		System.out.println("Incorrect date. Pleasy retry again.");
        		return;
        	}
    		System.out.print("Salary: ");
    		double salary = Double.parseDouble(reader.readLine());
    		System.out.print("Insurance number: ");
    		String insuranceNumber = reader.readLine();
    		
    		// Continue with specific date 
    		// Teacher date 
    		if(userType.equals("2")) {
    			System.out.println("School: ");
    			int i = 1;
        		for(School s : Database.getSchools()) {
        			System.out.println(i + ". " + s.getName());
    		    	i++;
    		    }
        		System.out.print("Enter number of school: ");
        		School s = Database.getSchools().get(Integer.parseInt(reader.readLine()) - 1);
        		System.out.println("Job title: "
        				+ "1. Lecturer."
        				+ "2. Senior lecturer."
        				+ "3. Tutor."
        				+ "4. Professor");
        		String option = reader.readLine();
        		TeacherTypes t = null;
        		switch(option) {
        			case "1":
        				t = TeacherTypes.LECTURER;
        			case "2":
        				t = TeacherTypes.SENIORLECTURER;
        			case "3":
        				t = TeacherTypes.TUTOR;
        			case "4":
        				t = TeacherTypes.PROFESSOR;
        		}
    			System.out.println("Is teacher doing research?\n1. Yes\n2.No");
    			option = reader.readLine();
    			if(option.equals("1")) {
            		user = new Teacher(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber, s, t);
            		wt = new WithTeacherResearcher(user);
            		isTeacherResearcher = true;
    			} else if(option.equals("2")) {
            		user = new Teacher(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber, s, t);
    			}
    		} 
    		else if(userType.equals("3")) {
    			System.out.print("Manager type:\n1. OR\n2. SITE\n3. SAM\n4. BS\n5. ISE");
    			String option = reader.readLine();
    			ManagerType m = null;
    			switch(option) {
    				case "1":
    					m = ManagerType.OR;
    				case "2":
    					m = ManagerType.SITE;
    				case "3":
    					m = ManagerType.SAM;
    				case "4":
    					m = ManagerType.BS;
    				case "5":
    					m = ManagerType.ISE;
    			}
    			user  = new Manager(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber, m);
    		}
    		else if(userType.equals("4")) {
    			user = new Librarian(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber);
    		}
    		else if(userType.equals("5")) {
    			user = new TechSupportWorker(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber);
    		}
    		else if(userType.equals("7")) {
    			user  = new Admin(new SimpleUser(name, surname, password, d), id, hireDate, salary, insuranceNumber);
    		}	
    	}
    	
    	if(isStudentResearcher) {
    		if(!Database.getUsers().contains(ws)) {
    			Database.getUsers().add(ws);
        		System.out.println("User has been successfully created.");
    		} else {
    			System.out.println("Error! Such user already exists in a system");
    		}
    	} else if(isTeacherResearcher) {
    		if(!Database.getUsers().contains(wt)) {
    			Database.getUsers().add(wt);
        		System.out.println("User has been successfully created.");
    		} else {
    			System.out.println("Error! Such user already exists in a system");
    		}
    	} else {
    		if(!Database.getUsers().contains(user)) {
    			Database.getUsers().add(user);
        		System.out.println("User has been successfully created.");
    		} else {
    			System.out.println("Error! Such user already exists in a system");
    		}
    	}
    }
}