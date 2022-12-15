package Attributes;

import java.util.Vector;
import Actors.*;

public final class Database {
	private static final Database db = new Database();
	
	private static Vector<Teacher> teachers;
    private static Vector<Student> students;
    private static Vector<Manager> managers;
    private static Vector<Dean> deans;
    private static Vector<Librarian> librarians;
    private static Vector<Admin> admins;
    private static Vector<TechSupportWorker> techSupportWorkers;
    private static Vector<School> schools;
    private static Vector<Book> books;
    private static Vector<Course> courses;
    private static Vector<News> allNews;
    private static Vector<Request> requests;
    private static Vector<Parent> parents;
    private static Vector<String> userActions;
    
    {
    	teachers = new Vector<Teacher>();
    	students = new Vector<Student>();
    	managers = new Vector<Manager>();
    	schools = new Vector<School>();
    	books = new Vector<Book>();
    	courses = new Vector<Course>();
    	allNews = new Vector<News>();
    	requests = new Vector<Request>();
    	deans = new Vector<Dean>();
    	librarians = new Vector<Librarian>();
    	admins = new Vector<Admin>();
    	techSupportWorkers = new Vector<TechSupportWorker>();
    	parents = new Vector<Parent>();
    	setUserActions(new Vector<String>()); 
    }
    
    private Database() {};
    
    
	public static Database getDatabase() {
		return db;
	}
	
	public static Vector<Teacher> getTeachers() {
		return teachers;
	}
	
	public static Vector<Student> getStudents() {
		return students;
	}
	
	public static Vector<Book> getBooks() {
		return books;
	}
	
	public static Vector<Course> getCourses() {
		return courses;
	}
	
	public static Vector<Manager> getManagers() {
		return managers;
	}
	
	public static Vector<News> getAllNews() {
		return allNews;
	}
	
	public static Vector<Request> getRequests() {
		return requests;
	}
	
	public static Vector<School> getSchools() {
		return schools;
	}
	
	public static Vector<Dean> getDeans() {
	    return deans;
	}
	
	public static Vector<Librarian> getLibrarians() {
	    return librarians;
	}
	
	public static Vector<Admin> getAdmins() {
	    return admins;
	}
	
	public static Vector<TechSupportWorker> getTechSupportWorkers() {
	    return techSupportWorkers;
	}
	
	public static Vector<Parent> getParents(){
		return parents;
	}

	public static Vector<String> getUserActions() {
		return userActions;
	}

	public static void setUserActions(Vector<String> userActions) {
		Database.userActions = userActions;
	}
}