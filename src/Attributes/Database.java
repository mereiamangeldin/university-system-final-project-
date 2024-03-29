package Attributes;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import Interfaces.*;

import Actors.*;
import Enums.ManagerType;
/**Storage of university system.*/

public final class Database implements Serializable {
	private final static String BASE_PATH = "C:\\temp\\";
	private static String path;
    private static Vector<User> users;
    private static Vector<School> schools;
    private static Vector<Book> books;
    private static Vector<Course> courses;
    private static Vector<News> news;
    private static Vector<Request> requests;
    private static Vector<Action> userActions;

    private Database(String path) {
    	Database.path = path;
    };
    
	private static Database instance = new Database(BASE_PATH);

	public String getPath() {
		return Database.path;
	}
	
	/**
	 * returns instance of Database
	 * */
	
	public static Database getInstance() {
		return instance;
	}
    
    {
    	schools = new Vector<School>();
    	books = new Vector<Book>();
    	courses = new Vector<Course>();
    	news = new Vector<News>();
    	requests = new Vector<Request>();
    	users = new Vector<User>();
    	userActions = new Vector<Action>(); 
    }
    
    public static void loadAttributes() {
		Database.deserializeUsers();
		Database.deserializeNews();
		Database.deserializeBooks();
		Database.deserializeCourses();
		Database.deserializeSchools();
		Database.deserializeUserActions();
    }
   
	public static Vector<Action> getUserActions(){
    	return userActions;
    }
    
    public static void setUserActions(Vector<Action> userActions) {
    	Database.userActions = userActions;
    }
    
    public static Vector<User> getUsers() {
    	return users;
    }
    
    public static Employee getEmployeeByUsername(String username) {
    	Employee e = getEmployees().stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    	return e;
    }

    public static Student getStudentById(String id) {
        Student s = getStudents().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        return s;
    }

    public static Manager getManagerById(String id) {
    	Manager m = getManagers().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    	return m;
    }
    
    public static Librarian getLibrarianById(String id) {
    	Librarian l = getLibrarians().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        return l;
    }
    
    public static Teacher getTeacherById(String id) {
    	Teacher t = getTeachers().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    	return t;
    }
    
    public static TechSupportWorker getTechSupportWorkerById(String id) {
    	TechSupportWorker t = getTechSupportWorkers().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    	return t;
    }
    
    public static Book getBookById(int id) {
    	Book b = Database.getBooks().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    	return b;
    }
    
    public static Course getCourseById(String id) {
    	Course c = Database.getCourses().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    	return c;
    }
     
    public static User getUserByUsername(String username) {
    	User u = Database.getUsers().stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    	return u;
    }
   
    
    public static Employee getEmployeeById(String id) {
    	Employee e = Database.getEmployees().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    	return e;
    }
    
    public static Vector<Course> getTeachersCourse(Teacher teacher) {
    	Vector<Course> v = new Vector<Course>();
    	for(Course c : Database.getCourses()) {
    		if(c.getTeachers().contains(teacher)) {
    			v.add(c);
    		}
    	}
    	return v;
    }
    
    public static Vector<Manager> getORManagers() {
    	Vector <Manager> ORManagers = getManagers().stream().filter(x -> x.getType().equals(ManagerType.OR)).collect(Collectors.toCollection(Vector::new));
    	return ORManagers;
    }
    
    public static Vector<Employee> getEmployees() {
		Vector <Employee> employees = users.stream().filter(x -> x instanceof Employee).map(x -> (Employee)x).collect(Collectors.toCollection(Vector::new));
		return employees;
	}
	
	public static Vector<Teacher> getTeachers() {
		Vector <Teacher> teachers = users.stream().filter(x -> x instanceof Teacher).map(x -> (Teacher)x).collect(Collectors.toCollection(Vector::new));
		return teachers;
	}
	
	public static Vector<Student> getStudents() {
		Vector<Student> students = users.stream().filter(x -> x instanceof Student).map(x -> (Student)x).collect(Collectors.toCollection(Vector::new));
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
		Vector <Dean> deans = Database.getUsers().stream().filter(x -> x instanceof Dean).map(x -> (Dean)x).collect(Collectors.toCollection(Vector::new));
		return deans;
	}
	
	public static Vector<Librarian> getLibrarians() {
		Vector <Librarian> librarians = Database.getUsers().stream().filter(x -> x instanceof Librarian).map(x -> (Librarian)x).collect(Collectors.toCollection(Vector::new));
		return librarians;
	}
	
	public static Vector<Admin> getAdmins() {
		Vector <Admin> admins = Database.getUsers().stream().filter(x -> x instanceof Admin).map(x -> (Admin)x).collect(Collectors.toCollection(Vector::new));
		return admins;
	}
	
	public static School getSchoolByName(String name) {
		School s = Database.getSchools().stream().filter(x -> x.getShortName().equals(name)).findFirst().orElse(null);
		return s;
	}
	
	public static Vector<TechSupportWorker> getTechSupportWorkers() {
		Vector <TechSupportWorker> techSupportWorkers = Database.getUsers().stream().filter(x -> x instanceof TechSupportWorker).map(x -> (TechSupportWorker)x).collect(Collectors.toCollection(Vector::new));
		return techSupportWorkers;
	}
	
	public static Vector<Parent> getParents(){
		Vector <Parent> parents = users.stream().filter(x -> x instanceof Parent).map(x -> (Parent)x).collect(Collectors.toCollection(Vector::new));
		return parents;
	}
	
	// Serialization and Deserialization parts
	// In order to serialize all users of the system and deserialize them.
    public static void serializeAll() {
    	Database.serializeUsers();
    	Database.serializeBooks();
    	Database.serializeCourses();
    	Database.serializeNews();
    	Database.serializeSchools();
    	Database.serializeUserActions();
    }
    
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
	
    public static void serializeUserActions() {
		try {
			FileOutputStream fos = new FileOutputStream(path + "userActions.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userActions);
			oos.flush();
			oos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();	
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}

    @SuppressWarnings("unchecked")
	public static void deserializeUserActions() {
		try {
			FileInputStream fis = new FileInputStream(path + "userActions.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			userActions = (Vector<Action>)ois.readObject();
			fis.close();
			ois.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			userActions = new Vector<Action>();
		} catch(IOException e) {
			e.printStackTrace();
			userActions = new Vector<Action>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			userActions = new Vector<Action>();
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
}