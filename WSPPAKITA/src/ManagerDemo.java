import java.io.File;
import java.io.IOException;
import java.util.*;

public class ManagerDemo {
	Scanner in = new Scanner(System.in);
	private void printList(List list) {
		for(int i=0; i<list.size(); i++)
			System.out.println(i+1+ ")" +list.get(i));
	}
	public boolean showStudents(){
		if(Data.INSTANCE.students.isEmpty()) {
			System.out.println("No students yet...Try adding one");
			return false;
		}
		printList(Data.INSTANCE.students);
		return true;
	}
	public boolean showCourses(){
		if(Data.INSTANCE.courses.isEmpty()) {
			System.out.println("No courses yet...");
			return false;
		}
		printList(Data.INSTANCE.courses);
		return true;
	}
	private void save() throws IOException {
		Data.write();
	}
	private void exit() {
		System.out.println("Bye bye");
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void addStudent() {
		System.out.println("Enter name: ");
		Data.INSTANCE.students.add(new Student(in.next()));
		System.out.println("Student added! ");
	}
	private void addCourse() {
		System.out.println("Enter name of the course: ");
		Data.INSTANCE.courses.add(new Course(in.next()));
		System.out.println("Course added! ");		
	}
	private void addCourseToStudent(Student s) {
		int i = in.nextInt()-1;
		Course c = Data.INSTANCE.courses.get(i);
		if(!s.courses.containsKey(c)) {
			s.courses.put(c, new Mark());
			System.out.println("Course "+Data.INSTANCE.courses.get(i) +" added to  "+s.name);	
		}
		else System.out.println("Student " + s.name+ " already registered to "+c);
	}
	public void run() throws IOException {
		try {
			System.out.println("Welcome!");
			menu : while(true){
				System.out.println("What do you want to do?\n 1) Add student \n 2) Add course  \n 3) Add course to a Student  \n 4) View students \n 5) Exit");
				int choice = in.nextInt();
				if(choice==1){
					addStudent: while(true){
						addStudent();
						System.out.println("\n 1) Add another student  \n 2) Return back \n 3) Exit");
						choice = in.nextInt();
						if(choice==1) continue addStudent;
						if(choice==2) continue menu;
						if(choice==3) {exit(); break menu;}
						break;
					}
				}
				else if (choice==2){
					addCourse: while(true){
						addCourse();
						System.out.println("\n 1) Add another course  \n 2) Return back \n 3) Exit");
						choice = in.nextInt();
						if(choice==1) continue addCourse;
						if(choice==2) continue menu;
						if(choice==3) {exit();  break menu;}
						break;
					}
				}
				else if (choice==3){
					addCourseToStudent: while(true){
						System.out.println("Choose student to which you want to add course: (Enter number)");
						if(!showStudents()) continue menu;
						Student s = Data.INSTANCE.students.get(in.nextInt()-1);
						System.out.println("Choose course: (Enter number)");
						if(!showCourses()) continue menu;
						addCourseToStudent(s);
						System.out.println("\n 1) Add another course to some student  \n 2) Return back \n 3) Exit");
						choice = in.nextInt();
						if(choice==1) continue addCourseToStudent;
						if(choice==2) continue menu;
						if(choice==3) {exit(); break menu;}
						break;
					}
				}
				else if (choice==4){
					if(!showStudents()) continue menu;
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = in.nextInt();
					if(choice==1) continue menu;
					if(choice==2) {exit();  break menu;}
					break;
				}
				else if (choice==5){
					exit();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
			save();
		}
	}

}
