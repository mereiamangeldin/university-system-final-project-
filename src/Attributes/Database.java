package Attributes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.*;

import Actors.*;

public final class Database {
	private final static String BASE_PATH = "C:\\temp\\";
	private static String path;
    private static Vector<User> users;
		
    private Database(String path) {
    	this.path = path;
    };
    
	private static Database instance = new Database(BASE_PATH);

	public String getPath() {
		return this.path;
	}
	
	public static Database getInstance() {
		return instance;
	}
//	  private static Vector<Teacher> teachers;
//    private static Vector<Student> students;
//    private static Vector<Manager> managers;
//    private static Vector<Dean> deans;
//    private static Vector<Librarian> librarians;
//    private static Vector<Admin> admins;
//    private static Vector<TechSupportWorker> techSupportWorkers;
    private static Vector<School> schools;
    private static Vector<Book> books;
    private static Vector<Course> courses;
    private static Vector<News> news;
    private static Vector<Request> requests;
//    private static Vector<Parent> parents;
    private static Vector<String> userActions;
    
    {
//    	teachers = new Vector<Teacher>();
//    	students = new Vector<Student>();
//    	managers = new Vector<Manager>();
    	schools = new Vector<School>();
    	books = new Vector<Book>();
    	courses = new Vector<Course>();
    	news = new Vector<News>();
    	requests = new Vector<Request>();
//    	deans = new Vector<Dean>();
//    	librarians = new Vector<Librarian>();
//    	admins = new Vector<Admin>();
//    	techSupportWorkers = new Vector<TechSupportWorker>();
//    	parents = new Vector<Parent>();
    	users = new Vector<User>();
    	setUserActions(new Vector<String>()); 
    }
    
    public static Vector<User> getUsers() {
    	return users;
    }
    
    
    public static Employee getEmployeeByUsername(String username) {
    	for(Employee employee : getEmployees()) {
    		if(employee.getUsername().equals(username)) {
    			return employee;
    		}
    	}
    	return null;
    }
//    
//    public static Student getStudentById(String id) {
//    	for(Student s : getStudents()) {
//    		if(s.getId().equals(id)) {
//    			return s;
//    		}
//    	}
//    	return null;
//    }
    
    public static Vector<Employee> getEmployees() {
		Vector <Employee> employees = users.stream().filter(x -> x instanceof Employee).map(x -> (Employee)x).collect(Collectors.toCollection(Vector::new));
		return employees;
	}
	
	public static Vector<Teacher> getTeachers() {
		Vector <Teacher> teachers = users.stream().filter(x -> x instanceof Teacher).map(x -> (Teacher)x).collect(Collectors.toCollection(Vector::new));
		return teachers;
	}
	
	public static Vector<Student> getStudents() {
		Vector <Student> students = users.stream().filter(x -> x instanceof Student).map(x -> (Student)x).collect(Collectors.toCollection(Vector::new));
		return students;
	}
	
	public static Vector<Book> getBooks() {
		return books;
	}
	
	public static Vector<Course> getCourses() {
		return courses;
	}
	
	public static Vector<Manager> getManagers() {
		Vector <Manager> managers = users.stream().filter(x -> x instanceof Manager).map(x -> (Manager)x).collect(Collectors.toCollection(Vector::new));
		return managers;
	}
	
	public static Vector<News> getNews() {
		return news;
	}
	
	public static Vector<Request> getRequests() {
		return requests;
	}
	
	public static Vector<School> getSchools() {
		return schools;
	}
	
	public static Vector<Dean> getDeans() {
		Vector <Dean> deans = users.stream().filter(x -> x instanceof Dean).map(x -> (Dean)x).collect(Collectors.toCollection(Vector::new));
		return deans;
	}
	
	public static Vector<Librarian> getLibrarians() {
		Vector <Librarian> librarians = users.stream().filter(x -> x instanceof Librarian).map(x -> (Librarian)x).collect(Collectors.toCollection(Vector::new));
		return librarians;
	}
	
	public static Vector<Admin> getAdmins() {
		Vector <Admin> admins = users.stream().filter(x -> x instanceof Admin).map(x -> (Admin)x).collect(Collectors.toCollection(Vector::new));
		return admins;
	}
	
	public static Vector<TechSupportWorker> getTechSupportWorkers() {
		Vector <TechSupportWorker> techSupportWorkers = users.stream().filter(x -> x instanceof TechSupportWorker).map(x -> (TechSupportWorker)x).collect(Collectors.toCollection(Vector::new));
		return techSupportWorkers;
	}
	
	public static Vector<Parent> getParents(){
		Vector <Parent> parents = users.stream().filter(x -> x instanceof Parent).map(x -> (Parent)x).collect(Collectors.toCollection(Vector::new));
		return parents;
	}
	
	// Serialization and Deserialization parts
	// In order to serialize all users of the system and deserialize them.
	public static void serializeUsers() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "users.txt");
			ObjectOutputStream 	oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deserializeUsers() {
		try {
			FileInputStream fis = new FileInputStream(path + "users.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			users = (Vector<User>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			users = new Vector<User>();
		} catch(IOException e) {
			e.printStackTrace();
			users = new Vector<User>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			users = new Vector<User>();
		}
	}
	
	public static void serializeCourses() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "courses.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courses);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deserializeCourses() {
		try {
			FileInputStream fis = new FileInputStream(path + "courses.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courses = (Vector<Course>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			courses = new Vector<Course>();
		} catch(IOException e) {
			e.printStackTrace();
			courses = new Vector<Course>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			courses = new Vector<Course>();
		}
	}
	
	public static void serializeNews() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "news.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(news);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deserializeNews() {
		try {
			FileInputStream fis = new FileInputStream(path + "news.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			news = (Vector<News>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			news = new Vector<News>();
		} catch(IOException e) {
			e.printStackTrace();
			news = new Vector<News>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			news = new Vector<News>();
		}
	}
	
	public static void serializeBooks() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "books.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(books);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deserializeBooks() {
		try {
			FileInputStream fis = new FileInputStream(path + "books.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			books = (Vector<Book>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			books = new Vector<Book>();
		} catch(IOException e) {
			e.printStackTrace();
			books = new Vector<Book>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			books = new Vector<Book>();
		}
	}	
	
	public static void serializeSchools() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "schools.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(schools);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deserializeSchools() {
		try {
			FileInputStream fis = new FileInputStream(path + "schools.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			schools = (Vector<School>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			schools = new Vector<School>();
		} catch(IOException e) {
			e.printStackTrace();
			schools = new Vector<School>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			schools = new Vector<School>();
		}
	}	

	public static Vector<String> getUserActions() {
		return userActions;
	}

	public static void setUserActions(Vector<String> userActions) {
		Database.userActions = userActions;
	}
}