package Actors;
import java.util.Date;
import java.util.Vector;
import Attributes.Database;
import Attributes.Payment;
import Enums.TypeOfPayment;
import Interfaces.User;

/**Accountant responsible for financial part of university.*/

public class Accountant extends Employee {
	private static final long serialVersionUID = 1L;
	private Vector <Payment> payments;
	private Vector<Student> notPayForStudentFee;

	public Accountant(User user, String id, Date hireDate, double salary, String insuranceNumber) {
		super(user, id, hireDate, salary, insuranceNumber);
		this.payments = new Vector<Payment>();
		this.notPayForStudentFee = new Vector<Student>();
	} 

	public Vector<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Vector<Payment> payments) {
		this.payments = payments;
	}
	
	public Vector<Student> getNotPayForStudentFee(){
		return notPayForStudentFee;
	}
	
	public void setNotPayForStudentFee(Vector<Student> notPayForStudentFee) {
		this.notPayForStudentFee = notPayForStudentFee;
	}

	public String toString() {
		return "Accountant payments = " + payments + " Students who did not pay for student fee : " + notPayForStudentFee;
	}
	
	/**
	 * allows to see all the payments
	 * */
	public String viewAllPayments() {
		for(Payment p : payments) {
			return p.toString();
		}
		return "\n";
	}
	
	/**
	 * to save students, who have not made a payment
	 * */
	public void getStudentWhoNotPayForSF() {
		for(Student s : Database.getStudents()) {
			boolean check = false;
			for(Payment p : payments) {
				if((p.getTypeOfPayment().equals(TypeOfPayment.STUDENT_FEE))) {
					if(p.getStudent().equals(s)) {
						check = true;
						break;
					}
				}
			}
			if(!check) {
				this.notPayForStudentFee.add(s);
			}
		}
	}
}