package Menu;

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

public class AdminMenu {
    
    public static void menu(User user) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	Admin admin = (Admin)user;
    	String menuAdmin = "\nWelcome, Admin: " + admin.getFullName() + """
    			\n1. Manage users.
    			2. See log files about user actions.
    			3. View students.
    			4. View all users.
    			5. Send message.
    			6. Read messages.
    			7. View news.
    			8. Change password.
    			9. Make request.
    			0. Logout.""";
    	while(admin.getLogged()) {
        	System.out.println(menuAdmin);
        	String option = reader.readLine();
        	switch(option) {
        		case "0":
        			admin.logout();
        			System.out.println("You logged out.");
        			break;
        		case "1":
        			String adminManageUser = """
        					1. Create new user.
        					2. Remove user. 
        					0. Return to main menu.""";
        			System.out.println(adminManageUser);
        			option = reader.readLine();
        			switch(option) {
        				case "0":
        					break;
        				case "1":
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
    			case "2":
//    				admin.seeUserActions(user);
//    				System.out.println("");
    			case "3":
    				admin.viewStudent();
//    				System.out.println();
    			case "4":
//    				System.out.println(Database.getUsers());
    			case "5":
    				System.out.print("Enter text of the message: ");
    				String text = reader.readLine();
    				Message m = new Message(new Date(), text);
//    				System.out.print(Database.getEmployees());
    				System.out.println("Enter employee username you want to message to: ");
    				String username = reader.readLine();
//    				Employee e = Database.getEmployeeByUsername(username);
//    				if(e != null) {
//    					admin.sendMessage(m, e);
//    					System.out.println("Message sent.");
//    				} 
//    				else {
//    					System.out.println("Employee not found.");
//    				}
    			case "6": 
    				System.out.println(admin.getEmail());
    			case "7":
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
    						admin.writeComment(comment, Database.getNews().get(choice - 1));
    						System.out.println("You commented on the news.");
    					case "0":
    						break;
    				}
    			case "8":
    				Menu.changePassword(user, reader);
    			case "9":
        			
        		

    	}
    	}
    }
    
    public static void addUser(String userType, BufferedReader r) throws IOException, ParseException {
    	String name, surname, password, date;
    	Date d;
    	User user = null;
        BufferedReader reader = r;
    	System.out.print("Name: ");
    	name = reader.readLine();
    	System.out.print("Last name: ");
    	surname = reader.readLine();
    	System.out.print("Create password: ");
    	password = reader.readLine();
    	System.out.print("Date of Birth in format yyyy/MM/dd: ");
    	date = reader.readLine();
    	d = new SimpleDateFormat("yyyy/MM/dd").parse(date);
    	if(userType.equals("1")) {
    		System.out.print("ID: ");
    		String id = reader.readLine();
    		int i = 1;
    		System.out.println("Enter the number of student's school: ");
    		for(School s : Database.getSchools()) {
    			System.out.println(i + ". " + s.getName());
		    	i++;
		    }
    		School s = Database.getSchools().get(i - 1);
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
		    System.out.println("Science degree: "
		    	+ "1. BACHELOR"
		    	+ "2. MASTER" 
		    	+ "3. PHD");
		    String sd = reader.readLine();
		    ScienceDegree scienceD = null;
		    switch(sd) {
		    	case "1": 
		    		scienceD = ScienceDegree.BACHELOR;
		    	case "2":
		    		scienceD = ScienceDegree.MASTER;
		    	case "3":
		    		scienceD = ScienceDegree.PHD;
		    }
		    user = new Student(name, surname, password, d, id, s, yearOfStudy, grant, scholarship, scienceD);
    	}
		else if(userType.equals("6")) {
			for(Student st : Database.getStudents()) {
    			System.out.println(st.getId() + " " + st.getFullName() + " " + st.getDateOfBirth());
    		}
    		System.out.println("Enter child's ID in the list of students:");
    		String idS = reader.readLine();
//    		Student child = Database.getStudentById(idS);
//    		if(child != null) {
//        		user = new Parent(name, surname, password, d, child);
//    		} 
		}
    	else {
    		System.out.print("ID: ");
    		String id = reader.readLine();
    		System.out.print("Hire date in format yyyy/MM/dd: ");
    		String hd = reader.readLine();
    		Date hireDate = new SimpleDateFormat("yyyy/MM/dd").parse(hd);
    		System.out.print("Salary: ");
    		double salary = Double.parseDouble(reader.readLine());
    		System.out.print("Insurance number: ");
    		String insuranceNumber = reader.readLine();
    		if(userType.equals("2")) {
    			System.out.println("School: ");
    			int i = 1;
        		for(School s : Database.getSchools()) {
        			System.out.println(i + ". " + s.getName());
    		    	i++;
    		    }
        		School s = Database.getSchools().get(i - 1);
        		System.out.println("Job title: "
        				+ "1. Lecturer."
        				+ "2. Senior lecturer."
        				+ "3. Tutor."
        				+ "4. Professor)");
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
        		user = new Teacher(name, surname, password, d, id, hireDate, salary, insuranceNumber, s, t);
    		} 
    		else if(userType.equals("3")) {
    			System.out.print("Manager type:"
    					+ "1. OR"
    					+ "2. SITE"
    					+ "3. SAM"
    					+ "4. BS");
    			String option = reader.readLine();
    			ManagerType m = null;
    			switch(option) {
    				case "1":
    					m = ManagerType.OR;
    				case "2":
    					m = ManagerType.SITI;
    				case "3":
    					m = ManagerType.SAM;
    				case "4":
    					m = ManagerType.BS;
    			}
    			user  = new Manager(name, surname, password, d, id, hireDate, salary, insuranceNumber, m);
    		}
    		else if(userType.equals("4")) {
    			user = new Librarian(name, surname, password, d, id, hireDate, salary, insuranceNumber);
    		}
    		else if(userType.equals("5")) {
    			user = new TechSupportWorker(name, surname, password, d, id, hireDate, salary, insuranceNumber);
    		}
    		else if(userType.equals("7")) {
    			user  = new Admin(name, surname, password, d, id, hireDate, salary, insuranceNumber);
    		}
    		
    	}
    	if(!Database.getUsers().contains(user)) {
    		Database.getUsers().add(user);
    		System.out.println("User has been successfully created.");
    	} else {
    		System.out.println("Error. Such a user exists in a system.");
    	}
    }
}